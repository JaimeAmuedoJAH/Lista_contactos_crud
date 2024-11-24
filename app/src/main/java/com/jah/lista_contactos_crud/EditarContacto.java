package com.jah.lista_contactos_crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class EditarContacto extends AppCompatActivity {

    EditText txtEditNombre, txtEditNumero;
    RadioGroup rgFotos1, rgFotos2;
    RadioButton rbFoto1, rbFoto2, rbFoto3, rbFoto4, rbFoto5, rbFoto6;
    Button btnEditar;
    ImageView imgv1, imgv2, imgv3, imgv4, imgv5, imgv6;
    Intent data;
    Contacto c;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_contacto);
        initComponents();

        btnEditar.setOnClickListener(view -> editar());
    }

    private void editar() {
        int telefono = Integer.parseInt(txtEditNumero.getText().toString());
        c.setNombre(txtEditNombre.getText().toString());
        c.setTelefono(telefono);
        if(rbFoto1.isChecked()){
            c.setFotoContacto(R.drawable.imagen1);
        }else if(rbFoto2.isChecked()){
            c.setFotoContacto(R.drawable.imagen2);
        }else if(rbFoto3.isChecked()){
            c.setFotoContacto(R.drawable.imagen3);
        }else if(rbFoto4.isChecked()){
            c.setFotoContacto(R.drawable.imagen4);
        }else if(rbFoto5.isChecked()){
            c.setFotoContacto(R.drawable.imagen5);
        }else if(rbFoto6.isChecked()){
            c.setFotoContacto(R.drawable.imagen6);
        }else{
            c.setFotoContacto(R.drawable.predeterminado);
        }

        GestionContactos.editarContacto(posicion, c);
        finish();
    }

    private void initComponents() {
        txtEditNombre = findViewById(R.id.txtEditNombre);
        txtEditNumero = findViewById(R.id.txtEditNumero);
        rgFotos1 = findViewById(R.id.rgFotos1);
        rgFotos2 = findViewById(R.id.rgFotos2);
        rbFoto1 = findViewById(R.id.rbFoto1);
        rbFoto2 = findViewById(R.id.rbFoto2);
        rbFoto3 = findViewById(R.id.rbFoto3);
        rbFoto4 = findViewById(R.id.rbFoto4);
        rbFoto5 = findViewById(R.id.rbFoto5);
        rbFoto6 = findViewById(R.id.rbFoto6);
        btnEditar = findViewById(R.id.btnEditar);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);
        imgv4 = findViewById(R.id.imgv4);
        imgv5 = findViewById(R.id.imgv5);
        imgv6 = findViewById(R.id.imgv6);
        data = getIntent();
        posicion = data.getIntExtra("posicion", 0);
        c = GestionContactos.getArrContactos().get(posicion);

        assert c != null;
        txtEditNombre.setText(c.getNombre());
        txtEditNumero.setText(c.getTelefono() + "");
    }
}