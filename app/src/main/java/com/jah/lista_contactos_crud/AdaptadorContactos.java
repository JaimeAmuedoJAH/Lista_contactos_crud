package com.jah.lista_contactos_crud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.HolderContactos> {

    List<Contacto> dataSet;
    Context context;
    Contacto contacto;
    private static final int REQUEST_CALL_PERMISSION = 1;
    int posicion;

    public AdaptadorContactos(List<Contacto> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public AdaptadorContactos.HolderContactos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new HolderContactos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorContactos.HolderContactos holder, int position) {
        contacto = dataSet.get(position);

        holder.lblNombre.setText(contacto.getNombre());
        holder.lblTelefono.setText(contacto.getTelefono() + "");
        holder.imvFoto.setImageResource(contacto.getFotoContacto());

        holder.cvCard.setOnClickListener(view -> {
            posicion = holder.getAdapterPosition();
            hacerLlamada(posicion);
        });
    }

    private void hacerLlamada(int posicion) {
        String telefono = dataSet.get(posicion).getTelefono() + "";
        Intent llamada = new Intent(Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel: " + telefono));
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        }else{
            context.startActivity(llamada);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class HolderContactos extends RecyclerView.ViewHolder{

        CardView cvCard;
        TextView lblNombre, lblTelefono;
        ImageView imvFoto;

        public HolderContactos(@NonNull View itemView) {
            super(itemView);
            cvCard = itemView.findViewById(R.id.cvCard);
            lblNombre = itemView.findViewById(R.id.lblNombre);
            lblTelefono = itemView.findViewById(R.id.lblTelefono);
            imvFoto = itemView.findViewById(R.id.imgvFoto);
        }
    }
}
