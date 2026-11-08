package main;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import modelo.*;
import modelo.Trabajo.Estado;
import modelo.Trabajo.Operacion;

public class HiloProductor {
	public static void ejecutar(List<Trabajo> lista, BlockingQueue<Trabajo> cola) {
		try {
			for (int i = 0; i < lista.size(); i++) {
				Trabajo t = lista.get(i);
				if (t.getEstado() == Estado.CANCELADO)
					continue;
				t.setEstado(Trabajo.Estado.EN_COLA); //poner estado "en cola" para que los trabajadores no se detengan
				cola.put(t); //espera si la cola está llena
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
