package com.example.colores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.colores.Constantes;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {



    private Context context;

    public DatabaseHelper(Context context) {
        super(context, Constantes.NOMBRE_BBDD, null, Constantes.VERSION);
        this.context = context;
    }


    public ArrayList<ColorSel> getAllData(){
        ArrayList<ColorSel> arrayList = new ArrayList<>();
        try{

            SQLiteDatabase bbdd = getReadableDatabase();
            if (bbdd != null){
                Cursor buscar = bbdd.rawQuery("select * from colores", null);
                if (buscar.getCount()!=0){
                    while (buscar.moveToNext()){
                        String nombre = buscar.getString(1);
                        int tono = buscar.getInt(0);
                        arrayList.add(new ColorSel(tono, nombre));
                    }
                    return arrayList;
                }
            }else {

                return arrayList;
            }

        }catch (Exception e){
            Toast.makeText(context, "No hay colores guardados", Toast.LENGTH_SHORT).show();
        }
        return arrayList;
    }

    public void añadirEntrada(String nombre, int tono){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor añadir = bd.rawQuery("insert into colores (nombre, tono) values (nombre, tono)", null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
