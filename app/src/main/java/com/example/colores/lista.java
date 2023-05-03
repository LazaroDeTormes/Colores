package com.example.colores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class lista extends AppCompatActivity {

    private ListView lista;
    private DatabaseHelper dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        dbH = new DatabaseHelper(this);
        lista = findViewById(R.id.lista);

        Adaptador adaptador = new Adaptador(this, R.layout.fila, dbH.getAllData());

        lista.setAdapter(adaptador);






    }
}