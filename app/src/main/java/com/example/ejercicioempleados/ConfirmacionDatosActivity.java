package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmacionDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);
        Intent i = getIntent();
        //Aqui ponemos la clave del extra que le hemos metido el intent en el otro lado
        String [] cadena = i.getStringArrayExtra("textoapasar");

        TextView textoNombre = findViewById(R.id.viewSetConfirmacionNombre);
        textoNombre.setText(cadena[0]);
        TextView textoApellido = findViewById(R.id.viewSetConfirmacionApellidos);
        textoApellido.setText(cadena[1]);
        TextView textoTelefono = findViewById(R.id.viewSetConfirmacionTelefono);
        textoTelefono.setText(cadena[2]);
        TextView textoEdad = findViewById(R.id.viewSetConfirmacionEdad);
        textoEdad.setText(cadena[3]);
    }

    public void pulsacionBtnAceptar(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}