package org.example.laberintos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManejadorArchivos {
    File archivoLaberinto;
    String laberinto;

    public String cargarLaberinto(Stage stage) throws Exception{
        cargarArchivo(stage);

        if(validacionLaberinto()){
            return laberinto;
        }
        return null;
    }

    private void cargarArchivo(Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Archivo del Laberinto");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        archivoLaberinto = fc.showOpenDialog(stage);
    }

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
                if (linea.isEmpty())
                    throw new Exception("Formato invalido.");
                if(caracterInvalido(linea))
                    throw new Exception("Caracteres invalidos dentro del archivo");

                contenido = contenido.concat(linea + "\n");

                if(!validacionES(contenido))
                    throw new Exception();
            }
        }
        laberinto = contenido;
        return true;
    }

    private boolean caracterInvalido(String linea){
        Pattern caracteres = Pattern.compile("[^+\\-| #ES]");
        return caracteres.matcher(linea).find();
    }

    private boolean validacionES(String laberinto) {
        boolean banderaE = false;
        boolean banderaS = false;
        int espacioES = 0;
        String[] filas = laberinto.split("\\R");
        if(filas[0].contains(" "))
            espacioES++;
        if(filas[filas.length-1].contains(" "))
            espacioES++;
        for(String f : filas){
            if(f.toUpperCase(Locale.ROOT).contains("E"))
                banderaE = true;
            if(f.toUpperCase(Locale.ROOT).contains("S"))
                banderaS = true;
            if(f.charAt(0) == ' ')
                espacioES++;
            if(f.charAt(f.length()-1) == ' ')
                espacioES++;
            if((espacioES == 2) && !(banderaE || banderaS))
                return true;
            if((banderaE && banderaS) && (espacioES == 0)) {
                return true;
            }
        }
        return false;
    }
}
