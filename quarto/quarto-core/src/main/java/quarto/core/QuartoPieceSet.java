package quarto.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

import quarto.exception.IllegalQuartoPieceException;

/**
 * A Quarto pieces Set.
 *
 * @author Steve Cancès
 * @since 1.0
 */
public interface QuartoPieceSet extends Iterable<QuartoPiece> {

	/**
	 * Pull a QuartoPiece from the set.
	 *
	 * @param quartoPiece
	 */
	void pull(final QuartoPiece quartoPiece) throws IllegalQuartoPieceException;

	default Set<QuartoPiece> createNewFullSet() {
		return this.createNewFullSet(TreeSet::new);
	}

	default Set<QuartoPiece> createNewFullSet(final Supplier<Set<QuartoPiece>> setSupplier) {
		final Set<QuartoPiece> set = Objects.requireNonNull(setSupplier, "setSupplier must not be null").get();
		set.clear();

		final Set<Boolean> booleans = new HashSet<>();
		booleans.add(true);
		booleans.add(false);

		for (final boolean square : booleans) {
			for (final boolean tall : booleans) {
				for (final boolean black : booleans) {
					for (final boolean solidTop : booleans) {
						set.add(QuartoPiece.of(square, tall, black, solidTop));
					}
				}
			}
		}
		return set;
	}
}
