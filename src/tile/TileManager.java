package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import monJeu.GamePanel;
import monJeu.KeyListenner;

public class TileManager {
	GamePanel gamePanel ;
	KeyListenner key;
	public Tile[] tile;
	public int mapTileNum[][];
	public TileManager(GamePanel gamePanel,KeyListenner key) {
		this.gamePanel = gamePanel;
		this.key = key;
		tile = new Tile[10];
		mapTileNum = new int[gamePanel.maxMondeColonnes][gamePanel.maxMondeLignes];
		getTileImage();
		getMap("/maps/world 1.csv");
	}
	public void getTileImage() {
		try {
			BufferedImage tileSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/tileSheet1.png"));
			for (int i=0;i<4;i++) {
				int x = i%2;
				int y = i/2 ;
				tile[i] = new Tile();
				tile[i].image = tileSheet.getSubimage(x*gamePanel.sizeTile, y*gamePanel.sizeTile, gamePanel.sizeTile,gamePanel.sizeTile );
				if (i==1 || i==3) {
					tile[i].collision = true;
				}
				else {
					tile[i].collision = false;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void getMap(String map) {
		try {
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			/*for (int i = 0; i < gamePanel.maxMondeLignes; i++) {
			        String line = br.readLine();
			        String[] numbers = line.split(",");
			
			    for (int j = 0; j < gamePanel.maxMondeColonnes; j++) {
			        int num = Integer.parseInt(numbers[j]);
			        mapTileNum[i][j] = num;
			    }
			}
			br.close()*/
			int col=0,lignes=0;
			while (lignes < gamePanel.maxMondeLignes) {
		        String line = br.readLine();
			    String[] numbers = line.split(",");
			    while (col < gamePanel.maxMondeColonnes) {
			        int num = Integer.parseInt(numbers[col]);
			        mapTileNum[lignes][col] = num;
			        //System.out.println("Numéro de tuile à la position (" + lignes + "," + col + ") = " + num);
			        col++;
			    }
		        
		        if (col==gamePanel.maxMondeColonnes) {
		        	col=0;
		        	lignes++;
		        }
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// ne pas utiliser for car incrementation trop elevée (ligne dans .csv = 30 et la a chaque increm i=48 puis 96 >>>30 donc ...)
	public void draw(Graphics2D g2) {
		
		/*for (int i = 0;i<gamePanel.maxMondeColonnes;i++) {
			for (int j = 0;j<gamePanel.maxMondeLignes;j++) {
				int tileNum = mapTileNum[j][i];
				int worldX = i * gamePanel.finalSizeTile;
	            int worldY = j * gamePanel.finalSizeTile;
	            int screenX = worldX - gamePanel.player.mondeX + gamePanel.player.screenX;
	            int screenY = worldY - gamePanel.player.mondeY + gamePanel.player.screenY;
	            if (worldX+gamePanel.finalSizeTile> gamePanel.player.mondeX - gamePanel.player.screenX &&
            		worldX-gamePanel.finalSizeTile< gamePanel.player.mondeX + gamePanel.player.screenX &&
            		worldY+gamePanel.finalSizeTile> gamePanel.player.mondeY - gamePanel.player.screenY &&
            		worldY-gamePanel.finalSizeTile< gamePanel.player.mondeY + gamePanel.player.screenY ) {
				g2.drawImage(tile[tileNum].image, screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile, null);
	            }
			}
		}*/
		int lignes=0,col=0;
		while (col<gamePanel.maxMondeColonnes && lignes<gamePanel.maxMondeLignes) {
			int tileNum = mapTileNum[lignes][col];
			int worldX = col * gamePanel.finalSizeTile;
            int worldY = lignes * gamePanel.finalSizeTile;
            int screenX = worldX - gamePanel.player.mondeX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.mondeY + gamePanel.player.screenY;
            if (worldX+gamePanel.finalSizeTile> gamePanel.player.mondeX - gamePanel.player.screenX &&
            		worldX-gamePanel.finalSizeTile< gamePanel.player.mondeX + gamePanel.player.screenX &&
            		worldY+gamePanel.finalSizeTile> gamePanel.player.mondeY - gamePanel.player.screenY &&
            		worldY-gamePanel.finalSizeTile< gamePanel.player.mondeY + gamePanel.player.screenY ) {
            		g2.drawImage(tile[tileNum].image, screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile, null);
        			if(key.debog==true) {
        				if (key.coord==true) {
            			String number = "("+col+";"+lignes+")";
	            		g2.drawString(number,screenX, screenY+15);
        				}
        				if (tile[tileNum].collision == true) { 
		            		g2.setColor(Color.red);
	            		}
	            		else {
	            			g2.setColor(Color.green);
	            		}
	            		g2.drawRect(screenX, screenY,gamePanel.finalSizeTile,gamePanel.finalSizeTile);
            		}
            }
            col++;
			if (col==gamePanel.maxMondeColonnes) {
	        	col=0;
	        	lignes++;
	        }
		}
	}
}
