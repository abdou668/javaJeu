package caracteres;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import monJeu.GamePanel;
import monJeu.KeyListenner;

public class Monster extends Caracteres{
	GamePanel gamePanel;
	KeyListenner key;
	public int screenX,screenY;
	public Monster (GamePanel gamePanel,KeyListenner key) {
		this.gamePanel = gamePanel;
		this.key = key;
		screenX=(gamePanel.larEcran/2) - gamePanel.finalSizeTile/2;
		screenY=(gamePanel.lonEcran/2)- gamePanel.finalSizeTile/2;
		zoneSolide = new Rectangle(8,8,32,32);
		defaultPara();
		getPlayerImage();
	}
	public void getPlayerImage() {
		try {
			haut = ImageIO.read(getClass().getResourceAsStream("/player/dos.png"));
			bas = ImageIO.read(getClass().getResourceAsStream("/player/face.png"));
			gauche = ImageIO.read(getClass().getResourceAsStream("/player/gauche.png"));
			droite = ImageIO.read(getClass().getResourceAsStream("/player/droite.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void defaultPara() {
		mondeX = 15*gamePanel.finalSizeTile;
		mondeY = 20*gamePanel.finalSizeTile;
		speed = 1;
		direction = "bas";
	}
	public void update() {
	collision=false;
	gamePanel.collisionM.checkCollision(this);
	if (collision==false) {
		switch (direction) {
		case "haut":
			mondeY -= speed;
			break;
		case "bas":
			mondeY += speed;
			break;
		case "gauche":
			mondeX -= speed;
			break;
		case "droite":
			mondeX += speed;
			break;
		}
	}
	else {
		switch (direction) {
		case "haut":
			direction="bas";
			break;
		case "bas":
			direction="haut";
			break;
		case "gauche":
			mondeX -= speed;
			break;
		case "droite":
			mondeX += speed;
			break;
		}
	}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
		case "haut":
			image = haut;
			break;
		case "bas":
			image = bas;
			break;
		case "gauche":
			image = gauche;
			break;
		case "droite":
			image = droite;
			break;
		default:
			image = bas;
			break;
		}
		//g2.setColor(Color.red);
		//g2.fillRect(x,y,gamePanel.finalSizeTile-1,gamePanel.finalSizeTile-1);
		int screenX = mondeX - gamePanel.player.mondeX + gamePanel.player.screenX;
        int screenY = mondeY - gamePanel.player.mondeY + gamePanel.player.screenY;
        if (mondeX+gamePanel.finalSizeTile> gamePanel.player.mondeX - gamePanel.player.screenX &&
        		mondeX-gamePanel.finalSizeTile< gamePanel.player.mondeX + gamePanel.player.screenX &&
        		mondeY+gamePanel.finalSizeTile> gamePanel.player.mondeY - gamePanel.player.screenY &&
        		mondeY-gamePanel.finalSizeTile< gamePanel.player.mondeY + gamePanel.player.screenY ) {
        		g2.drawImage(image, screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile, null);
        		if (key.debog==true) {
        			String number = "SPEED : "+speed;
        			g2.setColor(Color.blue);
            		g2.drawString(number,screenX+gamePanel.finalSizeTile/2, screenY+gamePanel.finalSizeTile+10);
        			g2.drawRect(screenX + zoneSolide.x, screenY + zoneSolide.y, zoneSolide.width, zoneSolide.height);
        		}
        }
	}
}
