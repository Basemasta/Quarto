package quarto.core;

import java.util.Collection;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public interface QuartoPiecePredicate {

	public static Predicate<QuartoPiece> IS_SQUARE = piece -> (piece != null) && piece.isSquare();

	public static Predicate<QuartoPiece> IS_CIRCULAR = piece -> (piece != null) && !piece.isSquare();

	public static Predicate<QuartoPiece> IS_TALL = piece -> (piece != null) && piece.isTall();

	public static Predicate<QuartoPiece> IS_SHORT = piece -> (piece != null) && !piece.isTall();

	public static Predicate<QuartoPiece> IS_BLACK = piece -> (piece != null) && piece.isBlack();

	public static Predicate<QuartoPiece> IS_WHITE = piece -> (piece != null) && !piece.isBlack();

	public static Predicate<QuartoPiece> IS_SOLID_TOP = piece -> (piece != null) && piece.isSolidTop();

	public static Predicate<QuartoPiece> IS_HOLLOW_TOP = piece -> (piece != null) && !piece.isSolidTop();

	public static Collection<Predicate<QuartoPiece>> ALL = ImmutableList.of(
			QuartoPiecePredicate.IS_SQUARE,
			QuartoPiecePredicate.IS_CIRCULAR,
			QuartoPiecePredicate.IS_TALL,
			QuartoPiecePredicate.IS_SHORT,
			QuartoPiecePredicate.IS_BLACK,
			QuartoPiecePredicate.IS_WHITE,
			QuartoPiecePredicate.IS_SOLID_TOP,
			QuartoPiecePredicate.IS_HOLLOW_TOP);

}
