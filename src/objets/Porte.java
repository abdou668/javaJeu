package objets;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Porte extends Objets {
	public Porte() {
		nom = "Porte";
		collision = true;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objets/porte.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}