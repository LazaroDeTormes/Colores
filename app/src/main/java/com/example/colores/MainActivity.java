package com.example.colores;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int DIALOGO_SALIDA = 1;
    private AlertDialog.Builder ventana;
    private TextView datos;
    private TextView cuadrado;
    private SeekBar rojo;
    private SeekBar verde;
    private SeekBar azul;
    private SeekBar blanco;
    private int r, g, b, a;
    private EditText nombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper dbH = new DatabaseHelper(this);



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


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.guardarColorMenu:
                showDialog(DIALOGO_SALIDA);

        }

        return true;
    }

    protected Dialog onCreateDialog(int id){

        switch (id) {
            case DIALOGO_SALIDA:
                ventana = new AlertDialog.Builder(this);
                LayoutInflater inflador = this.getLayoutInflater();
                View view = inflador.inflate(R.layout.laydialogo, null);

                ventana.setTitle("Dale un nombre")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                r = rojo.getProgress();
                                g = verde.getProgress();
                                b = azul.getProgress();
                                a = blanco.getProgress();
                                int color = Color.argb(a,r,g,b);

                                String nom = nombre.getText().toString();

                                ColorSel col = new ColorSel(nom, r, g, b, a, color);
                                Cursor c =
                                System.out.println(col);
                            }
                        })
                        .setView(view);

                nombre = view.findViewById(R.id.etnom);
                break;


        }
        return ventana.create();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuopver:
                Intent i = new Intent(MainActivity.this, lista.class);
                startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
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