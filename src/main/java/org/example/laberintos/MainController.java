package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    private Button btnDFS;
    @FXML
    private Button btnBFS;
    @FXML
    private Button btnDijkstra;
    @FXML
    private Button btnCargarArchivo;
    @FXML
    private TextArea areaCodigo;

    @FXML
    protected void onDFSClick() {
        // TODO: ejecutar DFS
    }

    @FXML
    protected void onBFSClick() {
        // TODO: ejecutar BFS
    }

    @FXML
    protected void onDijkstraClick() {
        // TODO: ejecutar Dijkstra
    }

    @FXML
    protected void onCargarArchivoClick() {
        // TODO: cargar el archivo del laberinto
    }
}
