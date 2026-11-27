# Laberintos: DFS, BFS y Dijkstra
Programa en JavaFX que carga un laberinto desde un archivo de texto, lo
representa como grafo y lo resuelve con DFS, BFS y Dijkstra, mostrando la
exploración paso a paso y comparando los tres resultados.

## Cómo ejecutarlo

```
./mvnw clean javafx:run
```

Requiere JDK 17 o superior (JavaFX 21).

## Uso

- **Cargar Archivo:** elige un `.txt` con el laberinto. Si el archivo no es
  válido se muestra el motivo y se conserva el laberinto anterior.
- **DFS / BFS / Dijkstra:** muestran la exploración paso a paso. El deslizador
  "Pausa por paso" (5 a 300 ms) controla la velocidad, también mientras corre.
- **Todos:** muestra las tres soluciones, sin animación.

Símbolos del paso a paso: `E` entrada, `S` salida, `@` vértice actual,
`.` visitado, `*` camino final.

## Formato del archivo

- Cada línea es un renglón y todos tienen la misma longitud. No hay líneas
  vacías ni tabulaciones.
- Caracteres válidos: `+ - |` (paredes), espacio (transitable), `#`
  (transitable, se trata como espacio), `E` (entrada) y `S` (salida).
- Entrada y salida, de una de estas dos formas:
    1. Exactamente una `E` y una `S`.
    2. Sin marcas, exactamente dos posiciones transitables en el borde: la
       primera en orden de lectura (arriba a abajo, izquierda a derecha) es la
       entrada y la segunda es la salida.
- Un laberinto sin solución es válido: se explora y se avisa que no hay camino.

Ejemplo: `laberinto_prueba.txt`.

## Diseño

- Grafo no dirigido, cada movimiento cuesta 1. Cada posición transitable es un
  vértice (id = fila * columnas + columna).
- Adyacencia con listas ligadas: `Hashtable<Integer, LinkedList<Arcos>>`.
  Clases: `Grafo`, `Vertices`, `Arcos`, `Vertice` y `Resultado`.
- DFS y BFS exploran en el orden arriba, derecha, abajo, izquierda. Dijkstra
  desempata por el identificador menor, así el resultado es reproducible.
- El detalle está en el comentario al inicio de `Grafo.java`.
- 

## Responsabilidades de cada integrante

El trabajo fue colaborativo: ambos participamos en el código y en la
finalización del proyecto.

- **Uriel:** inicio del proyecto y esqueleto de las clases, carga y validación
  del archivo del laberinto, algoritmo de Dijkstra y la opción "Todos"
  (mostrar las tres soluciones).
- **Yael:** interfaz gráfica, estructura del grafo (`Vertice`, `Vertices`,
  `Arcos`, `Grafo`), BFS y DFS, el paso a paso, mejoras en la validación del
  archivo, el tema visual, la limpieza y los arreglos del código.

## Declaración de uso de IA

Se utilizó Claude (modelo Sonnet 5.5, de Anthropic) como apoyo durante el
desarrollo: para entender cómo construir BFS, revisar y probar el código,
proponer la validación del archivo, el paso a paso con su animación, la documentación. También ayudó a probar Dijkstra y sugirió el criterio
de desempate por identificador. Al final se usó para la limpieza del proyecto y
para redactar este README.