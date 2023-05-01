package com.example.colores;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String nombre_bbdd = "colores.db";
    private static final int version = 1;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, nombre_bbdd, null, version);
    }


    public ArrayList<ColorSel> getAllData(){
        try{
            ArrayList<ColorSel> arrayList = new ArrayList<>();
            SQLiteDatabase bbdd = getReadableDatabase();
            if (bbdd != null){
                Cursor buscar = bbdd.rawQuery("select * from colores.db", null);
                if (buscar.getCount()!=0){
                    while (buscar.moveToNext()){
                        String nombre = buscar.getString(0);
                        int tono = buscar.getInt(5);
                        arrayList.add(new ColorSel(nombre, tono));
                    }
                    return arrayList;
                }
            }else {

                return null;
            }

        }catch (Exception e){
            Toast.makeText(context, "No hay colores guardados", Toast.LENGTH_SHORT).show();
        }
        return null;
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
