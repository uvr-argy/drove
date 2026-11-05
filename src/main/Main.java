package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Process p = Subproceso.lanzar("subprocesos.EstadisticasCsv");
		
		Subproceso.esperar(p);
		
		String s = Subproceso.leerResultado(p);
		System.out.println(s);
	}
}
