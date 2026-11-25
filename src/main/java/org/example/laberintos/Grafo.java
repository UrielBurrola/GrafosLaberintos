package org.example.laberintos;

import java.util.*;

public class Grafo {
    private final Vertices vertices = new Vertices();
    private final Hashtable<Integer, LinkedList<Arcos>> adyacencia = new Hashtable<>();

    private Vertice entrada;
    private Vertice salida;

    private Resultado resultadoDFS;
    private Resultado resultadoBFS;
    private Resultado resultadoDijkstra;

    public void AgregarVertice(Vertice v){
        vertices.agregar(v);
        adyacencia.put(v.getId(), new LinkedList<>());
    }

    public void agregarArco(Vertice a, Vertice b) {
        Arcos arco = new Arcos(a, b, 1);
        adyacencia.get(a.getId()).add(arco);
        adyacencia.get(b.getId()).add(arco);
    }

    public void setEntrada(Vertice entrada) { this.entrada = entrada; }
    public void setSalida(Vertice salida) { this.salida = salida; }
    public Vertice getEntrada() { return entrada; }
    public Vertice getSalida() { return salida; }
    public Vertices getVertices() { return vertices; }

    //algoritmos
    public List<Vertice> DFS() {
        //TODO implementar DFS por pila; Vecinos arriba, derecha, abajo, izquierda
        //Al final resultadoDFS = construirResultado("DFS", orden, predecesores);
        throw new UnsupportedOperationException("DFS pendiente.");
    }

    public List<Vertice> BFS() {
        //TODO implementar BFS por cola; Vecinos arriba, derecha, abajo, izquierda
        //Al final resultadoBFS = construirResultado("BFS", orden, predecesores);
        throw new UnsupportedOperationException("BFS pendiente.");
    }

    public List<Vertice> caminoMasCorto() {
        //TODO Dijkastra
        throw new UnsupportedOperationException("Dijkstra pendiente.");
    }

    //auxiliares
    private List<Vertice> vecinosEnOrden(Vertice v) {
        // TODO
        throw new UnsupportedOperationException("vecinosEnOrden pendiente");
    }

    private Resultado construirResultado(String algoritmo, List<Vertice> orden, Hashtable<Integer, Integer> predecesores) {
        // TODO
        throw new UnsupportedOperationException("construirResultado pendiente");
    }

    public Resultado getResultadoDFS() { return resultadoDFS; }
    public Resultado getResultadoBFS() { return resultadoBFS; }
    public Resultado getResultadoDijkstra() { return resultadoDijkstra; }

}
