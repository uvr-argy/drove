package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archivo {

    public String volcarArchivoDeTexto(String nombreArchivo) {
        try {
            Path ruta = Paths.get(nombreArchivo);
            return Files.readString(ruta);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return ""; 
        }
    }

   
    public void escribirArchivoDeTexto(String nombreArchivo, String contenido) {
        try {
            Path ruta = Paths.get(nombreArchivo);
            Files.writeString(ruta, contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public boolean comprobarQueUnArchivoExiste(String nombreArchivo) {
        Path ruta = Paths.get(nombreArchivo);
        return Files.exists(ruta);
    }
}

