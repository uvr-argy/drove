package main;

import java.util.List;
import java.util.ArrayList;

import modelo.*;
import excepciones.*;

//clase para operar con List<Trabajo>. más que nada, funciones auxiliares
public class ListaTrabajo {
	public static List<Trabajo> parseCsv(String csv) {
		String[] lineas = csv.split("\\R", -1);
		List<Trabajo> lista = new ArrayList<Trabajo>();
		
		if (lineas.length == 0) //si no hay lineas
			return lista;
		
		for (int i = 0; i < lineas.length; i++) {
			Trabajo t;
			String linea = lineas[i];
			if (linea.length() < 2)
				continue;
			
			try {
				t = TrabajoCsvMapper.toClass(linea);
			} catch (CsvException e) {
				System.out.println("Linea invalida: " + linea + " / Motivo: " + e.getMessage());
				continue;
			}
			
			if (idExiste(t.getId(), lista)) {
				System.out.println("Trabajo no válido. ID '" + t.getId() + "' repetido.");
				continue;
			}
			t.setEstado(Trabajo.Estado.PENDIENTE);
			lista.add(t);
		}
		return lista;
	}
	
	private static boolean idExiste(int id, List<Trabajo> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId() == id)
				return true;
		}
		return false;
	}
}
