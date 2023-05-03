package com.example.colores;

public class ColorSel {

    private String nombre;
    private int r;
    private int g;
    private int b;
    private int a;
    private int tono;

    public ColorSel(int tono, String nombre, int r, int g, int b, int a) {
        this.tono = tono;
        this.nombre = nombre;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public ColorSel(String nombre, int tono) {
        this.nombre = nombre;
        this.tono = tono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getTono() {
        return tono;
    }

    public void setTono(int tono) {
        this.tono = tono;
    }

    @Override
    public String toString() {
        return "ColorSel{" +
                "nombre='" + nombre + '\'' +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                ", tono=" + tono +
                '}';
    }
}
