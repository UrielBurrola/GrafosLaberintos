package org.example.laberintos;

import java.util.List;
import java.util.ArrayList;

public class ManejadorGrafo {
    private Grafo grafo; //null hasta que cargue un laberinto
    private String laberinto; // texto original (para el dibujo)

    public void cargar(String laberinto) {
        Grafo nuevo = construirGrafo(laberinto);
        this.grafo = nuevo;
        this.laberinto = laberinto;
    }

    public boolean hayLaberinto() {
        return grafo != null;
    }

    private Grafo construirGrafo(String laberinto) {
        String[] filas = laberinto.split("\\R");
        int numFilas = filas.length;
        int numColumnas = filas[0].length();

        Grafo g = new Grafo();
        Vertice[][] mapa = new Vertice[numFilas][numColumnas];

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                char ch = filas[i].charAt(j);
                if (ch == ' ' || ch == 'E' || ch == '#' || ch == 'S') {
                    mapa[i][j] = new Vertice(i * numColumnas + j, i, j);
                    g.agregarVertice(mapa[i][j]);
                }
            }
        }
        //Arcos. cada vertice con el de arriba y el de la izquierda, si existen
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (i > 0 && mapa[i][j] != null && mapa[i - 1][j] != null) g.agregarArco(mapa[i][j], mapa[i - 1][j]);
                if (j > 0 && mapa[i][j] != null && mapa[i][j - 1] != null) g.agregarArco(mapa[i][j], mapa[i][j - 1]);
            }

        }
        Vertice entrada = null, salida = null;
        //a) Recorrer las filas buscando 'E' y 'S'; si aparecen tomar mapa [i][j]
        //b) Si falta alguna: recorrer en orden de lectura y completar SOLO lo que falta con un vertice del borde
        // c) si despues de b) sigue faltando alguna: throw new IllegalArgumentException
        //a):
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (filas[i].charAt(j) == 'E') {
                    entrada = mapa[i][j];
                }
                if (filas[i].charAt(j) == 'S') {
                    salida = mapa[i][j];
                }
            }
        }

        //b):
        if (entrada == null || salida == null) {
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    if (filas[i].charAt(j) == ' ' || filas[i].charAt(j) == '#') {
                        Vertice v = mapa[i][j];
                        boolean esBorde = i == 0 || i == numFilas - 1 || j == 0 || j == numColumnas - 1;
                        if (v != null && esBorde) {
                            if (entrada == null && v != salida) {
                                entrada = v;
                            } else if (salida == null && v != entrada) {
                                salida = v;
                            }
                        }

                    }
                }
            }
        }

        //c:
        if (entrada == null || salida == null) {
            throw new IllegalArgumentException("No se encontraron entradas y salidas");
        }

        g.setEntrada(entrada);
        g.setSalida(salida);

        return g;
    }

    public List<String> resolverDFS() {
        return despliegaLaberinto(grafo.DFS(), grafo.getResultadoDFS());
    }

    public List<String> resolverBFS() {

        return despliegaLaberinto(grafo.BFS(), grafo.getResultadoBFS());
    }

    public List<String> resolverDijkstra() {
        return despliegaLaberinto(grafo.caminoMasCorto(), grafo.getResultadoDijkstra());
    }

    private static final String LEYENDA = "\nE entrada   S salida   @ actual   . visitado   * camino\n" + "+ - | pared   # y espacio: posicion transitable\n";

    //String por paso y marca el camino final o te dice si no hay solucion
    public List<String> despliegaLaberinto(List<Vertice> ordenVisita, Resultado resultado) {
        String[] filas = laberinto.split("\\R");
        char[][] tablero = new char[filas.length][];
        for (int i = 0; i < filas.length; i++) {
            tablero[i] = filas[i].toCharArray();
        }

        Vertice entrada = grafo.getEntrada();
        Vertice salida = grafo.getSalida();
        tablero[entrada.getFila()][entrada.getColumna()] = 'E';
        tablero[salida.getFila()][salida.getColumna()] = 'S';

        List<String> pasos = new ArrayList<>();
        Vertice anterior = null;
        for (Vertice v : ordenVisita) {
            if (anterior != null) {
                marcarVisitado(tablero, anterior, entrada, salida);
            }
            tablero[v.getFila()][v.getColumna()] = '@';
            pasos.add(tableroATexto(tablero) + LEYENDA);
            anterior = v;
        }
        if (anterior != null) {
            marcarVisitado(tablero, anterior, entrada, salida);
        }
        if (resultado.haySolucion()) {
            for(Vertice v : resultado.getCamino()){
                if (tablero[v.getFila()][v.getColumna()] == '.'){
                    tablero[v.getFila()][v.getColumna()] = '*';
                }
            }
            pasos.add(tableroATexto(tablero) + LEYENDA + "\n" + resultado.getAlgoritmo() + ": " + ordenVisita.size() + " visitados, " + resultado.longitudMovimientos() + " movimientos");
        } else {
            pasos.add(tableroATexto(tablero) + LEYENDA + "\n" + resultado.getAlgoritmo() + ": no hay solución");
        }
        return pasos;
    }

    private void marcarVisitado(char[][] tablero, Vertice v, Vertice entrada, Vertice salida) {
        char marca = '.';
        if (v.equals(entrada)) marca = 'E';
        else if (v.equals(salida)) marca = 'S';
        tablero[v.getFila()][v.getColumna()] = marca;
    }

    private String tableroATexto(char[][] tablero) {
        StringBuilder sb = new StringBuilder();
        for (char[] fila : tablero) {
            sb.append(new String(fila)).append("\n");
        }
        return sb.toString();
    }


    public String mostrarTresSoluciones() {
        //TODO
        throw new UnsupportedOperationException("mostrarTresSoluciones pendiente");
    }
}
