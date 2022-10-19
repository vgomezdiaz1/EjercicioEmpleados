package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pulsacionBtnNuevoEmpleado(View v){
        Intent i = new Intent(this,FormularioActivity.class);
        startActivity(i);
    }

    public void pulsacionBtnListadoEmpleado(View v){

    }
}