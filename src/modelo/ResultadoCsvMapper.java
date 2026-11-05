package modelo;

import excepciones.PersistenciaException;

public class ResultadoCsvMapper {
	public static String toCsv(Resultado r) {
		String str = "";
		
		str += r.getId() + ";";
		str += r.getOperacion() + ";";
		str += r.getEstado() + ";";
		str += r.getDuracion() + ";";
		str += r.getMensaje();
		
		return str;
	}
	
	public static Resultado toObject(String linea) throws PersistenciaException {
		//Este metodo existe de forma simbolica.
		//no se prevé usarla por ahora, pero
		//si hay que desarrollarla en algun momento,
		//aqui esta
		/*
		Resultado r;
		String[] datos = linea.split(";", -1);
		
		int id;
		String operacion;
		String estado;
		double duracion;
		String mensaje;
		*/
		return null;
	}
}
