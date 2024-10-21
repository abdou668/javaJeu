package monJeu;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenner implements KeyListener  {
	GamePanel gamePanel;
	public boolean debog=false,plus=false,moins=false,coord=false;
	public boolean haut=false,bas=false,gauche=false,droite=false;

	public KeyListenner(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	// haut = 38, bas = 40 , gauche = 37, droite = 39, d= 68
	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getKeyChar();
		if (code == 'd') {
			debog = !debog;
            System.out.println("Mode debug: " + (debog ? "activé" : "désactivé"));
			}
		if (code == 'c') {
			coord = !coord;
            System.out.println("Mode coord: " + (coord ? "activé" : "désactivé"));
			}
		if (code == 'e') {
			gamePanel.player.inventory = !gamePanel.player.inventory;
            System.out.println("Inventaire: " + (gamePanel.player.inventory ? "activé" : "désactivé"));
			}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//System.out.println(code);
		if (code == 38) {
			haut = true;
		}
		if (code == 40) {
			bas = true;
		}
		if (code == 37) {
			gauche = true;
		}
		if (code == 39) {
			droite = true;
		}
		if (code == 54) {
			moins = true;
		}
		if (code == 61) {
			plus = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 38) {
			haut = false;
		}
		if (code == 40) {
			bas = false;
		}
		if (code == 37) {
			gauche = false;
		}
		if (code == 39) {
			droite = false;
		}
		if (code == 54) {
			moins = false;
			System.out.println("le debog est effectue");
		}
		if (code == 61) {
			plus = false;
			System.out.println("le debog est effectue");
		}
		
	}

}
