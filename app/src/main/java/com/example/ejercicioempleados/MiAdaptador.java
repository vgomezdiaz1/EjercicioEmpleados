package com.example.ejercicioempleados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView txtNombre, txtApellido, textEmail, txtTelefono;

        //Para encontrar lo que queremos rellenar se usa el elemento de entrada itemView
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.ViewNombre);
            txtApellido = itemView.findViewById(R.id.ViewApellidos);
            textEmail = itemView.findViewById(R.id.ViewEmail);
            txtTelefono = itemView.findViewById(R.id.ViewTelefono);

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
    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MyViewHolder holder, int position) {
        holder.txtNombre.setText(lista.get(position).getNombre());
        holder.txtApellido.setText(lista.get(position).getApellido());
        holder.textEmail.setText(lista.get(position).getEmail());
        holder.txtTelefono.setText(lista.get(position).getTelefono());
    }
    //Este sirve para coger los elementos que tiene la lista
    @Override
    public int getItemCount() {

        return lista.size();
    }
}
