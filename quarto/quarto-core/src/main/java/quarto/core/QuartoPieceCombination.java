package quarto.core;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

/**
 * Combination of 4 {@link QuartoPieceCoord}s which represents a row or a column or a square...
 *
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoPieceCombination implements Iterable<QuartoPieceCoord> {

	/** The coords */
	private final List<QuartoPieceCoord> coords;

	/**
	 * Create and return a {@link QuartoPieceCombination} composed of 4 {@link QuartoPieceCoord}s.
	 *
	 * @param coord1 a coord
	 * @param coord2 a coord
	 * @param coord3 a coord
	 * @param coord4 a coord
	 * @return the combination
	 */
	public static QuartoPieceCombination of(
			final QuartoPieceCoord coord1,
			final QuartoPieceCoord coord2,
			final QuartoPieceCoord coord3,
			final QuartoPieceCoord coord4) {
		return new QuartoPieceCombination(coord1, coord2, coord3, coord4);
	}

	/**
	 * Private constructor of a {@code QuartoPieceCombination}. Use
	 * {@link #of(QuartoPieceCoord, QuartoPieceCoord, QuartoPieceCoord, QuartoPieceCoord)} instead.
	 */
	private QuartoPieceCombination(
			final QuartoPieceCoord coord1,
			final QuartoPieceCoord coord2,
			final QuartoPieceCoord coord3,
			final QuartoPieceCoord coord4) {
		this.coords = ImmutableList.of(coord1, coord2, coord3, coord4);
	}

	@Override
	public Iterator<QuartoPieceCoord> iterator() {
		return this.coords.iterator();
	}

	/**
	 * Get the attribute {@link #coords}.
	 *
	 * @return The attribute coords.
	 */
	public List<QuartoPieceCoord> getCoords() {
		return this.coords;
	}

	/**
	 * Return new a {@link #coords} stream.
	 *
	 * @return the {@link #coords} stream
	 */
	public Stream<QuartoPieceCoord> coordsStream() {
		return this.coords.stream();
	}

}
