package org.example.laberintos;
import java.util.Collection;
import java.util.List;
import java.util.Hashtable;

public class Vertices {
    private final Hashtable<Integer, Vertice> tabla = new Hashtable<>();

    public void agregar(Vertice v) { tabla.put(v.getId(), v); }
    public Vertice obtener(int id) { return tabla.get(id); }
    public boolean contiene(int id) { return tabla.containsKey(id); }
    public int size() { return tabla.size(); }

    public Collection<Vertice> todos() { return tabla.values(); }
}
