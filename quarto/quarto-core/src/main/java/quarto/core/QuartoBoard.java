package quarto.core;

import java.util.Collection;

import quarto.exception.IllegalQuartoPieceCoordsException;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public interface QuartoBoard {

	QuartoPiece[][] getBoard();

	QuartoBoard putPiece(
			final QuartoPieceCoord coord,
			final QuartoPiece piece) throws IllegalQuartoPieceCoordsException;

	boolean isFull();

	boolean isWinningBoard();

	boolean isBoardOver();

	Collection<QuartoPieceCombination> getWinningCombinations();

	QuartoBoard cloneBoard();
}
