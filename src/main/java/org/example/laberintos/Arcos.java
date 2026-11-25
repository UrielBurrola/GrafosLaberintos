package org.example.laberintos;

public class Arcos {
    private final Vertice a;
    private final Vertice b;
    private final int costo;

    public Arcos(Vertice a, Vertice b, int costo) {
        this.a = a;
        this.b = b;
        this.costo = costo;
    }

    public Vertice getA() { return a; }
    public Vertice getB() { return b; }
    public int getCosto() { return costo; }

    public Vertice otroExtremo(Vertice v) { return v.getId() == a.getId() ? b : a;}
}
