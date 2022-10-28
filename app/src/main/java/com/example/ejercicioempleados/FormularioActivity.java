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
                    //Aqui seleccionamos el id que ha cogido el usuario, sino como cogemos toda la agenda coge el primero de la agenda
                    int idContactoQueQuiero = c.getColumnIndex(ContactsContract.Contacts._ID);
                    String id = c.getString(idContactoQueQuiero);
                    //Nos dice el tipo de dato que vamos a sacar oseasela consulta
                    String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ? ";
                    //Es un array porque nos puede devolver varias sentencias
                    //Le ponemos el tipo de elemento que queremos extraer dentro de los {}, para igualar el tipo de dato con el whereName
                    //En las dos datos que se pone aqui, se cambia por las interrogaciones de la consulta de mas arriba
                    String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, id };
                    //Hacemos el cuersor para el nombre
                    //1º donde hace la busqueda el cursor (dentro de la agenda toda la informacion)
                    //2º proyeccion a null
                    //3º El tipo de datro que recibimos
                    //4º Todos los datos que hemos sacado en la consulta
                    //5º El orden a filtrar
                    Cursor nameCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                    //Mientras haya info en el cursor
                    while (nameCur.moveToNext()) {
                        //Sacamos los dos indices que necesitamos
                        int i = nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                        int i2 =nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
                        //Guardamos en un String la posicion del cursor en la que estan los datos que queremos
                        String given = nameCur.getString(i);
                        String family = nameCur.getString(i2);
                        //Añadimos a donde queremos los datos extraidos para mostrarlos
                        EditText tNom = findViewById(R.id.editTextNombre);
                        tNom.setText(given);
                        EditText tApe = findViewById(R.id.editTextApellidos);
                        tApe.setText(family);
                    }
                    nameCur.close();

                    //Con estas sentencias controlamos la cantidad de numeros que tiene el contacto
                    //Guardando en hp el numero de numeros, para realizar las consultas que correspondan
                    int i2 = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                    int hp = Integer.parseInt(c.getString(i2));
                    //Con todo esto cogemos el telefono del contacto seleccionado
                    if(hp>0){
                        String whereTelf = ContactsContract.CommonDataKinds.Phone.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ";
                        String[] whereTelfParams = new String[] { ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, id };
                        Cursor telfCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereTelf, whereTelfParams, null);
                        while (telfCur.moveToNext()) {
                            int i = telfCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String given = telfCur.getString(i);
                            EditText tTelf = findViewById(R.id.editTextFormularioTelefono);
                            tTelf.setText(given);
                        }
                        telfCur.close();
                    }

                    //Con todo esto cogemos el email del contacto seleccionado
                    String whereMail = ContactsContract.CommonDataKinds.Email.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ";
                    String[] whereMailParams = new String[] { ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE , id };
                    Cursor mailCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereMail, whereMailParams, null);
                    while (mailCur.moveToNext()) {
                        int i = mailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                        String given = mailCur.getString(i);
                        EditText tEd = findViewById(R.id.editTextEdad);
                        tEd.setText(given);
                    }
                    mailCur.close();
                    /*
                    int i2 = c.getColumnIndex(ContactsContract.Contacts.Data.DATA4);
                    EditText tTelf = findViewById(R.id.editTextFormularioTelefono);
                    tTelf.setText(c.getString(i3));
                    int i3 = c.getColumnIndex(ContactsContract.Contacts.Data.DATA4);
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