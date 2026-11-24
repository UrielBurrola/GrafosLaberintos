package org.example.laberintos;

import java.util.ArrayList;

public class Vertice {
    int x;
    int y;

    private ArrayList<Vertice> adyacentes;

    public void agregarAdyacente(Vertice n){
        adyacentes.add(n);
    }


}
