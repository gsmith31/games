/**
 * Stitch multiple Glitch playr sprite sheets into one.
 * 
 * @author J. Hollingsworth and G. Roth
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GlitchStitch {

	private static final int FRAME_WIDTH = 192;
	private static final int FRAME_HEIGHT = 160;

	public static void main(String[] args) throws IOException {
		BufferedImage result = new BufferedImage(
				4032, 960,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();

		// BASE
		int width = 96;
		int height = 130;
		BufferedImage bi = ImageIO.read(new File("demon_base.png"));
		for( int x = 0; x < 15; x++){
		g.drawImage(bi.getSubimage(x * width, 0, width, height), x*FRAME_WIDTH+(FRAME_WIDTH - width)/2, 0 +(FRAME_HEIGHT - height)  , null);
		}
		
		// CLIMB
		width = 94;
		height = 127;
		bi = ImageIO.read(new File("demon_climb.png"));
		for (int x = 0; x < 19; x++) {
			g.drawImage(bi.getSubimage(x * width, 0, width, height), 
					x*FRAME_WIDTH+(FRAME_WIDTH - width)/2, 
					FRAME_HEIGHT+(FRAME_HEIGHT - height), 
					null);
		}

		// JUMP
		width = 128;
		height = 132;
		bi = ImageIO.read(new File("demon_jump.png"));
		for (int x = 0; x < 33; x++) {
			BufferedImage sub = bi.getSubimage(x * width, 0, width, height);
			if (x < 21) {
				g.drawImage(sub, 
						x*FRAME_WIDTH+(FRAME_WIDTH - width)/2, 
						(FRAME_HEIGHT*2)+(FRAME_HEIGHT - height), 
						null);
			} else {
				g.drawImage(sub, 
						(x - 21)*FRAME_WIDTH+(FRAME_WIDTH - width)/2, 
						(FRAME_HEIGHT*3)+(FRAME_HEIGHT - height), 
						null);
			}
		}

		// SLEEP
		width = 110;
		height = 128;
		bi = ImageIO.read(new File("demon_sleep.png"));
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 21; x++) {
				g.drawImage(bi.getSubimage(x * width, y * height, width, height), 
						x*FRAME_WIDTH+(FRAME_WIDTH - width)/2, 
						FRAME_HEIGHT*(y+4)+(FRAME_HEIGHT - height), 
						null);
			}
		}

		ImageIO.write(result, "png", new File("result.png"));
	}

}
