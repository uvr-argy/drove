package main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import modelo.*;
import modelo.Trabajo.Estado;

public class HiloTrabajador {
	public static void ejecutar(Trabajo t, ExecutorService trabajadores) {
		t.setEstado(Estado.EJECUTANDO);
		long tiempoInicio = System.nanoTime();
		switch (t.getOperacion()) {
			case ANALIZAR_TEXTO:
				t.ejecutar();
				break;
			case BUSCAR_TEXTO:
				Callable<Boolean> tarea = () -> {
					return t.ejecutar();
				};
				Future<Boolean> future = trabajadores.submit(tarea);
				try {
					boolean resultado = future.get();					
				} catch (Exception e) { 
					long tiempoFinal = System.nanoTime();
					t.setEstado(Trabajo.Estado.FALLIDO);
					t.setDuracion((tiempoFinal - tiempoInicio) / 1000000);
				}
				//al hacer todo la clase trabajo, la variable de resultado no se utiliza
				break;
			case ESTADISTICAS_CSV:
				String volcado = Archivo.leer(t.getParametros()[0]);
				String codificado = Base64Wrapper.codificar(volcado);
				Process proceso = Subproceso.lanzar("subprocesos.EstadisticasCsv", codificado);
				try {
					proceso.waitFor();					
				} catch (InterruptedException e) {
					t.setEstado(Estado.FALLIDO);
					t.setResultado("Error en el proceso.");
				}
				String resultado = Subproceso.leerResultado(proceso);
				t.setResultado(resultado);
				long tiempoFinal = System.nanoTime();
				t.setDuracion((tiempoFinal - tiempoInicio) / 1000000);
				break;
		}
		if (t.getEstado() == Estado.EJECUTANDO)
			t.setEstado(Estado.COMPLETADO);
	}
}
