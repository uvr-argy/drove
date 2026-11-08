package main;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import modelo.*;
import modelo.Trabajo.Estado;

public class HiloProductor {
	public static void ejecutar(List<Trabajo> lista, BlockingQueue<Trabajo> cola) {
		//System.out.println("Ejecutando hilo productor...");
		try {
			for (int i = 0; i < lista.size(); i++) {
				Trabajo t = lista.get(i);
				if (t.getEstado() == Estado.CANCELADO)
					continue;
				cola.put(t); //espera si la cola está llena
				t.setEstado(Trabajo.Estado.EN_COLA); //poner estado "en cola" para que los trabajadores no se detengan
				//System.out.println("Añadido a cola tarea " + t.getId());
			}
			Trabajo dummy = new Trabajo();
			dummy.setEstado(Estado.COMPLETADO);
			for (int i = 0; i < Main.HILOS_TRABAJADORES; i++) {
				cola.put(dummy);
			}
		} catch (InterruptedException e) {
			//System.out.println("Error :(");
			Thread.currentThread().interrupt();
		}
		//System.out.println("Hilo productor finalizado!");
	}
}
