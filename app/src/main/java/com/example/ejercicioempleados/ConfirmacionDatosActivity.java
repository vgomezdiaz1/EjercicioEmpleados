package com.example.ejercicioempleados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void pulsacionBtnGuardar(View v) {
        //Cogemos los datos de los textView
        TextView textoNombre = findViewById(R.id.viewSetConfirmacionNombre);
        TextView textoApellido = findViewById(R.id.viewSetConfirmacionApellidos);
        TextView textoTelefono = findViewById(R.id.viewSetConfirmacionTelefono);
        TextView textoEdad = findViewById(R.id.viewSetConfirmacionEdad);
        //Guardamos los datos en variables
        String nombre = textoNombre.getText().toString();
        String apellido = textoApellido.getText().toString();
        String telefono = textoTelefono.getText().toString();
        String email = textoEdad.getText().toString();
        //Creamos un ContentValues, que es lo que guarda los datos para introducir en un BBDD
        ContentValues cv = new ContentValues();
        cv.put("name", nombre);
        cv.put("apellido",apellido);
        cv.put("email", email);
        cv.put("telefono", telefono);
        //Iniciamos la BBDD
        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db), MODE_PRIVATE, null);
        //Insertamos los datos en la BBDD
        long correcto = myDB.insert("empleados",null, cv);
        if(correcto>-1){
            //Creamos un toast que nos confirme que todo esta correcto
            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
            //Volvemos a la pagina principal
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show();
        }
    }
}