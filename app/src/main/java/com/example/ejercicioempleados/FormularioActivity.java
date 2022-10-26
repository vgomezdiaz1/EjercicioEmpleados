package com.example.ejercicioempleados;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    public void pulsacionBtnAceptar(View v) {
        int n = 0;
        String tNombre = "";
        String tApellido = "";
        String tTelefono = "";
        String tEdad = "";
        EditText tNom = findViewById(R.id.editTextNombre);
        EditText tApe = findViewById(R.id.editTextApellidos);
        EditText tTelf = findViewById(R.id.editTextFormularioTelefono);
        EditText tEd = findViewById(R.id.editTextEdad);
        if (comprobarVacios(tEd)) {
            tEdad = tEd.getText().toString();
            n++;
        }
        if (comprobarVacios(tTelf)) {
            tTelefono = tTelf.getText().toString();
            n++;
        }
        if (comprobarVacios(tApe)) {
            tApellido = tApe.getText().toString();
            n++;
        }
        if (comprobarVacios(tNom)) {
            tNombre = tNom.getText().toString();
            n++;
        }
        if (n == 4) {
            Intent i = new Intent(this, ConfirmacionDatosActivity.class);
            i.putExtra("nombre", tNombre);
            i.putExtra("apellido", tApellido);
            i.putExtra("telefono", tTelefono);
            i.putExtra("edad", tEdad);
            startActivity(i);
        } else {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void pulsacionBtnCargarContacto(View v) {
        Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, 34);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 34) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Todo ha ido bien y ha clicado ok", Toast.LENGTH_SHORT).show();
                //Guardas el contacto en un Uri de Android no de java
                Uri contactData = data.getData();
                Cursor c = getContentResolver().query(contactData,null,null,null,null);
                if(c.moveToFirst()){
                    String whereName = ContactsContract.Data.MIMETYPE + " = ?";
                    String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE };
                    Cursor nameCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                    while (nameCur.moveToNext()) {
                        int i = nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                        int i2 =nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
                        String given = nameCur.getString(i);
                        String family = nameCur.getString(i2);

                        EditText tNom = findViewById(R.id.editTextNombre);
                        tNom.setText(given);

                        EditText tApe = findViewById(R.id.editTextApellidos);
                        tApe.setText(family);
                    }
                    nameCur.close();

                    /*int i = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_SOURCE);
                    EditText tNom = findViewById(R.id.editTextNombre);
                    tNom.setText(c.getString(i));
                    int i1 = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_SOURCE);
                    EditText tApe = findViewById(R.id.editTextApellidos);
                    tApe.setText(c.getString(i1));
                    int i2 = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                    EditText tTelf = findViewById(R.id.editTextFormularioTelefono);
                    tTelf.setText(c.getString(i2));
                    int i3 = c.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                    EditText tEd = findViewById(R.id.editTextEdad);
                    tEd.setText(c.getString(i3));*/
                }
            } else {
                Toast.makeText(this, "Todo ha ido bien pero ha clicado cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean comprobarVacios(EditText x) {
        if (x.getText().toString().equals("")) {
            x.requestFocus();
            x.setBackgroundTintList(getColorStateList(R.color.red));
            return false;
        } else {
            x.setBackgroundTintList(getColorStateList(R.color.teal_700));
            return true;
        }
    }
}