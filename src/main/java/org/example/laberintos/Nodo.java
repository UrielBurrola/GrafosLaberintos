package org.example.laberintos;

import java.util.ArrayList;

public class Nodo {
    int x;
    int y;

    private ArrayList<Nodo> adyacentes;

    public void agregarAdyacente(Nodo n){
        adyacentes.add(n);
    }


}
