package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainController {
    ManejadorArchivos ma = new ManejadorArchivos();

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

    //Todo: Reformatear para conectar al boton indicado
    @FXML
    protected void cargarLaberinto() {
        Stage stage;
        if (btnCargarArchivo.getScene() != null) {
            stage = (Stage) btnCargarArchivo.getScene().getWindow();
            if (stage != null)
                try {
                    ma.cargarLaberinto(stage);
                } catch (Exception e) {
                    //Todo: Mostrar mensajes de error
                }

        }
    }
}
