package quarto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import quarto.core.QuartoBoard;
import quarto.core.QuartoBoardImpl;
import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCoord;
import quarto.core.QuartoPieceSet;
import quarto.core.QuartoPieceSetImpl;
import quarto.exception.IllegalQuartoPieceCoordsException;
import quarto.exception.IllegalQuartoPieceException;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class GameImpl implements Game {

	private static final Log LOGGER = LogFactory.getLog(GameImpl.class);

	private final QuartoBoard quartoBoard;

	private final QuartoPieceSet quartoPieceSet;

	private final Map<GameObserver, ExecutorService> gameObservers;

	private final ThreadFactory threadFactory;

	private final Player player1;

	private final Player player2;

	private Player currentPlayer;

	private QuartoPiece selectedQuartoPiece;

	private ExecutorService gameExecutorService;

	public GameImpl(final Player player1, final Player player2) {
		this.quartoBoard = new QuartoBoardImpl();
		this.quartoPieceSet = QuartoPieceSetImpl.newFullSet();
		this.gameObservers = new ConcurrentHashMap<>();
		this.threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("game-observer-thread-%s")
				.build();
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player2;
		this.selectedQuartoPiece = null;
		this.gameExecutorService = null;

		this.registerObserver(player1);
		this.registerObserver(player2);
	}

	@Override
	public synchronized void start() {
		if (!this.isStarted()) {
			this.gameExecutorService = Executors.newSingleThreadExecutor();
			this.gameExecutorService.execute(() -> GameImpl.this.runGame());
		}
	}

	public synchronized void stop() {
		if (this.isStarted()) {
			this.gameExecutorService.shutdownNow();
			this.gameExecutorService = null;
		}
	}

	public synchronized boolean isStarted() {
		return this.gameExecutorService != null;
	}

	private void runGame() {
		this.notifyObserversQuartoBoardChanged();
		this.notifyObserversSelectedQuartoPieceChanged();

		while (!this.quartoBoard.isBoardOver()) {
			this.currentPlayerSelectedQuartoPiece();
			this.nextPlayer();
			this.currentPlayerSelectedQuartoPieceCoord();
		}
		this.notifyObserversEndOfTheGame();
	}

	private void currentPlayerSelectedQuartoPieceCoord() {
		QuartoPieceCoord playerSelectedQuartoPieceCoord = null;
		while (playerSelectedQuartoPieceCoord == null) {
			playerSelectedQuartoPieceCoord = this.currentPlayer.selectedQuartoPieceCoord();
			try {
				this.quartoBoard.putPiece(playerSelectedQuartoPieceCoord, this.selectedQuartoPiece);
			} catch (final IllegalQuartoPieceCoordsException e) {
				playerSelectedQuartoPieceCoord = null;
				GameImpl.LOGGER.warn("Error while a quarto piece coords selection", e);
			}
		}
		this.notifyObserversQuartoBoardChanged();
	}

	private void currentPlayerSelectedQuartoPiece() {
		QuartoPiece playerSelectedQuartoPiece = null;
		while (playerSelectedQuartoPiece == null) {
			playerSelectedQuartoPiece = this.currentPlayer.selectedQuartoPiece();
			try {
				this.quartoPieceSet.pull(playerSelectedQuartoPiece);
			} catch (final IllegalQuartoPieceException e) {
				playerSelectedQuartoPiece = null;
				GameImpl.LOGGER.warn("Error while a quarto piece selection", e);
			}
		}
		this.selectedQuartoPiece = playerSelectedQuartoPiece;
		this.notifyObserversSelectedQuartoPieceChanged();
	}

	@Override
	public synchronized void registerObserver(final GameObserver gameObserver) {
		if (!this.gameObservers.containsKey(gameObserver)) {
			this.gameObservers.put(gameObserver, Executors.newSingleThreadExecutor(this.threadFactory));
		}
	}

	private void notifyObserversEndOfTheGame() {
		this.notifyObservers(gameObserver -> gameObserver.endOfTheGame(this.quartoBoard.getWinningCombinations()));
	}

	private void notifyObserversQuartoBoardChanged() {
		this.notifyObservers(gameObserver -> gameObserver.quartoBoardChanged(this.quartoBoard.getBoard()));
	}

	private void notifyObserversSelectedQuartoPieceChanged() {
		this.notifyObservers(gameObserver -> gameObserver.selectedQuartoPieceChanged(this.selectedQuartoPiece));
	}

	private void notifyObservers(final Consumer<GameObserver> consumer) {
		this.gameObservers
				.keySet()
				.stream()
				.forEach(observer -> this.notifyObserver(observer, consumer));
	}

	private void notifyObserver(final GameObserver gameObserver, final Consumer<GameObserver> consumer) {
		this.gameObservers.get(gameObserver).execute(() -> consumer.accept(gameObserver));
	}

	private GameImpl nextPlayer() {
		this.currentPlayer = (this.currentPlayer == this.player1 ? this.player2 : this.player1);
		return this;
	}
}
