package quarto;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public interface Game {

	public void start();

	public boolean isStarted();

	public void stop();

	public void registerObserver(final GameObserver gameObserver);
}
