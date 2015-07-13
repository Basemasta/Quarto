package quarto.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.Assert;

import quarto.exception.IllegalQuartoPieceCoordsException;

import com.google.common.collect.ImmutableList;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoBoardImpl implements QuartoBoard {

	private static QuartoPieceCombination ROW0 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 0), QuartoPieceCoord.of(0, 1), QuartoPieceCoord.of(0, 2), QuartoPieceCoord.of(0, 3));
	private static QuartoPieceCombination ROW1 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(1, 0), QuartoPieceCoord.of(1, 1), QuartoPieceCoord.of(1, 2), QuartoPieceCoord.of(1, 3));
	private static QuartoPieceCombination ROW2 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(2, 0), QuartoPieceCoord.of(2, 1), QuartoPieceCoord.of(2, 2), QuartoPieceCoord.of(2, 3));
	private static QuartoPieceCombination ROW3 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(3, 0), QuartoPieceCoord.of(3, 1), QuartoPieceCoord.of(3, 2), QuartoPieceCoord.of(3, 3));

	private static QuartoPieceCombination COL0 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 0),
			QuartoPieceCoord.of(1, 0),
			QuartoPieceCoord.of(2, 0),
			QuartoPieceCoord.of(3, 0));
	private static QuartoPieceCombination COL1 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 1),
			QuartoPieceCoord.of(1, 1),
			QuartoPieceCoord.of(2, 1),
			QuartoPieceCoord.of(3, 1));
	private static QuartoPieceCombination COL2 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 2),
			QuartoPieceCoord.of(1, 2),
			QuartoPieceCoord.of(2, 2),
			QuartoPieceCoord.of(3, 2));
	private static QuartoPieceCombination COL3 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 3),
			QuartoPieceCoord.of(1, 3),
			QuartoPieceCoord.of(2, 3),
			QuartoPieceCoord.of(3, 3));

	private static QuartoPieceCombination DIA0 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 0), QuartoPieceCoord.of(1, 1),
			QuartoPieceCoord.of(2, 2), QuartoPieceCoord.of(3, 3));
	private static QuartoPieceCombination DIA1 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(3, 0), QuartoPieceCoord.of(2, 1),
			QuartoPieceCoord.of(1, 2), QuartoPieceCoord.of(0, 3));

	private static QuartoPieceCombination SQU0 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 0), QuartoPieceCoord.of(0, 1),
			QuartoPieceCoord.of(1, 0), QuartoPieceCoord.of(1, 1));
	private static QuartoPieceCombination SQU1 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 2), QuartoPieceCoord.of(0, 3),
			QuartoPieceCoord.of(1, 2), QuartoPieceCoord.of(1, 3));
	private static QuartoPieceCombination SQU2 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(2, 0), QuartoPieceCoord.of(2, 1),
			QuartoPieceCoord.of(3, 0), QuartoPieceCoord.of(3, 1));
	private static QuartoPieceCombination SQU3 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(2, 2), QuartoPieceCoord.of(2, 3),
			QuartoPieceCoord.of(3, 2), QuartoPieceCoord.of(3, 3));

	private static QuartoPieceCombination SQU4 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(0, 1), QuartoPieceCoord.of(0, 2),
			QuartoPieceCoord.of(1, 1), QuartoPieceCoord.of(1, 2));
	private static QuartoPieceCombination SQU5 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(1, 2), QuartoPieceCoord.of(1, 3),
			QuartoPieceCoord.of(2, 2), QuartoPieceCoord.of(2, 3));
	private static QuartoPieceCombination SQU6 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(2, 1), QuartoPieceCoord.of(2, 2),
			QuartoPieceCoord.of(3, 1), QuartoPieceCoord.of(3, 2));
	private static QuartoPieceCombination SQU7 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(1, 0), QuartoPieceCoord.of(1, 1),
			QuartoPieceCoord.of(2, 0), QuartoPieceCoord.of(2, 1));

	private static QuartoPieceCombination SQU8 = QuartoPieceCombination.of(
			QuartoPieceCoord.of(1, 1), QuartoPieceCoord.of(1, 2),
			QuartoPieceCoord.of(2, 1), QuartoPieceCoord.of(2, 2));

	private static List<QuartoPieceCombination> COMBINATIONS = ImmutableList.<QuartoPieceCombination> builder()
			.add(QuartoBoardImpl.ROW0).add(QuartoBoardImpl.ROW1).add(QuartoBoardImpl.ROW2).add(QuartoBoardImpl.ROW3)
			.add(QuartoBoardImpl.COL0).add(QuartoBoardImpl.COL1).add(QuartoBoardImpl.COL2).add(QuartoBoardImpl.COL3)
			.add(QuartoBoardImpl.DIA0).add(QuartoBoardImpl.DIA1)
			.add(QuartoBoardImpl.SQU0).add(QuartoBoardImpl.SQU1).add(QuartoBoardImpl.SQU2).add(QuartoBoardImpl.SQU3)
			.add(QuartoBoardImpl.SQU4).add(QuartoBoardImpl.SQU5).add(QuartoBoardImpl.SQU6).add(QuartoBoardImpl.SQU7)
			.add(QuartoBoardImpl.SQU8)
			.build();

	QuartoPiece[][] board;

	public QuartoBoardImpl() {
		this.board = new QuartoPiece[4][4];
	}

	public QuartoBoardImpl(final QuartoPiece[][] board) {
		this.board = board;
	}

	private static Predicate<Set<QuartoPiece>> HAVE_A_COMMON_ATTRIBUTE = pieces ->
			QuartoPiecePredicate.ALL
					.stream()
					.anyMatch(predicate -> pieces.stream().allMatch(predicate));

	@Override
	public QuartoPiece[][] getBoard() {
		return Arrays.stream(this.board)
				.map(row -> row.clone())
				.toArray(length -> new QuartoPiece[length][]);
	}

	private Stream<QuartoPiece> getBoardPiecesAsStream() {
		return Arrays
				.stream(this.board)
				.flatMap(row -> Arrays.stream(row));
	}

	@Override
	public QuartoBoard putPiece(
			final QuartoPieceCoord coord,
			final QuartoPiece piece) throws IllegalQuartoPieceCoordsException {
		final int x = coord.getX();
		final int y = coord.getY();
		try {
			Assert.isTrue((x >= 0) && (x < 4), "x must be between 0 (included) and 4 (excluded)");
			Assert.isTrue((y >= 0) && (y < 4), "y must be between 0 (included) and 4 (excluded)");
			Assert.notNull(piece, "piece must not be null");
			Assert.isNull(this.board[x][y], String.format("there is already a piece in [%s][%s]", x, y));
			Assert.isTrue(!this.contains(piece), "the board already contains this piece");
		} catch (final IllegalArgumentException exception) {
			throw new IllegalQuartoPieceCoordsException(exception);
		}
		this.board[x][y] = piece;
		return this;
	}

	@Override
	public boolean isFull() {
		return !this
				.getBoardPiecesAsStream()
				.filter(Objects::isNull)
				.map(x -> true)
				.findFirst()
				.isPresent();
	}

	@Override
	public boolean isWinningBoard() {
		return QuartoBoardImpl.COMBINATIONS
				.stream()
				.filter(this::winningCombination)
				.findFirst()
				.isPresent();
	}

	@Override
	public boolean isBoardOver() {
		return this.isFull() || this.isWinningBoard();
	}

	@Override
	public Collection<QuartoPieceCombination> getWinningCombinations() {
		return QuartoBoardImpl.COMBINATIONS
				.stream()
				.filter(this::winningCombination)
				.collect(Collectors.toList());
	}

	@Override
	public QuartoBoard cloneBoard() {
		final QuartoBoardImpl clone = new QuartoBoardImpl();
		clone.board = this.getBoard();
		return clone;
	}

	private boolean contains(final QuartoPiece piece) {
		return this
				.getBoardPiecesAsStream()
				.filter(p -> piece.equals(p))
				.findFirst()
				.isPresent();
	}

	private boolean winningCombination(final QuartoPieceCombination combination) {
		final Set<QuartoPiece> combinationPieces = combination
				.coordsStream()
				.map(coord -> this.board[coord.getX()][coord.getY()])
				.collect(Collectors.toSet());
		return QuartoBoardImpl.HAVE_A_COMMON_ATTRIBUTE.test(combinationPieces);
	}

}
