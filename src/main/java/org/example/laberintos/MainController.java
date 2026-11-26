package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Slider;

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
    @FXML
    private Slider sliderPausa;
    @FXML
    private Label lblPausa;

    //Avisa si intentan correr un algoritmo sin haber cargado un archivo
    private boolean laberintoCargado = false;
    private String laberinto;
    private static final int MS_POR_PASO = 30;
    private Timeline animacion;

    @FXML
    private void initialize() {
        sliderPausa.valueProperty().addListener((obs, anterior, nuevo) -> {
            lblPausa.setText(Math.round(nuevo.doubleValue()) + " ms");
            if (animacion != null) {
                animacion.setRate(MS_POR_PASO / nuevo.doubleValue());
            }
        });
    }

    @FXML
    protected void onDFSClick() {
        if(sinLaberinto()) return;
        try {
            List<String> pasos = manejador.resolverDFS();
            animarSolucion("DFS", pasos);
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al resolver", e.getMessage());
        }

    }

    @FXML
    protected void onBFSClick() {
        if(sinLaberinto()) return;
        try {
            List<String> pasos = manejador.resolverBFS();
            animarSolucion("BFS", pasos);
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al resolver", e.getMessage());
        }
    }

    @FXML
    protected void onDijkstraClick() {
        if(sinLaberinto()) return;
        try {
            List<String> pasos = manejador.resolverDijkstra();
            animarSolucion("Dijkstra", pasos);
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al resolver", e.getMessage());
        }
    }

    @FXML
    protected void onTodosClick(){
        if(sinLaberinto()) return;
        detenerAnimacion();
        try {
            List<String> pasosB = manejador.resolverBFS();
            List<String> pasosD = manejador.resolverDFS();
            List<String> pasosDi = manejador.resolverDijkstra();
            laberinto = "";
            cargarSolucion("BFS", pasosB, true);
            cargarSolucion("DFS", pasosD, true);
            cargarSolucion("Dijkstra", pasosDi, true);
            mostrarArte();
        } catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error al resolver", e.getMessage());
        }
    }

    private void cargarSolucion(String nombre, List<String> pasos, boolean todos){
        if(!todos)
            laberinto = "";
        laberinto = laberinto.concat("Solucion de laberinto mediante " + nombre + ":\n");
        laberinto = laberinto.concat(pasos.get(pasos.size() - 1) + "\n");
        areaCodigo.setText(laberinto);
    }

    private void animarSolucion(String nombre, List<String> pasos) {
        detenerAnimacion();
        String titulo = "Solucion de laberinto mediante " + nombre + ":\n";

        animacion = new Timeline();
        for (int i = 0; i < pasos.size(); i++) {
            String paso = pasos.get(i);
            animacion.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * MS_POR_PASO),
                            e -> areaCodigo.setText(titulo + paso)));
        }
        animacion.setRate(MS_POR_PASO / sliderPausa.getValue());
        animacion.setOnFinished(e -> mostrarArte());
        animacion.play();
    }

    private void detenerAnimacion() {
        if (animacion != null) {
            animacion.stop();
            animacion = null;
        }
    }

    //Este metodo es solo para que se vea bonito, detalle totalmente opcional
    private void mostrarArte() {
        String arte = cargarArte();
        if (arte == null) return;

        Label figura = new Label(arte);
        figura.setFont(Font.font("Monospaced", 10));

        Label mensaje = new Label("Bien hecho!");
        mensaje.setFont(Font.font("System", FontWeight.BOLD, 20));
        VBox contenido = new VBox(10, figura, mensaje);
        contenido.setAlignment(Pos.CENTER);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Fin del recorrido");
        alerta.setHeaderText(null);
        alerta.setGraphic(null);
        alerta.getDialogPane().setContent(contenido);
        alerta.getDialogPane().getStylesheets().add(MainController.class.getResource("estilo.css").toExternalForm());
        alerta.show();
    }

    private String cargarArte() {
        try (InputStream in = MainController.class.getResourceAsStream("arte.txt")) {
            if (in == null) return null;
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            return null;
        }
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
            detenerAnimacion();
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
