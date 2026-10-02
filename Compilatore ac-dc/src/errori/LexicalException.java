package errori;

public class LexicalException extends Exception {
	
	
	public LexicalException(String message, Throwable c) {
		super(message, c);
	}
	
	public LexicalException(String message) {
		super(message);
	}
	

}
