package modelo;

import main.*;
import java.util.ArrayList;
import java.util.List;

import excepciones.PersistenciaException;

public class TrabajoRepository {
	
	public List<Trabajo> cargar() {
		
		List<Trabajo> lista = new ArrayList<Trabajo>();
		String volcado = Archivo.leer("main.csv");
		String[] lineas = volcado.split("\n", -1);
		
		for (int i = 0; i < lineas.length; i++) {
			String linea = lineas[i];
			Trabajo t;
			
			try {
				t = TrabajoCsvMapper.toClass(linea);
			} catch (PersistenciaException e) {
				continue;
			}
			
			lista.add(t);
		}
		return lista;
	}
}
