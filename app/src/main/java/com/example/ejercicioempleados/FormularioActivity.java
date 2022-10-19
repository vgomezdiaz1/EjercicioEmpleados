package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }
    public void pulsacionBtnAceptar(View v){
        String [] cadena = new String[4];
        EditText texto = findViewById(R.id.editTextNombre);
        cadena[0]= texto.getText().toString();
        texto = findViewById(R.id.editTextApellidos);
        cadena[1]= texto.getText().toString();
        texto = findViewById(R.id.editTextFormularioTelefono);
        cadena[2]= texto.getText().toString();
        texto = findViewById(R.id.editTextEdad);
        cadena[3]= texto.getText().toString();

        Intent i = new Intent(this,ConfirmacionDatosActivity.class);

        i.putExtra("textoapasar",cadena);
        startActivity(i);
    }
}