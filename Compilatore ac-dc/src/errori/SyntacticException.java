package errori;

public class SyntacticException extends Exception{
	
	public SyntacticException(String message, Throwable c) {
		super(message, c);
	}
	
	public SyntacticException(String message) {
		super(message);
	}
}
