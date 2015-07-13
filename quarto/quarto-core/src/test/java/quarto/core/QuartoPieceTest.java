package quarto.core;

import org.junit.Assert;
import org.junit.Test;

import quarto.core.QuartoPiece;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoPieceTest {

	@Test
	public void testGetters() {
		Assert.assertEquals(true, new QuartoPiece(true, false, false, false).isSquare());
		Assert.assertEquals(true, new QuartoPiece(false, true, false, false).isTall());
		Assert.assertEquals(true, new QuartoPiece(false, false, true, false).isBlack());
		Assert.assertEquals(true, new QuartoPiece(false, false, false, true).isSolidTop());

		Assert.assertEquals(false, new QuartoPiece(false, true, true, true).isSquare());
		Assert.assertEquals(false, new QuartoPiece(true, false, true, true).isTall());
		Assert.assertEquals(false, new QuartoPiece(true, true, false, true).isBlack());
		Assert.assertEquals(false, new QuartoPiece(true, true, true, false).isSolidTop());
	}

	@Test
	public void testEquals() {
		Assert.assertEquals(new QuartoPiece(true, false, true, true), new QuartoPiece(true, false, true, true));
		Assert.assertNotEquals(new QuartoPiece(true, false, true, true), new QuartoPiece(true, true, true, true));
	}

	@Test
	public void testToString() {
		Assert.assertEquals(
				"[Square][Tall][Black][Solid-top]",
				new QuartoPiece(true, true, true, true).toString());
		Assert.assertEquals(
				"[Circular][Short][White][Hollow-top]",
				new QuartoPiece(false, false, false, false).toString());
	}
}
