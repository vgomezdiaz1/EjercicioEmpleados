package com.example.ejercicioempleados;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {

    ArrayList<Empleado> lista;

    public MiAdaptador(ArrayList<Empleado> lista) {
        this.lista = lista;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtid, txtNombre, txtApellido, textEmail, txtTelefono, txtLinea;

        //Para encontrar lo que queremos rellenar se usa el elemento de entrada itemView
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //Podemos guardar el id en una variable int o para seguir la logica, ponemos un textView
            //y le asignamos en visibility gone
            txtid = itemView.findViewById(R.id.txt_id);
            txtNombre = itemView.findViewById(R.id.ViewNombre);
            txtApellido = itemView.findViewById(R.id.ViewApellidos);
            textEmail = itemView.findViewById(R.id.ViewEmail);
            txtTelefono = itemView.findViewById(R.id.ViewTelefono);
            txtLinea = itemView.findViewById(R.id.viewPasarLinea);
            //Programamos la pulsacion corta
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(itemView.getContext(), "Clicado", Toast.LENGTH_SHORT).show();
                    return false;
                }

            });


            //Ahora vamos a programar que si mantenemos pulsado salga un menu
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Creamos una alerta, dandole el contexto la vista actual (itemView.getContext())
                    AlertDialog.Builder ab = new AlertDialog.Builder(itemView.getContext());
                    //Opcion de minimenu
                    ab.setTitle("Borrar empleado");
                    //mensaje de confirmacion
                    ab.setMessage("¿Seguro que desea borrar el empleado " + txtNombre.getText().toString() + "?");
                    //opcion de si
                    ab.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase myDB = itemView.getContext().openOrCreateDatabase(itemView.getContext().getResources().getString(R.string.db), itemView.getContext().MODE_PRIVATE, null);
                            myDB.execSQL("DELETE FROM empleados WHERE ID = " + txtid.getText().toString());
                            //Con esta linea recargamos la activity y asi se borra de pantalla lo borrado
                            ((Activity)itemView.getContext()).recreate();
                        }
                    });
                    //opcion de no
                    ab.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(), "Empleado no borrado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ab.show();
                    return false;
                }
            });
        }

    }



    //Es un create normal y esto va tal cual, no preguntes porque
    // y coge los datos que cogemos en ListaActivity o de su padre(parent)
    @NonNull
    @Override
    public MiAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());

        View v = inflador.inflate(R.layout.elemento,parent, false);

        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }


    //Esta funcion es para hacer cada elemento de la lista que hemos creado en MyViewHolder
    //y mostrarlo
    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MyViewHolder holder, int position) {
        holder.txtid.setText("" + (lista.get(position).getId()));
        holder.txtNombre.setText(lista.get(position).getNombre());
        holder.txtApellido.setText(lista.get(position).getApellido());
        holder.textEmail.setText(lista.get(position).getEmail());
        holder.txtTelefono.setText(lista.get(position).getTelefono());
        holder.txtLinea.setText("-----------------------------------------------");
    }
    //Este sirve para coger los elementos que tiene la lista
    @Override
    public int getItemCount() {

        return lista.size();
    }
}
