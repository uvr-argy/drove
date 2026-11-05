package excepciones;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistenciaException() {
		super("Error en la persistencia de datos.");
	}
}
