package com.example.ejercicioempleados;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class MiAdaptador extends RecyclerView.Adapter<MiAdpatador.MyViewHolder> {

    public static class MyViewHolder()extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }



    //Es un create normal
    @NonNull
    @Override
    public MiAdpatador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    //Esta funcion es para hacer cada elemento de la lista
    @Override
    public void onBindViewHolder(@NonNull MiAdpatador.MyViewHolder holder, int position) {

    }



    //Este sirve para coger los elementos que tiene la lista
    @Override
    public int getItemCount() {
        return 0;
    }
}
