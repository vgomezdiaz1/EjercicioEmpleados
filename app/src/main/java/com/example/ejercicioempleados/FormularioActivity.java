package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }
    public void pulsacionBtnAceptar(View v){
        int n = 0;
        String tNombre ="";
        String tApellido ="";
        String tTelefono ="";
        String tEdad ="";
        EditText tNom = findViewById(R.id.editTextNombre);
        EditText tApe = findViewById(R.id.editTextApellidos);
        EditText tTelf = findViewById(R.id.editTextFormularioTelefono);
        EditText tEd = findViewById(R.id.editTextEdad);
        if(comprobarVacios(tEd)) {
            tEdad = tEd.getText().toString();
            n++;
        }
        if(comprobarVacios(tTelf)) {
            tTelefono = tTelf.getText().toString();
            n++;
        }
        if(comprobarVacios(tApe)){
            tApellido = tApe.getText().toString();
            n++;
        }
        if(comprobarVacios(tNom)) {
            tNombre = tNom.getText().toString();
            n++;
        }
        if(n==4){
            Intent i = new Intent(this,ConfirmacionDatosActivity.class);
            i.putExtra("nombre",tNombre);
            i.putExtra("apellido",tApellido);
            i.putExtra("telefono",tTelefono);
            i.putExtra("edad",tEdad);
            startActivity(i);
        }else{
            Toast.makeText(this,"Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean comprobarVacios(EditText x){
        if(x.getText().toString().equals("")){
            x.requestFocus();
            x.setBackgroundTintList(getColorStateList(R.color.red));
            return false;
        }else{
            x.setBackgroundTintList(getColorStateList(R.color.teal_700));
            return true;
        }
    }
}