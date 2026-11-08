package main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import modelo.*;
import modelo.Trabajo.Estado;

public class HiloTrabajador {
	public static void ejecutar(Trabajo t, ExecutorService trabajadores, Semaphore semaphore) {
		t.setEstado(Estado.EJECUTANDO);
		System.out.println("[EX] Ejecutando trabajo (" + t.getId() + ")...");
		long tiempoInicio = System.nanoTime();
		switch (t.getOperacion()) {
			case ANALIZAR_TEXTO:
				System.out.println("[EX - " + t.getId() + "] Haciendo analisis de texto...");
				t.ejecutar();
				break;
			case BUSCAR_TEXTO:
				System.out.println("[EX - " + t.getId() + "] Creando callable...");
				Callable<Boolean> tarea = () -> {
					return t.ejecutar();
				};
				ExecutorService executor = Executors.newSingleThreadExecutor();
				Future<Boolean> future = executor.submit(tarea);
				try {
					System.out.println("[EX - " + t.getId() + "] Obteniendo callable...");
					future.get();
				} catch (Exception e) { 
					long tiempoFinal = System.nanoTime();
					t.setEstado(Trabajo.Estado.FALLIDO);
					t.setDuracion((tiempoFinal - tiempoInicio) / 1000000);
					System.out.println("[EX - " + t.getId() + "] Callable terminado con error...");
				}
				System.out.println("[EX - " + t.getId() + "] Callable terminado!");
				//al hacer todo la clase trabajo, la variable de resultado no se utiliza
				break;
			case ESTADISTICAS_CSV:
				System.out.println("[EX - " + t.getId() + "] Iniciando proceso...");
				String volcado = Archivo.leer(t.getParametros()[0]);
				if (volcado == null) {
					System.out.println("[EX - " + t.getId() + "] Archivo no encontrado...");
					t.setEstado(Estado.FALLIDO);
					t.setResultado("Archivo inexistente");
				} else {
					String codificado = Base64Wrapper.codificar(volcado);
					try {
						semaphore.acquire();
						Process proceso = Subproceso.lanzar("subprocesos.EstadisticasCsv", codificado);
						System.out.println("[EX - " + t.getId() + "] Esperando al proceso...");
						proceso.waitFor();					
						String resultado = Subproceso.leerResultado(proceso);
						t.setResultado(resultado);
					} catch (InterruptedException e) {
						System.out.println("[EX - " + t.getId() + "] Proceso terminado con error");
						t.setEstado(Estado.FALLIDO);
						t.setResultado("Error en el proceso.");
					}
					semaphore.release();
				}
				long tiempoFinal = System.nanoTime();
				t.setDuracion((tiempoFinal - tiempoInicio) / 1000000);
				System.out.println("[EX - " + t.getId() + "] Seccion de proceso finalizada!");
				break;
		}
		if (t.getEstado() == Estado.EJECUTANDO)
			t.setEstado(Estado.COMPLETADO);
		System.out.println("[EX] Trabajo (" + t.getId() + ") completado correctamente!");
	}
}
