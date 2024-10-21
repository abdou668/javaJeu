package monJeu;

import objets.ChampignonGrand;
import objets.Clef;
import objets.Porte;

public class ObjetsManager {
	GamePanel gamePanel;
	public ObjetsManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void setObjets() {
		gamePanel.obj[0] = new Clef();
		gamePanel.obj[0].mondeX = 40 * gamePanel.finalSizeTile;
		gamePanel.obj[0].mondeY = 8 * gamePanel.finalSizeTile;
		gamePanel.obj[1] = new Clef();
		gamePanel.obj[1].mondeX = 46 * gamePanel.finalSizeTile;
		gamePanel.obj[1].mondeY = 33 * gamePanel.finalSizeTile;
		gamePanel.obj[2] = new Porte();
		gamePanel.obj[2].mondeX = 25 * gamePanel.finalSizeTile;
		gamePanel.obj[2].mondeY = 35 * gamePanel.finalSizeTile;
		gamePanel.obj[3] = new ChampignonGrand();
		gamePanel.obj[3].mondeX = 46 * gamePanel.finalSizeTile;
		gamePanel.obj[3].mondeY = 35 * gamePanel.finalSizeTile;
	}
}
