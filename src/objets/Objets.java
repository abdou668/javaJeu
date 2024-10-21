package objets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import monJeu.GamePanel;
import monJeu.KeyListenner;

public class Objets {
	public int mondeX,mondeY;
	public BufferedImage image;
	public String nom;
	public Rectangle zoneSolide = new Rectangle(0,0,48,48);
	public int zoneSolideDefautX = zoneSolide.x;
	public int zoneSolideDefautY = zoneSolide.y;
	public boolean collision = false;
	public void draw(Graphics2D g2,GamePanel gamePanel,KeyListenner key) {
		int screenX = mondeX - gamePanel.player.mondeX + gamePanel.player.screenX;
        int screenY = mondeY - gamePanel.player.mondeY + gamePanel.player.screenY;
        if (mondeX+gamePanel.finalSizeTile> gamePanel.player.mondeX - gamePanel.player.screenX &&
        		mondeX-gamePanel.finalSizeTile< gamePanel.player.mondeX + gamePanel.player.screenX &&
        		mondeY+gamePanel.finalSizeTile> gamePanel.player.mondeY - gamePanel.player.screenY &&
        		mondeY-gamePanel.finalSizeTile< gamePanel.player.mondeY + gamePanel.player.screenY ) {
        		g2.drawImage(image, screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile, null);
        		if (key.debog == true) {
        			g2.setColor(Color.blue);
        			g2.drawRect(screenX + zoneSolide.x, screenY + zoneSolide.y , zoneSolide.width, zoneSolide.height);
        		}
        		
        }
	}
}
