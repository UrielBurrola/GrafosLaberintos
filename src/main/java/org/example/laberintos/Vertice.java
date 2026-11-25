package org.example.laberintos;

import java.util.ArrayList;

public class Vertice {
    private final int id;
    private final int fila;
    private final int columna;


    public Vertice(int id, int fila, int columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
    }
    public int getId() { return id; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertice)) return false;
        return ((Vertice) o).id == id;
    }
    @Override
    public int hashCode() { return Integer.hashCode(id); }
    @Override
    public String toString() { return "V" + id + "(" + fila + "," + columna + ")"; }
}
