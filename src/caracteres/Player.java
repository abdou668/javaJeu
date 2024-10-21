package caracteres;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import monJeu.GamePanel;
import monJeu.KeyListenner;

public class Player extends Caracteres {
	GamePanel gamePanel;
	KeyListenner key;
	public int screenX,screenY;
	public int hasKey=0;
	public boolean inventory = false;
	public Player (GamePanel gamePanel,KeyListenner key) {
		this.gamePanel = gamePanel;
		this.key = key;
		screenX=(gamePanel.larEcran/2) - gamePanel.finalSizeTile/2;
		screenY=(gamePanel.lonEcran/2)- gamePanel.finalSizeTile/2;
		zoneSolideDefautX = zoneSolide.x;
		zoneSolideDefautY = zoneSolide.y;
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
		mondeX = 21*gamePanel.finalSizeTile;
		mondeY = 20*gamePanel.finalSizeTile;
		speed = 4;
		direction = "";
	}
	public void objetPris(int i) {
		if (i != 999 ){
			if (gamePanel.obj[i].nom == "Clef") {
				System.out.println(gamePanel.obj[i].nom);
				gamePanel.obj[i] = null;
				hasKey++;
			}
			else if (gamePanel.obj[i].nom == "Porte") {
				if (hasKey>0) {
					gamePanel.obj[i] = null;
					hasKey--;
				}
			}
		}
	}
	public void update() {
	if(key.haut == true || key.bas == true || key.gauche == true || key.droite == true) {	
		if (key.haut == true) {
			direction = "haut";
		}
		else if (key.bas == true) {
			direction = "bas";
		}
		else if (key.gauche == true) {
			direction = "gauche";
		}
		else if (key.droite == true) {
			direction = "droite";
		}
	}
	else {
		direction = "";
	}
	collision=false;
	gamePanel.collisionM.checkCollision(this);
	int indexObjet = gamePanel.collisionM.objCollision(this,true);
	objetPris(indexObjet);
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
		}}
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
		g2.drawImage(image, screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile, null);
		if (key.debog==true) {
			if (key.plus==true && speed <24) {
				speed++;System.out.println(speed);
			}
			if (key.moins==true && speed >= 4) {
				speed--;
				System.out.println(speed);
			}
			String number = "SPEED : "+speed;
			g2.setColor(Color.blue);
    		g2.drawString(number,screenX+gamePanel.finalSizeTile/2, screenY+gamePanel.finalSizeTile+10);
			g2.drawRect(screenX + zoneSolide.x, screenY + zoneSolide.y, zoneSolide.width, zoneSolide.height);
		}
		else {
			speed=4;
		}
	}
}
