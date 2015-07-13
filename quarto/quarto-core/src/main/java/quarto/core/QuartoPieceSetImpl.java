package quarto.core;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.Assert;

import quarto.exception.IllegalQuartoPieceException;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoPieceSetImpl implements QuartoPieceSet {

	/** The set */
	private final Set<QuartoPiece> set;

	public static QuartoPieceSetImpl newFullSet() {
		return new QuartoPieceSetImpl();
	}

	private QuartoPieceSetImpl() {
		this.set = this.createNewFullSet();
	}

	@Override
	public void pull(final QuartoPiece quartoPiece) throws IllegalQuartoPieceException {
		try {
			Assert.isTrue(
					this.set.remove(Objects.requireNonNull(quartoPiece, "quartoPiece must not be null")),
					"this piece is not in the set");
		} catch (final IllegalArgumentException | NullPointerException exception) {
			throw new IllegalQuartoPieceException(exception);
		}
	}

	@Override
	public Iterator<QuartoPiece> iterator() {
		return this.set.iterator();
	}

}
