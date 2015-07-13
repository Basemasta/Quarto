package quarto.core;

import java.util.Objects;

import org.springframework.util.Assert;

/**
 * A Quarto piece coords.
 *
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoPieceCoord {

	/** The x of the {@link QuartoPieceCoord} */
	private final int x;

	/** The y of the {@link QuartoPieceCoord} */
	private final int y;

	/**
	 * Get a {@link QuartoPieceCoord}
	 *
	 * @param x {@link #x}
	 * @param y {@link #y}
	 * @return the {@link QuartoPieceCoord}
	 */
	public static QuartoPieceCoord of(final int x, final int y) {
		return new QuartoPieceCoord(x, y);
	}

	/**
	 * Private constructor of a {@code Coord}.
	 *
	 * @param x {@link #x}
	 * @param y {@link #y}
	 */
	private QuartoPieceCoord(final int x, final int y) {
		Assert.isTrue((x >= 0) && (x < 4), "x must be between 0 (included) and 4 (excluded)");
		Assert.isTrue((y >= 0) && (y < 4), "y must be between 0 (included) and 4 (excluded)");
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the attribute {@link #x}.
	 *
	 * @return The attribute x.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Get the attribute {@link #y}.
	 *
	 * @return The attribute y.
	 */
	public int getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}

	@Override
	public boolean equals(final Object obj) {

		if (obj instanceof QuartoPieceCoord) {
			final QuartoPieceCoord other = (QuartoPieceCoord) obj;
			return (other.x == this.x) && (other.y == this.y);
		}

		return false;

	}

}
