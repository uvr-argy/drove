package excepciones;

public class CsvException extends Exception {
	private static final long serialVersionUID = 1L;

	public CsvException() {
		
	}
	
	public CsvException(String message) {
		super(message);
	}
	
	public CsvException(String message, Throwable causa) {
		super(message, causa);
	}
}
