package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db), MODE_PRIVATE, null);
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS empleados " +
                "(name VARCHAR(50), " +
                "apellido varchar(50), " +
                "email varchar(100), " +
                "telefono varchar(20))"
        );

    }

    public void pulsacionBtnNuevoEmpleado(View v) {
        Intent i = new Intent(this, FormularioActivity.class);
        startActivity(i);
    }

    public void pulsacionBtnListadoEmpleado(View v) {

    }
}