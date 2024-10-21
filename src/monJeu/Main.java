package monJeu;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setTitle("Best Game Ever !!!");
		fenetre.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		fenetre.add(gamePanel);
		fenetre.pack();
		fenetre.setVisible(true);
		gamePanel.SetupGame();
		gamePanel.startGameThread();
	}
}
