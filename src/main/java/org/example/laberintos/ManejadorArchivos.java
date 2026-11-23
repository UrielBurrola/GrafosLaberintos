package org.example.laberintos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class ManejadorArchivos {
    File archivoLaberinto;

    public void cargarLaberinto(Stage stage) throws Exception{
        cargarArchivo(stage);
        if(!archivoLaberinto.isFile())
            throw new FileNotFoundException();

        if(validacionLaberinto()){

        }

    }

    private void cargarArchivo(Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Archivo del Laberinto");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        archivoLaberinto = fc.showOpenDialog(stage);
    }

    private boolean validacionLaberinto() throws Exception{
        return true;
    }
}
