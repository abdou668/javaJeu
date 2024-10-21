package objets;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Clef extends Objets {
	public Clef() {
		nom = "Clef";
		zoneSolide.y = 8;
		zoneSolide.height = 40;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objets/clef.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
