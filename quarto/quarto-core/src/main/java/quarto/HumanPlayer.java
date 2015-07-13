package quarto;

import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCoord;

/**
 * A human player.
 * 
 * @author Steve Cancès
 * @since 1.0
 */
public interface HumanPlayer extends Player {

	/**
	 * Select a Quarto piece.
	 *
	 * @param selectedQuartoPiece the selected Quarto piece
	 */
	void selectQuartoPiece(final QuartoPiece selectedQuartoPiece);

	/**
	 * Select a Quarto piece coord.
	 *
	 * @param selectedQuartoPieceCoord the selected Quarto piece coord
	 */
	void selectQuartoPieceCoord(final QuartoPieceCoord selectedQuartoPieceCoord);
}
