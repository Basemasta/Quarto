package quarto.exception;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class IllegalQuartoPieceCoordsException extends Exception {

	/** Serial UID */
	private static final long serialVersionUID = 8533725591857571313L;

	public IllegalQuartoPieceCoordsException() {
		super();
	}

	public IllegalQuartoPieceCoordsException(final String message) {
		super(message);
	}

	public IllegalQuartoPieceCoordsException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public IllegalQuartoPieceCoordsException(final Throwable cause) {
		super(cause);
	}

}
