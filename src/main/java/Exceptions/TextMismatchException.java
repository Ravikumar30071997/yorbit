package Exceptions;

public class TextMismatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public TextMismatchException() {
		super("text mismatch");
	}
}
