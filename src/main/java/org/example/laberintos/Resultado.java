package org.example.laberintos;
import java.util.List;

public class Resultado {
    private final String algoritmo;
    private final List<Vertice> ordenVisita;
    private final List<Vertice> camino;

    private final boolean HaySolucion;


    public Resultado(String algoritmo, List<Vertice> ordenVisita, List<Vertice> camino, boolean haySolucion) {
        this.algoritmo = algoritmo;
        this.ordenVisita = ordenVisita;
        this.camino = camino;
        this.HaySolucion = haySolucion;
    }

    public String getAlgoritmo() { return algoritmo; }
    public List<Vertice> getOrdenVisita() { return ordenVisita; }
    public List<Vertice> getCamino() { return camino; }
    public boolean haySolucion() { return HaySolucion; }

    //movimientos
    public int longitudMovimientos() { return haySolucion() ? camino.size() -1:-1; }
}
