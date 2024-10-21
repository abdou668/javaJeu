package monJeu;
import java.awt.*;
import javax.swing.JPanel;

import caracteres.Monster;
import caracteres.Player;
import objets.Objets;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {
	// ECRAN
	public final int sizeTile = 16;
	public final int scale =3;
	public final int finalSizeTile = sizeTile * scale;
	public final int maxEcranLignes = 16;
	public final int maxEcranColonnes = 12;
	public int larEcran = maxEcranLignes * finalSizeTile;
	public int lonEcran = maxEcranColonnes * finalSizeTile;
	// MONDE
	public final int maxMondeLignes = 50;
	public final int maxMondeColonnes = 50;
	public int larMonde = maxMondeLignes * finalSizeTile;
	public int lonMonde = maxMondeColonnes * finalSizeTile;

	int FPS = 60;
	KeyListenner key = new KeyListenner(this);
	public CollisionManager collisionM = new CollisionManager(this);
	public ObjetsManager objetsM = new ObjetsManager(this);
	public TileManager tileM = new TileManager(this,key);
	public Objets obj[] = new Objets[5];
	Thread gameThread;
	
	public Player player = new Player(this, key);
	public Monster monstre = new Monster(this, key);
	public UI ui = new UI(this,player);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(larEcran,lonEcran));
		this.setBackground(Color.lightGray);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}
	public void SetupGame() {
		objetsM.setObjets();
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		double draw = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread!=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / draw;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				//System.out.println("je update");
				repaint();
				delta--;
			}
		}
	}
	public void update() {
		player.update();
		monstre.update();;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		for (int i=  0;i<obj.length;i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this,key);
			}
		}
		monstre.draw(g2);
		player.draw(g2);
		if (player.inventory == true) {
			ui.draw(g2);
		}
		g2.dispose();
	}
}
