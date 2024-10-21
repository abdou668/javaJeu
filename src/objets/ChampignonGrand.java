package objets;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ChampignonGrand extends Objets {
	public ChampignonGrand() {
		nom = "Champignon grand";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objets/champignonGrand.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}