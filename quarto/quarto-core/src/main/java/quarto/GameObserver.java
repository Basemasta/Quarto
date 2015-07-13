package quarto;

import java.util.Collection;

import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCombination;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public interface GameObserver {

	public void selectedQuartoPieceChanged(final QuartoPiece quartoPiece);

	public void quartoBoardChanged(final QuartoPiece[][] quartoBoard);

	public void endOfTheGame(final Collection<QuartoPieceCombination> quartoPieceCombinations);

}
