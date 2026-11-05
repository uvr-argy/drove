package modelo;

public class Trabajo {
	private static final String[] OPERACIONES = {
			"ANALIZAR_TEXTO",
			"BUSCAR_TEXTO",
			"ESTADISTICAS_CSV"
	};
	
	private static final String[] ESTADOS = {
			"PENDIENTE",
			"EN_COLA",
			"EJECUTANDO",
			"COMPLETADO",
			"FALLIDO",
			"CANCELADO"
	};
	
	
	//propiedades
	private int id;
	private String operacion;
	private String archivo;
	private String estado;
	
	//constructores
	public Trabajo() { }
	public Trabajo(int id, String operacion, String archivo, String estado) {
		this.id = id;
		this.operacion = operacion;
		this.archivo = archivo;
		this.estado = estado;
	}
	
	//getters y setters
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOperacion() {
		return this.operacion;
	}
	
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public String getArchivo() {
		return this.archivo;
	}
	
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	//overrides
	@Override
	public String toString() {
		return "Trabajo [operacion=" + operacion + ", archivo=" + archivo + ", estado=" + estado + "]";
	}
	
	//validación de operaciones y estados
	public static boolean validarOperacion(String operacion) {
		return stringEnArray(operacion, OPERACIONES);
	}
	
	public boolean validarOperacion() {
		String operacion = this.getOperacion();
		return validarOperacion(operacion);
	}
	
	public static boolean validarEstado(String estado) {
		return stringEnArray(estado, ESTADOS);
	}
	
	public boolean validarEstado() {
		String estado = this.getEstado();
		return validarEstado(estado);
	}

	//funciones auxiliares
	private static boolean stringEnArray(String s, String[] a) {
		for (int i = 0; i < a.length; i++) {
			String iter = a[i];
			if (iter.equals(s))
				return true;
		}
		return false;
	}
}
