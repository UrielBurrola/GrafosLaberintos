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

    private static final String CARACTERES_VALIDOS = "+-| #ES";

    private boolean validacionLaberinto() throws Exception{
        if(!archivoLaberinto.isFile())
            throw new FileNotFoundException("El archivo no existe.");
        if(!archivoLaberinto.canRead())
            throw new Exception("No es posible leer el archivo.");
        if(archivoLaberinto.length() == 0)
            throw new Exception("El archivo esta vacio.");

        String contenido = "";
        int numFila = 0;
        int columnas = -1;
        try(Scanner sc = new Scanner(archivoLaberinto)){
            while (sc.hasNextLine()){
                String linea = sc.nextLine();
                numFila++;
                if (linea.isEmpty())
                    throw new Exception("Formato invalido: la fila " + numFila + " esta vacia.");
                validarCaracteres(linea, numFila);
                if (columnas == -1)
                    columnas = linea.length();
                else if (linea.length() != columnas)
                    throw new Exception("Las filas no tienen la misma longitud: la fila " + numFila
                            + " tiene " + linea.length() + " columnas y la primera tiene " + columnas + ".");

                contenido = contenido.concat(linea + "\n");
            }
            validacionES(contenido);
        }
        laberinto = contenido;
        return true;
    }

    private void validarCaracteres(String linea, int numFila) throws Exception{
        for(int j = 0; j < linea.length(); j++){
            if(CARACTERES_VALIDOS.indexOf(linea.charAt(j)) < 0)
                throw new Exception("Caracter invalido en la fila " + numFila + ", columna " + (j + 1) + ".");
        }
    }

    private void validacionES(String laberinto) throws Exception{
        String[] filas = laberinto.split("\\R");
        int cantE = 0;
        int cantS = 0;
        int aberturasBorde = 0;

        for(int y = 0; y < filas.length; y++){
            String fila = filas[y];
            for(int x = 0; x < fila.length(); x++){
                char c = fila.charAt(x);
                boolean esBorde = (y == 0 || y == filas.length - 1 || x == 0 || x == fila.length() - 1);

                if(c == 'E') cantE++;
                else if(c == 'S') cantS++;
                else if((c == ' ' || c == '#') && esBorde)
                    aberturasBorde++;
            }
        }
        if(cantE > 0 || cantS > 0){
            if(cantE != 1 || cantS != 1)
                throw new Exception("Debe haber exactamente una E y una S (hay " + cantE + " E y " + cantS + " S).");
        } else if(aberturasBorde != 2){
            throw new Exception("Sin marcas E/S debe haber exactamente dos posiciones transitables en el borde (hay " + aberturasBorde + ").");
        }
    }

}
