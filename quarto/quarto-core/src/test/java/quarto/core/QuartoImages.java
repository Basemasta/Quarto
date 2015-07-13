package quarto.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Steve Cancès
 * @since 1.0
 */
public class QuartoImages {

	/** Image type */
	private static final int IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;

	/** Image Size */
	private static final int IMAGE_SIZE = 1000;

	/** Tall Size */
	private static final int TALL_SIZE = 800;

	/** Short Size */
	private static final int SHORT_SIZE = 600;

	/** Dark color */
	private static final Color DARK = new Color(100, 60, 30);

	/** Light color */
	private static final Color LIGHT = new Color(210, 150, 110);

	/** Hollow factor */
	private static final double HOLLOW_FACTOR = 0.8;

	// @Test
	public void generatePiecesImages() throws Exception {

		for (final QuartoPiece piece : QuartoPieceSetImpl.newFullSet()) {
			final BufferedImage image = this.createImage(piece);
			this.saveImageAsFile(piece, image);
		}
	}

	private void saveImageAsFile(final QuartoPiece quartoPiece, final BufferedImage image) throws IOException {
		ImageIO.write(image, "png", new File("images////" + quartoPiece.toString() + ".png"));
	}

	private BufferedImage createImage(final QuartoPiece quartoPiece) {
		final BufferedImage image = this.createEmptyImage();
		final Graphics2D createGraphics = image.createGraphics();

		final int shapeSize = quartoPiece.isTall() ? QuartoImages.TALL_SIZE : QuartoImages.SHORT_SIZE;

		final int shapeOrigine = (QuartoImages.IMAGE_SIZE - shapeSize) / 2;

		final Color color = quartoPiece.isBlack() ? QuartoImages.DARK : QuartoImages.LIGHT;

		createGraphics.setColor(color);

		if (quartoPiece.isSquare()) {
			createGraphics.fillRect(shapeOrigine, shapeOrigine, shapeSize, shapeSize);
		} else {
			createGraphics.fillOval(shapeOrigine, shapeOrigine, shapeSize, shapeSize);
		}

		if (!quartoPiece.isSolidTop()) {
			createGraphics.setColor(this.brighter(color, QuartoImages.HOLLOW_FACTOR));
			final int hollowSize = shapeSize / 2;
			final int hollowOrigine = (QuartoImages.IMAGE_SIZE - hollowSize) / 2;
			createGraphics.fillOval(hollowOrigine, hollowOrigine, hollowSize, hollowSize);
		}

		return image;
	}

	private BufferedImage createEmptyImage() {
		return new BufferedImage(
				QuartoImages.IMAGE_SIZE,
				QuartoImages.IMAGE_SIZE,
				QuartoImages.IMAGE_TYPE);
	}

	public Color brighter(final Color originalColor, final double factor) {
		int r = originalColor.getRed();
		int g = originalColor.getGreen();
		int b = originalColor.getBlue();
		final int alpha = originalColor.getAlpha();

		final int i = (int) (1.0 / (1.0 - factor));
		if ((r == 0) && (g == 0) && (b == 0)) {
			return new Color(i, i, i, alpha);
		}
		if ((r > 0) && (r < i)) {
			r = i;
		}
		if ((g > 0) && (g < i)) {
			g = i;
		}
		if ((b > 0) && (b < i)) {
			b = i;
		}
		return new Color(Math.min((int) (r / factor), 255),
				Math.min((int) (g / factor), 255),
				Math.min((int) (b / factor), 255),
				alpha);
	}

	public Color darker(final Color originalColor, final double factor) {
		return new Color(Math.max((int) (originalColor.getRed() * factor), 0),
				Math.max((int) (originalColor.getGreen() * factor), 0),
				Math.max((int) (originalColor.getBlue() * factor), 0),
				originalColor.getAlpha());
	}

}
