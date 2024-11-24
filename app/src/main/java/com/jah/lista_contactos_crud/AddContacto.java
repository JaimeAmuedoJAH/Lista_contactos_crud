package com.jah.lista_contactos_crud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddContacto extends AppCompatActivity {

    EditText txtAddNombre, txtAddTelefono;
    Button btnAdd;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contacto);
        initComponents();

        btnAdd.setOnClickListener(view -> aniadir());
    }

    private void aniadir() {
        String nombre = txtAddNombre.getText().toString();
        int telefono = Integer.parseInt(txtAddTelefono.getText().toString());
        int foto = R.drawable.predeterminado;

        contacto = new Contacto(nombre, telefono, foto);
        GestionContactos.anhadirContacto(contacto);
        finish();
    }

    private void initComponents() {
        txtAddNombre = findViewById(R.id.txtAddNombre);
        txtAddTelefono = findViewById(R.id.txtAddTelefono);
        btnAdd = findViewById(R.id.btnAdd);
    }
}