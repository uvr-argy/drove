package main;

//import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Process p = Subproceso.lanzar("subprocesos.EstadisticasCsv");
		System.out.println(Subproceso.leerResultado(p));
	}
}
