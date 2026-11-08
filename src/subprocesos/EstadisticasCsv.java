package subprocesos;

import main.*;

public class EstadisticasCsv {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println(""); //error
			return;
		}
		String volcado = Base64Wrapper.decodificar(args[0]);
		String[] registros = volcado.split("[\\r\\n;]+", -1);
		double[] serializado = AnalizarDoubles.parseDoubles(registros);
		
		//hacer recuento de caracteristicas
		int recuento = serializado.length;
		double suma = AnalizarDoubles.suma(serializado);
		double media = AnalizarDoubles.promedio(serializado);
		double minimo = AnalizarDoubles.min(serializado);
		double maximo = AnalizarDoubles.max(serializado);
		int descartes = AnalizarDoubles.contarNoValidos(registros);
		//return;/*
		
		System.out.println("recuento=" + recuento + "/suma=" + suma + "/media=" + media + "/minimo=" + minimo + "/maximo=" + maximo + "/descartes=" + descartes);
		//*/
	}
}
