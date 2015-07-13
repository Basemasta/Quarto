package quarto.swing;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import quarto.Player;
import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCombination;
import quarto.core.QuartoPieceCoord;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class SwingPlayer implements Player {

	private final CountDownLatch countDownLatch;

	public SwingPlayer() {
		this.countDownLatch = new CountDownLatch(1);

		final Semaphore semaphore = new Semaphore(0, true);
	}

	@Override
	public void selectedQuartoPieceChanged(final QuartoPiece quartoPiece) {
		// TODO Auto-generated method stub

	}

	@Override
	public void quartoBoardChanged(final QuartoPiece[][] quartoBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endOfTheGame(final Collection<QuartoPieceCombination> quartoPieceCombinations) {
		// TODO Auto-generated method stub

	}

	@Override
	public QuartoPiece selectedQuartoPiece() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuartoPieceCoord selectedQuartoPieceCoord() {
		// TODO Auto-generated method stub
		return null;
	}

}
