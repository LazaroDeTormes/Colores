package com.example.colores;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView datos;
    private TextView cuadrado;
    private SeekBar rojo;
    private SeekBar verde;
    private SeekBar azul;
    private SeekBar blanco;
    private int r, g, b, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        datos = findViewById(R.id.datos);
        cuadrado = findViewById(R.id.cuadrado);
        rojo = findViewById(R.id.rojo);
        verde = findViewById(R.id.verde);
        azul = findViewById(R.id.azul);
        blanco = findViewById(R.id.blanco);



        rojo.setOnSeekBarChangeListener(this);
        verde.setOnSeekBarChangeListener(this);
        azul.setOnSeekBarChangeListener(this);
        blanco.setOnSeekBarChangeListener(this);

        registerForContextMenu(cuadrado);

        inicializacion();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu, menu);

    }

    public void ajustesRGB(){


        r = rojo.getProgress();
        g = verde.getProgress();
        b = azul.getProgress();
        a = blanco.getProgress();

        int color = Color.argb(a,r,g,b);

        cuadrado.setBackgroundColor(color);

        datos.setText("Rojo: "+r+" Verde: "+g+" Azul: "+b+" Transparencia: "+a);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        ajustesRGB();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void inicializacion(){
        ajustesRGB();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);



        outState.putInt("r",r);
        outState.putInt("g",g);
        outState.putInt("b",b);
        outState.putInt("a",a);


    }
}