package com.jah.lista_contactos_crud;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MaterialToolbar mtbMenu;
    RecyclerView rvContactos;
    LinearLayoutManager disposicionp;
    AdaptadorContactos adaptadorContactos;
    GridLayoutManager disposicionl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initComponents();

        setSupportActionBar(mtbMenu);

        if(savedInstanceState != null){
            GestionContactos.setArrContactos((ArrayList<Contacto>) savedInstanceState.getSerializable("contactos"));
            if (GestionContactos.getArrContactos() != null) {
                GestionContactos.setArrContactos(GestionContactos.getArrContactos());
            }
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                disposicionp = new LinearLayoutManager(getApplicationContext());
                rvContactos.setLayoutManager(disposicionp);
            }else{
                disposicionl = new GridLayoutManager(getApplicationContext(), 2);
                rvContactos.setLayoutManager(disposicionl);
            }
            adaptadorContactos = new AdaptadorContactos(GestionContactos.getArrContactos());
            rvContactos.setAdapter(adaptadorContactos);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            disposicionp = new LinearLayoutManager(getApplicationContext());
            rvContactos.setLayoutManager(disposicionp);
        }else{
            disposicionl = new GridLayoutManager(getApplicationContext(), 2);
            rvContactos.setLayoutManager(disposicionl);
        }
        adaptadorContactos = new AdaptadorContactos(GestionContactos.getArrContactos());
        rvContactos.setAdapter(adaptadorContactos);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("contactos", new ArrayList<>(GestionContactos.getArrContactos()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_nueva){
            Intent intent = new Intent(getApplicationContext(), AddContacto.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.item_nombre){
            GestionContactos.ordenarPorNombre();
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                disposicionp = new LinearLayoutManager(getApplicationContext());
                rvContactos.setLayoutManager(disposicionp);
            }else{
                disposicionl = new GridLayoutManager(getApplicationContext(), 2);
                rvContactos.setLayoutManager(disposicionl);
            }
            adaptadorContactos = new AdaptadorContactos(GestionContactos.getArrContactos());
            rvContactos.setAdapter(adaptadorContactos);
        }else if(item.getItemId() == R.id.item_salir){
            createDialogSalir();
        }else if(item.getItemId() == R.id.item_borrar){
            createDialogBorrar();
        }else if(item.getItemId() == R.id.item_editar){
            createDialogEditar();
        }
        return false;
    }

    private void createDialogEditar() {
        String str = "Introduce un numero entre " + 1 + " y "+ GestionContactos.getArrContactos().size();
        EditText txtNumeroCard = new EditText(this);
        txtNumeroCard.setHint(str);
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.dialog_editar_title)
                .setView(txtNumeroCard)
                .setNegativeButton(R.string.dialog_salir_negative, null)
                .setPositiveButton(R.string.dialog_salir_positive, (dialogInterface, i) -> {
                    Intent editar = new Intent(this, EditarContacto.class);
                    int posicion = Integer.parseInt(txtNumeroCard.getText().toString()) - 1;
                    editar.putExtra("posicion", posicion);
                    startActivity(editar);
                })
                .create()
                .show();
    }

    private void createDialogBorrar() {
        String str = "Introduce un numero entre " + 1 + " y "+ GestionContactos.getArrContactos().size();
        EditText txtNumeroCard = new EditText(this);
        txtNumeroCard.setHint(str);
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.dialog_borrar_title)
                .setView(txtNumeroCard)
                .setNegativeButton(R.string.dialog_salir_negative, null)
                .setPositiveButton(R.string.dialog_salir_positive, (dialogInterface, i) -> createDialogConfirmar(txtNumeroCard.getText().toString()))
                .create()
                .show();
    }

    private void createDialogConfirmar(String str_numero) {
        int numero = Integer.parseInt(str_numero) - 1;
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.dialog_confirm_title)
                .setMessage(R.string.dialog_confirm_message)
                .setNegativeButton(R.string.dialog_salir_negative, null)
                .setPositiveButton(R.string.dialog_salir_positive, (dialogInterface, i) -> {
                    GestionContactos.borrarContacto(numero);
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                        disposicionp = new LinearLayoutManager(getApplicationContext());
                        rvContactos.setLayoutManager(disposicionp);
                    }else{
                        disposicionl = new GridLayoutManager(getApplicationContext(), 2);
                        rvContactos.setLayoutManager(disposicionl);
                    }
                    adaptadorContactos = new AdaptadorContactos(GestionContactos.getArrContactos());
                    rvContactos.setAdapter(adaptadorContactos);
                })
                .create()
                .show();
    }

    private void createDialogSalir() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.dialog_salir_title)
                .setMessage(R.string.dialog_salir_message)
                .setNegativeButton(R.string.dialog_salir_negative, null)
                .setPositiveButton(R.string.dialog_salir_positive, (dialogInterface, i) -> finishAffinity())
                .create()
                .show();
    }

    private void initComponents() {
        rvContactos = findViewById(R.id.rvContactos);
        mtbMenu = findViewById(R.id.mtbMenu);
        GestionContactos.cargarContactos();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            disposicionp = new LinearLayoutManager(getApplicationContext());
            rvContactos.setLayoutManager(disposicionp);
        }else{
            disposicionl = new GridLayoutManager(getApplicationContext(), 2);
            rvContactos.setLayoutManager(disposicionl);
        }
        adaptadorContactos = new AdaptadorContactos(GestionContactos.getArrContactos());
        rvContactos.setAdapter(adaptadorContactos);

    }
}