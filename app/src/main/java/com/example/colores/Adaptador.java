package com.example.colores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {

    private Activity contexto;
    private ArrayList<ColorSel> colores;

    public Adaptador(Activity contexto, int layout, ArrayList<ColorSel> colores) {
        super(contexto, layout, colores);
        this.contexto = contexto;
        this.colores = colores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;

        Adaptador.ViewHolder vh;

        if (fila == null){

            LayoutInflater inflador = contexto.getLayoutInflater();
            fila = inflador.inflate(R.layout.fila, null);
            vh = new Adaptador.ViewHolder();

            vh.tono = fila.findViewById(R.id.colorTono);

            vh.nom = fila.findViewById(R.id.colorNombre);

            fila.setTag(vh);

        } else {
            vh = (Adaptador.ViewHolder) fila.getTag();
        }

        LinearLayout tono = fila.findViewById(R.id.colorTono);
        TextView nom = fila.findViewById(R.id.colorNombre);

        tono.setBackgroundColor(colores.get(position).getTono());
        nom.setText(colores.get(position).getNombre());



        return fila;

    }


    public class ViewHolder{
        private LinearLayout tono;
        private TextView nom;
    }


}
