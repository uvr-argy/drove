package main;

import java.util.List;
import java.util.ArrayList;

public class AnalizarDoubles {
	public static double[] parseDoubles(String[] str) {
		double[] res;
		List<Double> lista = new ArrayList<Double>();
		for (int i = 0; i < str.length; i++) {
			try {
				lista.add(Double.parseDouble(str[i]));
			} catch (NumberFormatException e) {
				//System.err.println("El valor '" + str[i] + "' no se ha podido parsear correctamente.");
			}
		}
		//pasar contenido a array tradicional
		res = new double[lista.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = lista.get(i);
		}
		return res;
	}
	
	public static int contarNoValidos(String[] str) {
		int recuento = str.length;
		recuento -= contarValidos(str);
		return recuento;
	}
	
	public static double promedio(double[] d) {
		double promedio = suma(d);
		promedio /= d.length;
		return promedio;
	}
	
	public static double suma(double[] d) {
		double res = 0;
		for (int i = 0; i < d.length; i++) {
			res += d[i];
		}
		return res;
	}
	
	public static double cantidad(double[] d) {
		return d.length;
	}
	
	public static double min(double[] d) {
		double min = 0;
		for (int i = 0; i < d.length; i++) {
			double act = d[i];
			if (act < min)
				min = act;
		}
		return min;
	}
	
	public static double max(double[] d) {
		double max = 0;
		for (int i = 0; i < d.length; i++) {
			double act = d[i];
			if (act > max)
				max = act;
		}
		return max;
	}
	
	public static int contarValidos(String[] str) {
		int recuento = 0;
		for (int i = 0; i < str.length; i++) {
			String texto = str[i];
			if (valido(texto))
				recuento++;
		}
		return recuento;
	}
	
	private static boolean valido(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
