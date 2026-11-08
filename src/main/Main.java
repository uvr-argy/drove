package main;

import modelo.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static final int TRABAJOS_PENDIENTES = 20;
	public static final int HILOS_TRABAJADORES = 4;
	
	public static void main(String[] args) {
		//crear lista con todos los trabajos validados
		List<Trabajo> lista = ListaTrabajo.parseCsv(Archivo.leer("files/main.csv"));
		
		BlockingQueue<Trabajo> cola = new ArrayBlockingQueue<>(TRABAJOS_PENDIENTES); //lista limitada a 20 trabajos pendientes
		ExecutorService trabajadores = Executors.newFixedThreadPool(HILOS_TRABAJADORES); //uso de 4 hilos trabajadores
		
		Thread productor = new Thread(() -> HiloProductor.ejecutar(lista, cola));
	}
}
