package modelo;

import excepciones.PersistenciaException;

public class TrabajoCsvMapper {
	public static String toCsv(Trabajo t) {
		String str = "";
		str += t.getId() + ";";
		str += t.getOperacion() + ";";
		str += t.getArchivo() + ";";
		str += t.getEstado();
		return str;
	}
	
	public static Trabajo toClass(String linea) throws PersistenciaException {
		String[] datos = linea.split(";", -1);
		
		int id;
		String operacion;
		String archivo;
		String estado;
		
		if (datos.length != 4) {
			throw new PersistenciaException();
		}
		
		//evitar validacion de datos excepto si no existen
		try {
			id = Integer.parseInt(datos[0]);
		} catch (NumberFormatException e) {
			throw new PersistenciaException();
		}
		
		//si la operacion no es valida, se tiene que mostrar en tiempo de ejecucion
		operacion = datos[1].trim();
		if (operacion.length() == 0)
			throw new PersistenciaException();
		
		archivo = datos[2].trim();
		if (archivo.length() == 0)
			throw new PersistenciaException();
		
		//en el caso de los estados no es necesario mostrar los errores en tiempo de ejecucion
		estado = datos[3].trim();
		if (Trabajo.validarEstado(estado) == false)
			throw new PersistenciaException();
		
		Trabajo t = new Trabajo(id, operacion, archivo, estado);
		return t;
	}
}
