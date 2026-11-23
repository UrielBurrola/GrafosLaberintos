package org.example.laberintos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainController {
    ManejadorArchivos ma = new ManejadorArchivos();

    @FXML
    private Label welcomeText;

    //Todo: Reformatear para conectar al boton indicado
    @FXML
    protected void onHelloButtonClick() {
        Stage stage;
        if(welcomeText.getScene() != null){
            stage = (Stage)welcomeText.getScene().getWindow();
            if(stage != null)
                try {
                    ma.cargarLaberinto(stage);
                } catch (Exception e){
                    //Todo: Mostrar mensajes de error
                }

        }
    }
}
