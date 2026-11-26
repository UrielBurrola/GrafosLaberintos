package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.List;

public class MainController {
    ManejadorArchivos ma = new ManejadorArchivos();

    private final ManejadorGrafo manejador = new ManejadorGrafo();

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

    //Avisa si intentan correr un algoritmo sin haber cargado un archivo
    private boolean laberintoCargado = false;
    private String laberinto;

    @FXML
    protected void onDFSClick() {
        if(sinLaberinto()) return;
        // TODO: ejecutar DFS
    }

    @FXML
    protected void onBFSClick() {
        if(sinLaberinto()) return;
        try {
            List<String> pasos = manejador.resolverBFS();
            areaCodigo.setText(pasos.get(pasos.size() - 1));
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al resolver", e.getMessage());
        }
    }

    @FXML
    protected void onDijkstraClick() {
        if(sinLaberinto()) return;
        // TODO: ejecutar Dijkstra
    }

    private boolean sinLaberinto(){
        if(!laberintoCargado){
            mostrarAlerta(Alert.AlertType.WARNING, "Sin laberinto.", "Debes cargar un archivo");
            return true;
        }
        return false;
    }

    //metodo auxiliar
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    protected void cargarLaberinto() {
        try {
            String texto = ma.cargarLaberinto(getStage());
            if (texto == null) return;
            manejador.cargar(texto);
            laberintoCargado = true;
            areaCodigo.setText(texto);
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar el archivo", e.getMessage());
        }
    }

    private Stage getStage(){
        Stage stage;
        if (btnCargarArchivo.getScene() != null) {
            stage = (Stage) btnCargarArchivo.getScene().getWindow();
            return stage;
        }
        return null;
    }
}
