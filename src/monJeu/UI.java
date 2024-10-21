package monJeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import caracteres.Player;

public class UI {
	GamePanel gamePanel;
	Player player;
	Font font1;
	String inventaire;
	public UI(GamePanel gamePanel ,Player player) {
		this.gamePanel = gamePanel;
		this.player = player;
		font1= new Font("Arial",Font.BOLD,40);
		inventaire = "Inventory: \n Key = " + player.hasKey;
	}
	public void fondDeTexte(Graphics2D g2) {
		g2.setFont(font1);
		Color couleur1 = new Color(0,0,0,210);
		g2.setColor(couleur1);
		int x=50,y=50;
		g2.fillRoundRect(x, y, gamePanel.larEcran-(gamePanel.finalSizeTile*3), gamePanel.lonEcran/2, 35, 35);
		g2.setColor(Color.white);
		for (String line : inventaire.split("\n")) {
			g2.drawString(line, x+gamePanel.finalSizeTile, y+gamePanel.finalSizeTile);
			y+=40;
		}

	}
	public void draw(Graphics2D g2) {
		fondDeTexte(g2);	}
}
