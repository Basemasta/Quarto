package quarto.exception;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class IllegalQuartoPieceException extends Exception {

	/** Serial UID */
	private static final long serialVersionUID = 3139562832286900252L;

	public IllegalQuartoPieceException() {
		super();
	}

	public IllegalQuartoPieceException(final String message) {
		super(message);
	}

	public IllegalQuartoPieceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public IllegalQuartoPieceException(final Throwable cause) {
		super(cause);
	}

}
