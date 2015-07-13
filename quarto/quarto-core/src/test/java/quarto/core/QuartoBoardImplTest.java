package quarto.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import quarto.core.QuartoBoard;
import quarto.core.QuartoBoardImpl;
import quarto.core.QuartoPiece;
import quarto.core.QuartoPieceCombination;
import quarto.core.QuartoPieceCoord;
import quarto.core.QuartoPieceSetImpl;

import com.google.common.collect.Lists;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoBoardImplTest {

	private QuartoBoard board;

	@Before
	public void before() {
		this.board = new QuartoBoardImpl();
	}

	@Test
	public void testIsFull() throws Exception {
		Assert.assertFalse(this.board.isFull());
		final Iterator<QuartoPiece> setIterator = QuartoPieceSetImpl.newFullSet().iterator();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.board.putPiece(
						QuartoPieceCoord.of(i, j),
						setIterator.next());
			}
		}
		Assert.assertTrue(this.board.isFull());
		Assert.assertTrue(this.board.isBoardOver());
	}

	@Test
	public void testCloneBoard() throws Exception {
		final List<QuartoPiece> list = Lists.newArrayList(QuartoPieceSetImpl.newFullSet());
		Collections.shuffle(list);
		this.board.putPiece(QuartoPieceCoord.of(0, 0), list.get(0));
		this.board.putPiece(QuartoPieceCoord.of(1, 1), list.get(1));

		final QuartoBoard clone = this.board.cloneBoard();

		Assert.assertTrue(this.board != clone);
		Assert.assertNotNull(this.board.getBoard()[0][0]);
		Assert.assertTrue(this.board.getBoard()[0][0] == clone.getBoard()[0][0]);
		Assert.assertNotNull(this.board.getBoard()[1][1]);
		Assert.assertTrue(this.board.getBoard()[1][1] == clone.getBoard()[1][1]);
	}

	@Test
	public void testIsWinningBoard() throws Exception {
		this.board
				.putPiece(QuartoPieceCoord.of(0, 0), QuartoPiece.of(true, true, true, true))
				.putPiece(QuartoPieceCoord.of(0, 1), QuartoPiece.of(true, false, true, true))
				.putPiece(QuartoPieceCoord.of(0, 2), QuartoPiece.of(true, true, false, true));
		Assert.assertFalse(this.board.isWinningBoard());
		Assert.assertFalse(this.board.isBoardOver());

		this.board.putPiece(QuartoPieceCoord.of(0, 3), QuartoPiece.of(true, true, true, false));
		Assert.assertTrue(this.board.isWinningBoard());
		Assert.assertTrue(this.board.isBoardOver());

		this.board = new QuartoBoardImpl();
		this.board
				.putPiece(QuartoPieceCoord.of(0, 0), QuartoPiece.of(false, true, true, true))
				.putPiece(QuartoPieceCoord.of(0, 1), QuartoPiece.of(true, false, true, true))
				.putPiece(QuartoPieceCoord.of(0, 2), QuartoPiece.of(true, true, false, true))
				.putPiece(QuartoPieceCoord.of(0, 3), QuartoPiece.of(true, true, true, false));
		Assert.assertFalse(this.board.isWinningBoard());
		Assert.assertFalse(this.board.isBoardOver());
	}

	@Test
	public void testWinningCombinations() throws Exception {
		Collection<QuartoPieceCombination> winningCombinations = this.board.getWinningCombinations();
		Assert.assertNotNull(winningCombinations);
		Assert.assertEquals(0, winningCombinations.size());

		this.board
				.putPiece(QuartoPieceCoord.of(0, 0), QuartoPiece.of(true, true, true, true))
				.putPiece(QuartoPieceCoord.of(0, 1), QuartoPiece.of(true, false, true, true))
				.putPiece(QuartoPieceCoord.of(0, 2), QuartoPiece.of(true, true, false, true))
				.putPiece(QuartoPieceCoord.of(0, 3), QuartoPiece.of(true, true, true, false));
		winningCombinations = this.board.getWinningCombinations();

		Assert.assertNotNull(winningCombinations);
		Assert.assertEquals(1, winningCombinations.size());
		final QuartoPieceCombination combination = winningCombinations.iterator().next();
		Assert.assertTrue(combination.getCoords().contains(QuartoPieceCoord.of(0, 0)));
		Assert.assertTrue(combination.getCoords().contains(QuartoPieceCoord.of(0, 1)));
		Assert.assertTrue(combination.getCoords().contains(QuartoPieceCoord.of(0, 2)));
		Assert.assertTrue(combination.getCoords().contains(QuartoPieceCoord.of(0, 3)));

	}

}
