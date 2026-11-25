package org.example.laberintos;
import java.util.List;

public class ManejadorGrafo {
    private Grafo grafo; //null hasta que cargue un laberinto
    private String laberinto; // texto original (para el dibujo)

    public void cargar(String laberinto){
        Grafo nuevo = construirGrafo(laberinto);
        this.grafo = nuevo;
        this.laberinto = laberinto;
    }

    public boolean hayLaberinto(){
        return grafo != null; }

    private Grafo construirGrafo(String laberinto){
        //TODO
        throw new UnsupportedOperationException("construirGrafo pendiente");
    }

    public List<String> resolverDFS(){
        return despliegaLaberinto(grafo.DFS(), grafo.getResultadoDFS());
    }

    public List<String> resolverBFS(){
        return despliegaLaberinto(grafo.BFS(), grafo.getResultadoBFS());
    }

    public List<String> resolverDijkstra(){
        return despliegaLaberinto(grafo.caminoMasCorto(), grafo.getResultadoDijkstra());
    }

    //String por paso y marca el camino final o te dice si no hay solucion
    public List<String> despliegaLaberinto(List<Vertice> ordenVisita, Resultado resultado){
        //TODO
        throw new UnsupportedOperationException("despliegaLaberinto pendiente");
    }

    public String mostrarTresSoluciones(){
        //TODO
        throw new UnsupportedOperationException("mostrarTresSoluciones pendiente");
    }
}
