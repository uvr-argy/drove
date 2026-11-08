package excepciones;

public class TrabajoInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TrabajoInvalidoException() {
		
	}
	
	public TrabajoInvalidoException(String mensaje) {
		super(mensaje);
	}
	
	public TrabajoInvalidoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
