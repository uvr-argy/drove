package main;

import modelo.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
	public static final int TRABAJOS_PENDIENTES = 20;
	public static final int HILOS_TRABAJADORES = 4;
	public static final int PROCESOS_ACTIVOS = 2;
	
	public static void main(String[] args) {
		String archivo;
		while (true) {
			System.out.println("Introduce el nombre del archivo de entrada: ");
			Scanner sc = new Scanner(System.in);
			archivo = sc.next().trim();
			if (Archivo.existe(archivo)) {
				sc.close();
				break;
			}
			System.out.println("El archivo no existe. Por favor, introduce el nombre de un archivo accesible.");
			
		}
		
		//crear lista con todos los trabajos validados
		List<Trabajo> lista = ListaTrabajo.parseCsv(Archivo.leer(archivo));
		
		BlockingQueue<Trabajo> cola = new ArrayBlockingQueue<>(TRABAJOS_PENDIENTES); //lista limitada a 20 trabajos pendientes
		ExecutorService trabajadores = Executors.newFixedThreadPool(HILOS_TRABAJADORES); //uso de 4 hilos trabajadores
		Semaphore semaphore = new Semaphore(PROCESOS_ACTIVOS);
		
		Thread productor = new Thread(() -> {
			HiloProductor.ejecutar(lista, cola); //ir añadiendo constantemente elementos a la cola
		});
		productor.start();
		for (int i = 0; i < HILOS_TRABAJADORES; i++) {
			trabajadores.submit(() -> {
				try {
					//System.out.println("Hilo ejecutor iniciado.");
					while (true) {
						Trabajo t = cola.take();
						
						if (t.getEstado() != Trabajo.Estado.EN_COLA) //si no está en cola, ya ha terminado
							break;
						HiloTrabajador.ejecutar(t, trabajadores, semaphore);
					}
					//System.out.println("Hilo ejecutor finalizado.");
				} catch (InterruptedException e) {
					//e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			});
		}
		try {			
			productor.join();
			trabajadores.shutdown();
			if (!trabajadores.awaitTermination(5, TimeUnit.SECONDS)) {
				trabajadores.shutdownNow();
			}
		} catch (InterruptedException e) {
			
		}
		
		String informe = ListaTrabajo.generarInforme(lista);
		Archivo.escribir("informe.csv", informe);
		System.out.println("Informe generado!");
	}
}
