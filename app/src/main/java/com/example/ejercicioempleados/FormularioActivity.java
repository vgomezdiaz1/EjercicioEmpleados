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
        if(comprobarVacios()){

            EditText texto;
            texto = findViewById(R.id.editTextNombre);
            String tNombre = texto.getText().toString();
            texto = findViewById(R.id.editTextApellidos);
            String tApellido= texto.getText().toString();
            texto = findViewById(R.id.editTextFormularioTelefono);
            String tTelefono= texto.getText().toString();
            texto = findViewById(R.id.editTextEdad);
            String tEdad= texto.getText().toString();

            Intent i = new Intent(this,ConfirmacionDatosActivity.class);

            i.putExtra("nombre",tNombre);
            i.putExtra("apellido",tApellido);
            i.putExtra("telefono",tTelefono);
            i.putExtra("edad",tEdad);
            startActivity(i);
        }
    }

    private boolean comprobarVacios(){
        EditText texto;
        texto = findViewById(R.id.editTextNombre);
        if(texto.getText().toString().equals("")){

        }
        return true;
    }
}