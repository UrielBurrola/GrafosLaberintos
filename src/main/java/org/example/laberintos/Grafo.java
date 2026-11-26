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

    public void agregarVertice(Vertice v){
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
        //implementar DFS por pila; Vecinos arriba, derecha, abajo, izquierda
        if (entrada == null || salida == null) {
            throw new IllegalStateException("Entrada y salida no definidas.");
        }

        List<Vertice> orden = new ArrayList<>();
        Hashtable<Integer, Integer> predecesores = new Hashtable<>();
        Set<Integer> visitados = new HashSet<>();
        Deque<Vertice> pila = new ArrayDeque<>();

        pila.push(entrada);
        while (!pila.isEmpty()) {
            Vertice actual = pila.pop();
            if (visitados.contains(actual.getId())){
                continue;

            }

            visitados.add(actual.getId());
            orden.add(actual);

            if (actual.getId() == salida.getId()) {
                break;
            }

            List<Vertice> vecinos = vecinosEnOrden(actual);
            for (int i = vecinos.size() - 1; i >= 0; i--) {
                Vertice vecino = vecinos.get(i);
                if (!visitados.contains(vecino.getId())) {
                    pila.push(vecino);
                    predecesores.put(vecino.getId(), actual.getId());
                }
            }
        }


        resultadoDFS = construirResultado("DFS", orden, predecesores);
        return orden;
    }

    public List<Vertice> BFS() {
        if (entrada == null || salida == null) {
            throw new IllegalStateException("Entrada y salida no definidas.");
        }

        List<Vertice> orden = new ArrayList<>();
        Hashtable<Integer, Integer> predecesores = new Hashtable<>();
        Set<Integer> descubiertos = new HashSet<>();
        Queue<Vertice> cola = new LinkedList<>();

        cola.add(entrada);
        descubiertos.add(entrada.getId());

        while (!cola.isEmpty()) {
            Vertice actual = cola.remove();
            orden.add(actual);
            if (actual.getId() == salida.getId()) {
                break;
            }

            // Agregar vecinos no visitados a la cola
            for (Vertice vecino : vecinosEnOrden(actual)) {
                if (!descubiertos.contains(vecino.getId())) {
                    cola.add(vecino);
                    descubiertos.add(vecino.getId());
                    predecesores.put(vecino.getId(), actual.getId());
                }
            }
        }
        resultadoBFS = construirResultado("BFS", orden, predecesores);
        return orden;

    }

    // Clase auxiliar para caminoMasCorto
    private static class NodoDistancia {
        final Vertice vertice;
        final int distancia;

        NodoDistancia(Vertice vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }

    public List<Vertice> caminoMasCorto() {
        if (entrada == null || salida == null) {
            throw new IllegalStateException("Entrada y salida no definidas.");
        }

        List<Vertice> orden = new ArrayList<>();
        Hashtable<Integer, Integer> predecesores = new Hashtable<>();
        Hashtable<Integer, Integer> distancias = new Hashtable<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>(Comparator.comparingInt(n -> n.distancia));

        for (Vertice v : vertices.todos()) {
            distancias.put(v.getId(), Integer.MAX_VALUE);
        }

        distancias.put(entrada.getId(), 0);
        cola.add(new NodoDistancia(entrada, 0));

        while (!cola.isEmpty()) {
            NodoDistancia nodoActual = cola.poll();
            Vertice actual = nodoActual.vertice;

            // Ignorar entradas desactualizadas en la cola de prioridad
            if (visitados.contains(actual.getId())) {
                continue;
            }

            visitados.add(actual.getId());
            orden.add(actual);

            if (actual.getId() == salida.getId()) {
                break;
            }

            for (Arcos arco : adyacencia.get(actual.getId())) {
                Vertice vecino = arco.otroExtremo(actual);
                if (visitados.contains(vecino.getId())) {
                    continue;
                }

                int nuevaDistancia = distancias.get(actual.getId()) + arco.getCosto();
                if (nuevaDistancia < distancias.get(vecino.getId())) {
                    distancias.put(vecino.getId(), nuevaDistancia);
                    predecesores.put(vecino.getId(), actual.getId());
                    cola.add(new NodoDistancia(vecino, nuevaDistancia));
                }
            }
        }

        resultadoDijkstra = construirResultado("Dijkstra", orden, predecesores);
        return orden;
    }

    //auxiliares
    private List<Vertice> vecinosEnOrden(Vertice v) {
        int[] dfila = {-1, 0, 1, 0};
        int[] dcolumna = {0, 1, 0, -1};
        List<Vertice> resultado = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int fila = v.getFila() + dfila[i];
            int columna = v.getColumna() + dcolumna[i];

            for (Arcos a : adyacencia.get(v.getId())) {
                Vertice w = a.otroExtremo(v);
                if (w.getFila() == fila && w.getColumna() == columna) {
                    resultado.add(w);
                    break;
                }
            }

        }
        return resultado;
    }

    private Resultado construirResultado(String algoritmo, List<Vertice> orden, Hashtable<Integer, Integer> predecesores) {
        boolean haySolucion = orden.get(orden.size() - 1).getId() == salida.getId();
        LinkedList<Vertice> camino = new LinkedList<>();
        if (haySolucion) {
            Integer id = salida.getId();
            while (id != null) {
                camino.addFirst(vertices.obtener(id));
                id = predecesores.get(id);
            }
        }
        return new Resultado(algoritmo, orden, camino, haySolucion);
    }

    public Resultado getResultadoDFS() { return resultadoDFS; }
    public Resultado getResultadoBFS() { return resultadoBFS; }
    public Resultado getResultadoDijkstra() { return resultadoDijkstra; }

}
