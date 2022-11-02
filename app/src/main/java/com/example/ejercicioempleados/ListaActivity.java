
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.ejercicioempleados.MiAdaptador;
import com.example.ejercicioempleados.R;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        /*
        //Iniciamos la bbdd en esta activity
        SQLiteDatabase myDB = openOrCreateDatabase(getResources().getString(R.string.db), MODE_PRIVATE, null);
        //Al hacer esta consulta me devuelve un cursor
        Cursor cursor = myDB.rawQuery("select * from empleados",null);
        //mientras siga habiendo objetos en el cursor
        while(cursor.moveToNext()){
            //Ponemos lo que sea cada parte del objeto y lo guardamos
            String nombre = cursor.getString(0);//Esta es la posicion dentro de la consulta
            String apellidos = cursor.getString(1);
            String email = cursor.getString(2);
            String telefono = cursor.getString(3);
            //Aqui lo imprimimos por LOGCAT
            Log.v("empleado", nombre + " " + apellidos + " " + telefono + " " + email);
        }*/
        //Aqui es cuando empezamos a mostrarlas en pantalla
        //
        //Muy importante Hay que poner un reciclerView y poner match_parent en layout_width y layout_height
        //Creamos un Layout llamado elemento.xml y lo cambiamos a linear layout y añadimos tantos textView como hagan falta
        //
        //Esto es copiar y pegar para las listas
        RecyclerView rv = findViewById(R.id.lista_Empleados);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        MiAdaptador adaptador = new MiAdaptador();
        rv.setAdapter(adaptador);



    }
}