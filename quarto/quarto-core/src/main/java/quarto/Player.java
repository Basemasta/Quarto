package quarto;

import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCoord;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public interface Player extends GameObserver {

	public QuartoPiece selectedQuartoPiece();

	public QuartoPieceCoord selectedQuartoPieceCoord();
}
