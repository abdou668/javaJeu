package caracteres;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Caracteres {
	public int mondeX,mondeY;
	public int speed;
	public BufferedImage haut,bas,gauche,droite;
	public String direction;
	public Rectangle zoneSolide = new Rectangle(8,8,32,32);
	public int zoneSolideDefautX,zoneSolideDefautY;
	public boolean collision = false;
}