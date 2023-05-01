package com.example.colores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class lista extends AppCompatActivity {

    private ListView lista;
    private ArrayList<ColorSel> colores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = findViewById(R.id.lista);
        colores = new ArrayList<>();

        Adaptador adaptador = new Adaptador(this, R.layout.fila, colores);

        lista.setAdapter(adaptador);




    }
}