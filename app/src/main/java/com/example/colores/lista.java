package com.example.colores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class lista extends AppCompatActivity {

    private static final int DIALOGO_BORRADO = 2;
    private ListView lista;
    private DatabaseHelper dbH;
    private LinearLayout fondotitulomenu;
    private AlertDialog.Builder ventana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        dbH = new DatabaseHelper(this);

        lista = findViewById(R.id.lista);



        Adaptador adaptador = new Adaptador(this, R.layout.fila, dbH.getAllData());

        lista.setAdapter(adaptador);

        registerForContextMenu(lista);







    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ColorSel eleccion = (ColorSel) lista.getAdapter().getItem(info.position);
        LayoutInflater inflador = this.getLayoutInflater();
        View view = inflador.inflate(R.layout.fondotitulo, null);

        fondotitulomenu = view.findViewById(R.id.fondotitulomenu);
        fondotitulomenu.setBackgroundColor(eleccion.getTono());
        menu.setHeaderView(view);
        inflater.inflate(R.menu.menubaseitem, menu);

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuBorrarTupla:
                showDialog(DIALOGO_BORRADO);

        }

        return true;
    }

    protected Dialog onCreateDialog(int id){

        switch (id) {
            case DIALOGO_BORRADO:
                ventana = new AlertDialog.Builder(this);

                ventana.setTitle("¿Seguro que quieres borrar este color?")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lista.getAdapter().getItem()
                                borrarTupla(1);

                            }
                        });

                break;


        }
        return ventana.create();
    }

    private void borrarTupla(int tono) {

        SQLiteDatabase bbdd = dbH.getWritableDatabase();
        bbdd.delete("colores", "tono="+tono, null);

    }
}