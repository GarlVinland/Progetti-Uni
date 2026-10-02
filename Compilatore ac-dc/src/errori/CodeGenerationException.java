package errori;

public class CodeGenerationException extends Exception{
	
	public CodeGenerationException(String message, Throwable c) {
		super(message, c);
	}
	
	public CodeGenerationException(String message) {
		super(message);
	}
	
}
