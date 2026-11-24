package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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

    @FXML
    protected void onCargarArchivoClick() {
        Stage stage = (Stage) btnCargarArchivo.getScene().getWindow();
        try {
            ma.cargarLaberinto(stage);
            laberintoCargado = true;
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo cargar el laberinto" + e.getMessage());
        }
    }

    private boolean hayLaberinto(){
        if(!laberintoCargado){
            mostrarAlerta(Alert.AlertType.WARNING, "Sin laberinto", "Debes cargar un archivo");
            return false;
        }
        return true;
    }

    //metodo auxiliar
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
