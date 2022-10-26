package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmacionDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);
        Intent i = getIntent();

        TextView textoNombre = findViewById(R.id.viewSetConfirmacionNombre);
        textoNombre.setText(i.getStringExtra("nombre"));
        TextView textoApellido = findViewById(R.id.viewSetConfirmacionApellidos);
        textoApellido.setText(i.getStringExtra("apellido"));
        TextView textoTelefono = findViewById(R.id.viewSetConfirmacionTelefono);
        textoTelefono.setText(i.getStringExtra("telefono"));
        TextView textoEdad = findViewById(R.id.viewSetConfirmacionEdad);
        textoEdad.setText(i.getStringExtra("edad"));
    }

    public void pulsacionBtnAceptar(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}