package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archivo {

    public String volcar(String nombreArchivo) {
        try {
            Path ruta = Paths.get(nombreArchivo);
            return Files.readString(ruta);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return ""; 
        }
    }

    public void escribir(String nombreArchivo, String contenido) {
        try {
            Path ruta = Paths.get(nombreArchivo);
            Files.writeString(ruta, contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public boolean existe(String nombreArchivo) {
        Path ruta = Paths.get(nombreArchivo);
        return Files.exists(ruta);
    }
}

