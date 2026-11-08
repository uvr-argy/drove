package modelo;

import excepciones.CsvException;

public class TrabajoCsvMapper {
	public static Trabajo toClass(String linea) throws CsvException {
		String[] datos = linea.split(";", -1);
		
		int id;
		Trabajo.Operacion operacion;
		String[] parametros;
		
		//comprobar datos suficientes
		if (datos.length < 3)
			throw new CsvException("Campos insuficientes");
		
		//parsear id
		try {
			id = Integer.parseInt(datos[0]);
		} catch (NumberFormatException e) {
			throw new CsvException("Identificador invalido", e.getCause());
		}
		
		//obtener operacion
		try {
			operacion = Trabajo.Operacion.valueOf(datos[1].trim());			
		} catch (IllegalArgumentException e) {
			throw new CsvException("Operación invalida");
		}
		
		//obtener parametros
		parametros = new String[datos.length - 2];
		for (int i = 0; i < parametros.length; i++) {
			parametros[i] = datos[i + 2];
		}
		
		Trabajo t = new Trabajo(id, operacion, parametros);
		return t;
	}
	
	public static String toCsv(Trabajo t) {
		String str = "";
		
		str += t.getId() + ";";
		str += t.getOperacion() + ";";
		str += t.getEstado() + ";";
		str += t.getResultado() + ";";
		str += t.getDuracion() + ";";
		
		String[] parametros = t.getParametros();
		for (int i = 0; i < parametros.length; i++) {
			str += parametros[i] + ";";
		}
	
		return str;
	}
}
