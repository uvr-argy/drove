package excepciones;

public class OperacionException extends Exception {
	private static final long serialVersionUID = 1L;

	public OperacionException() {
		
	}
	
	public OperacionException(String mensaje) {
		super(mensaje);
	}
	
	public OperacionException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
