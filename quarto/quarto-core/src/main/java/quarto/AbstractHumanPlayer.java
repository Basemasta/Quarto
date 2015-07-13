package quarto;

import java.util.concurrent.Semaphore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCoord;

/**
 * An abstract {@link HumanPlayer}.
 * 
 * @author Steve Cancès
 * @since 1.0
 */
public abstract class AbstractHumanPlayer implements HumanPlayer {

	/** Logger */
	private static final Log LOGGER = LogFactory.getLog(GameImpl.class);

	private final Semaphore selectedQuartoPieceSemaphore;

	private QuartoPiece selectedQuartoPiece;

	private final Semaphore selectedQuartoPieceCoordSemaphore;

	private QuartoPieceCoord selectedQuartoPieceCoord;

	public AbstractHumanPlayer() {
		this.selectedQuartoPieceSemaphore = new Semaphore(0, true);
		this.selectedQuartoPieceCoordSemaphore = new Semaphore(0, true);
	}

	@Override
	public QuartoPiece selectedQuartoPiece() {
		QuartoPiece result = null;
		try {
			this.selectedQuartoPieceSemaphore.acquire();
			result = this.selectedQuartoPiece;
			this.selectedQuartoPiece = null;
			this.selectedQuartoPieceSemaphore.release();
		} catch (final InterruptedException e) {
			AbstractHumanPlayer.LOGGER.warn(
					"Interruption occured while waiting human player move (selectedQuartoPiece)", e);
		}
		return result;
	}

	@Override
	public QuartoPieceCoord selectedQuartoPieceCoord() {
		QuartoPieceCoord result = null;
		try {
			this.selectedQuartoPieceCoordSemaphore.acquire();
			result = this.selectedQuartoPieceCoord;
			this.selectedQuartoPieceCoord = null;
			this.selectedQuartoPieceCoordSemaphore.release();
		} catch (final InterruptedException e) {
			AbstractHumanPlayer.LOGGER.warn(
					"Interruption occured while waiting human player move (selectedQuartoPieceCoord)", e);
		}
		return result;
	}

	@Override
	public void selectQuartoPiece(final QuartoPiece selectedQuartoPiece) {
		try {
			if (!this.selectedQuartoPieceSemaphore.tryAcquire()) {
				this.selectedQuartoPiece = selectedQuartoPiece;
				this.selectedQuartoPieceSemaphore.release();
				this.selectedQuartoPieceSemaphore.acquire();
			}
		} catch (final InterruptedException e) {
			AbstractHumanPlayer.LOGGER.warn(
					"Interruption occured while human player move (selectQuartoPiece)", e);
		}
	}

	@Override
	public void selectQuartoPieceCoord(final QuartoPieceCoord selectedQuartoPieceCoord) {
		try {
			if (!this.selectedQuartoPieceCoordSemaphore.tryAcquire()) {
				this.selectedQuartoPieceCoord = selectedQuartoPieceCoord;
				this.selectedQuartoPieceCoordSemaphore.release();
				this.selectedQuartoPieceCoordSemaphore.acquire();
			}
		} catch (final InterruptedException e) {
			AbstractHumanPlayer.LOGGER.warn(
					"Interruption occured while human player move (selectQuartoPieceCoord)", e);
		}
	}

}
