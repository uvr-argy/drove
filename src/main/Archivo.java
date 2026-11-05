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


    public String[] convertirCsvAArray(String nombreArchivo, String separador) {
        if (!comprobarQueUnArchivoExiste(nombreArchivo)) {
            System.err.println("El archivo CSV no existe en la ruta indicada.");
            return new String[0];
        }

        String contenidoCsv = volcarArchivoDeTexto(nombreArchivo);

        String textoPlano = contenidoCsv.replace("\r\n", separador).replace("\n", separador);

        return textoPlano.split(java.util.regex.Pattern.quote(separador));
    }

    
    public void calcularEstadisticasCsv(String nombreArchivo) {
        String[] valoresString = convertirCsvAArray(nombreArchivo, ";");

        if (valoresString.length == 0) {
            return; 
        }

        double[] valoresDouble = AnalizarDoubles.parseDoubles(valoresString);

        System.out.println("ESTADÍSTICAS DEL ARCHIVO: " + nombreArchivo);
        System.out.println("Cantidad de registros: " + (int) AnalizarDoubles.cantidad(valoresDouble));
        System.out.println("Registros no válidos: " + AnalizarDoubles.contarNoValidos(valoresString));
        System.out.println("Suma total: " + AnalizarDoubles.suma(valoresDouble));
        System.out.println("Promedio: " + AnalizarDoubles.promedio(valoresDouble));
        System.out.println("Mínimo: " + AnalizarDoubles.min(valoresDouble));
        System.out.println("Máximo: " + AnalizarDoubles.max(valoresDouble));
    }
}

