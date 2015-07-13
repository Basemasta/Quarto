package quarto.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import quarto.core.QuartoPiece;
import quarto.util.AttributsComparisonChain;
import quarto.util.AttributsComparisonChain.Tuple;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class AttributsComparisonChainTest {

	@Test
	public void testName() throws Exception {

		final List<Tuple<QuartoPiece>> tuples = ImmutableList.<Tuple<QuartoPiece>> builder()
				.add(Tuple.of(
						QuartoPiece.of(true, true, true, true),
						QuartoPiece.of(true, true, true, false)))
				.add(Tuple.of(
						QuartoPiece.of(true, false, true, true),
						QuartoPiece.of(true, true, true, false)))
				.add(Tuple.of(
						QuartoPiece.of(true, true, true, true),
						QuartoPiece.of(false, false, false, false)))
				.build();

		tuples.stream().forEach(this::assertEquals);

	}

	public void assertEquals(final Tuple<QuartoPiece> tuple) {
		final int actual = AttributsComparisonChain.on(tuple.left, tuple.right)
				.compareTrueFirst(QuartoPiece::isSquare)
				.compareTrueFirst(QuartoPiece::isTall)
				.compareTrueFirst(QuartoPiece::isBlack)
				.compareTrueFirst(QuartoPiece::isSolidTop)
				.result();

		final int expected = ComparisonChain
				.start()
				.compareTrueFirst(tuple.left.isSquare(), tuple.right.isSquare())
				.compareTrueFirst(tuple.left.isTall(), tuple.right.isTall())
				.compareTrueFirst(tuple.left.isBlack(), tuple.right.isBlack())
				.compareTrueFirst(tuple.left.isSolidTop(), tuple.right.isSolidTop())
				.result();

		Assert.assertEquals(expected, actual);
	}
}
