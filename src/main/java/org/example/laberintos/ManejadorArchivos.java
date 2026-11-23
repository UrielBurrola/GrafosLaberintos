package org.example.laberintos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManejadorArchivos {
    File archivoLaberinto;

    public void cargarLaberinto(Stage stage) throws Exception{
        cargarArchivo(stage);;

        if(validacionLaberinto()){
            //todo: al pasar las validaciones se tiene que cargar el grafo
        }

    }

    private void cargarArchivo(Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Archivo del Laberinto");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        archivoLaberinto = fc.showOpenDialog(stage);
    }

    //Todo: terminar validaciones de formato para el laberinto
    private boolean validacionLaberinto() throws Exception{
        if(!archivoLaberinto.isFile())
            throw new FileNotFoundException();
        if(!archivoLaberinto.canRead())
            throw new Exception("No es poible leer el archivo.");
        if(archivoLaberinto.length() == 0)
            throw new Exception("El archivo esta vacio.");

        String contenido = "";
        try(Scanner sc = new Scanner(archivoLaberinto)){
            while (sc.hasNextLine()){
                String linea = sc.nextLine();
                if(caracterInvalido(linea))
                    throw new Exception("Caracteres invalidos dentro del archivo");
                //Todo: Continuar validaciones
            }
        }
        return true;
    }

    private boolean caracterInvalido(String linea){
        Pattern caracteres = Pattern.compile("[^+\\-| #ES]");
        return caracteres.matcher(linea).find();
    }
}
