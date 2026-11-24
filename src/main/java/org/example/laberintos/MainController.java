package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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

    private final ManejadorArchivos ma = new ManejadorArchivos();

    //Avisa si intentan correr un algoritmo sin haber cargado un archivo
    private boolean laberintoCargado = false;

    @FXML
    protected void onDFSClick() {
        if(!hayLaberinto()) return;
        // TODO: ejecutar DFS
    }

    @FXML
    protected void onBFSClick() {
        if(!hayLaberinto()) return;
        // TODO: ejecutar BFS
    }

    @FXML
    protected void onDijkstraClick() {
        if(!hayLaberinto()) return;
        // TODO: ejecutar Dijkstra
    }

    //metodo auxiliar
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();

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
