package quarto.core;

import java.util.Objects;
import java.util.StringJoiner;

import quarto.util.AttributsComparisonChain;

/**
 * A Quarto piece.
 *
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoPiece implements Comparable<QuartoPiece> {

	/** Square or Circular */
	private final boolean square;

	/** Tall or Short */
	private final boolean tall;

	/** Black or White */
	private final boolean black;

	/** Solid-top or Hollow-top */
	private final boolean solidTop;

	// public static boolean isBlack2(final QuartoPiece quartoPiece) {
	// return quartoPiece.isBlack();
	// }

	public static QuartoPiece of(final boolean square, final boolean tall, final boolean black, final boolean solidTop) {
		return new QuartoPiece(square, tall, black, solidTop);
	}

	/**
	 * Constructor of a {@code QuartoPiece}.
	 *
	 * @param square {@link #square}
	 * @param tall {@link #tall}
	 * @param black {@link #black}
	 * @param solidTop {@link #solidTop}
	 */
	public QuartoPiece(final boolean square, final boolean tall, final boolean black, final boolean solidTop) {
		super();
		this.square = square;
		this.tall = tall;
		this.black = black;
		this.solidTop = solidTop;
	}

	/**
	 * Get the attribute {@link #square}.
	 *
	 * @return The attribute square.
	 */
	public boolean isSquare() {
		return this.square;
	}

	/**
	 * Get the attribute {@link #tall}.
	 *
	 * @return The attribute tall.
	 */
	public boolean isTall() {
		return this.tall;
	}

	/**
	 * Get the attribute {@link #black}.
	 *
	 * @return The attribute black.
	 */
	public boolean isBlack() {
		return this.black;
	}

	/**
	 * Get the attribute {@link #solidTop}.
	 *
	 * @return The attribute solidTop.
	 */
	public boolean isSolidTop() {
		return this.solidTop;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.black, this.solidTop, this.square, this.tall);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof QuartoPiece) {
			final QuartoPiece other = (QuartoPiece) obj;
			return (this.square == other.square)
					&& (this.black == other.black)
					&& (this.solidTop == other.solidTop)
					&& (this.tall == other.tall);
		}
		return false;
	}

	@Override
	public String toString() {
		return new StringJoiner("][", "[", "]")
				.add(this.square ? "Square" : "Circular")
				.add(this.tall ? "Tall" : "Short")
				.add(this.black ? "Black" : "White")
				.add(this.solidTop ? "Solid-top" : "Hollow-top")
				.toString();
	}

	@Override
	public int compareTo(final QuartoPiece other) {
		return AttributsComparisonChain.on(this, other)
				.compareTrueFirst(QuartoPiece::isSquare)
				.compareTrueFirst(QuartoPiece::isTall)
				.compareTrueFirst(QuartoPiece::isBlack)
				.compareTrueFirst(QuartoPiece::isSolidTop)
				.result();
	}
}
