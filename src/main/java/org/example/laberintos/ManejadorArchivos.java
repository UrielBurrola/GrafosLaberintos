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
        if(archivoLaberinto == null) return null;
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
            }
            if(!validacionES(contenido))
                throw new Exception("Tipo de entrada o salida Invalidos.");
        }
        laberinto = contenido;
        return true;
    }

    private boolean caracterInvalido(String linea){
        Pattern caracteres = Pattern.compile("[^+\\-| #ES]$");
        return caracteres.matcher(linea).find();
    }

    private boolean validacionES(String laberinto) {
        String[] filas = laberinto.split("\\R");
        int cantE = 0;
        int cantS = 0;
        int espaciosBorde = 0;

        for(int y = 0; y < filas.length; y++){
            String fila = filas[y];
            boolean esBorde = (y == 0 || y == filas.length-1);
            for(int x = 0; x < fila.length(); x++){
                char c = Character.toUpperCase(fila.charAt(x));
                boolean esBordeColumna = (x == 0 || x == fila.length() - 1);

                if(c == 'E') cantE++;
                else if(c == 'S') cantS++;
                else if(c == ' ' && (esBorde || esBordeColumna))
                    espaciosBorde++;
                if (cantE > 1 || cantS > 1 || espaciosBorde > 2 || ((cantE > 0 || cantS > 0) && espaciosBorde > 0)) {
                    return false;
                }
            }
        }
        return (cantE == 1 && cantS == 1) || (espaciosBorde == 2);
    }

}
