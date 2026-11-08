package modelo;

import java.util.Arrays;

import main.*;
import excepciones.*;

public class Trabajo {
	//enums
	public enum Estado {
		PENDIENTE,
		EN_COLA,
		EJECUTANDO,
		COMPLETADO,
		FALLIDO,
		CANCELADO
	}
	
	public enum Operacion {
		ANALIZAR_TEXTO,
		BUSCAR_TEXTO,
		ESTADISTICAS_CSV
	}
	
	//propiedades
	private int id;
	private Operacion operacion;
	private Estado estado;
	private String resultado;
	private String[] parametros;
	private double duracion;
	
	//constructores
	public Trabajo() {
		
	}
	
	public Trabajo(int id, Operacion operacion, String[] parametros) {
		//constructor pensado para parsear CSV's
		this.id = id;
		this.operacion = operacion;
		this.parametros = parametros;
	}
	
	public Trabajo(int id, Operacion operacion, Estado estado, String resultado, String[] parametros) {
		this(id, operacion, parametros);
		this.estado = estado;
		this.resultado = resultado;
	}

	//getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Operacion getOperacion() {
		return operacion;
	}
	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String[] getParametros() {
		return parametros;
	}
	public void setParametros(String[] parametros) {
		this.parametros = parametros;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	//overrides
	@Override
	public String toString() {
		return "Trabajo [id=" + id + ", operacion=" + operacion + ", estado=" + estado + ", resultado=" + resultado
				+ ", parametros=" + Arrays.toString(parametros) + ", duracion=" + duracion + "]";
	}

	//ejecucion de trabajos
	public boolean ejecutar() {
		boolean ok = true;
		long tiempoInicio = System.nanoTime();
		
		String paramFijo = this.parametros[0];
		String[] paramVariable = new String[this.parametros.length - 1];
		for (int i = 0; i < paramVariable.length; i++) {
			paramVariable[i] = this.parametros[i + 1];
		}
		try {
			switch (this.operacion) {
				case ANALIZAR_TEXTO:
					this.analizarTexto(paramFijo);
					break;
				case BUSCAR_TEXTO:
					this.buscarTexto(paramFijo, paramVariable);
					break;
				default:
					break;
			}
			
		} catch (OperacionException e) {
			this.setEstado(Estado.FALLIDO);
			ok = false;
		}
		
		long tiempoFinal = System.nanoTime();
		this.setDuracion(((double) (tiempoFinal - tiempoInicio)) / 1000000); //convertir a milisegundos
		return ok;
	}
	
	private void analizarTexto(String archivo) throws OperacionException {
		String volcado = Archivo.leer(archivo);
		if (volcado == null) {
			this.resultado = "Error de lectura de archivo";
			throw new OperacionException();
		}
		int lineas = volcado.split("\\R", -1).length; //saltos de linea y similares
		int palabras = volcado.split("\s", -1).length; //cualquier tipo de espacioo 
		int caracteres = volcado.length(); //caracteres
		this.resultado = "lineas=" + lineas + "/palabras=" + palabras + "/caracteres=" + caracteres;
	}
	
	private void buscarTexto(String texto, String[] archivos) throws OperacionException{
		if (archivos.length == 0) {
			this.resultado = "Parametros insuficientes";
			throw new OperacionException();
		}
		String salida = "";
		for (int i = 0; i < archivos.length; i++) {
			String archivo = archivos[i];
			salida += archivo + "=";
			
			String volcado = Archivo.leer(archivo);
			if (volcado == null) {
				salida += "error/";
				continue;
			}
			int recuento = volcado.split(texto, -1).length;
			salida += recuento + "/";
		}
		this.resultado = salida;
	}
	
	public static void cancelar(Trabajo t) {
		if (t.estado == Estado.EN_COLA)
			t.setEstado(Estado.CANCELADO);
	}
}
