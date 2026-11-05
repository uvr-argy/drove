package modelo;

public class Resultado {
	//propiedades
	private int id;
	private String operacion;
	private String estado;
	private double duracion; //(segundos)
	private String mensaje;
	
	//constructores
	public Resultado() { }
	public Resultado(int id, String operacion, String estado, double duracion, String mensaje) {
		this.id = id;
		this.operacion = operacion;
		this.estado = estado;
		this.duracion = duracion;
		this.mensaje = mensaje;
	}
	
	//getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	//overrides
	@Override
	public String toString() {
		return "Resultado [id=" + id + ", operacion=" + operacion + ", estado=" + estado + ", duracion=" + duracion
				+ ", mensaje=" + mensaje + "]";
	}
}
