package monJeu;

import caracteres.Caracteres;

public class CollisionManager {
	GamePanel gamePanel;
	public CollisionManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void checkCollision(Caracteres caractere) {
		int caractereHaut = caractere.mondeY+caractere.zoneSolide.y;
		int caractereBas = caractere.mondeY+caractere.zoneSolide.y+caractere.zoneSolide.height;
		int caractereGauche = caractere.mondeX+caractere.zoneSolide.x;
		int caractereDroite = caractere.mondeX+caractere.zoneSolide.x+caractere.zoneSolide.width;
		
		int caractereHautPlace = caractereHaut/gamePanel.finalSizeTile ;
		int caractereBasPlace = caractereBas/gamePanel.finalSizeTile ;
		int caractereGauchePlace = caractereGauche/gamePanel.finalSizeTile ;
		int caractereDroitePlace = caractereDroite/gamePanel.finalSizeTile ;
		int tileNum1,tileNum2;
		switch (caractere.direction) {
		case "haut":
			
			caractereHautPlace = (caractereHaut - caractere.speed)/gamePanel.finalSizeTile;
			tileNum1 = gamePanel.tileM.mapTileNum[caractereHautPlace][caractereGauchePlace];
			tileNum2 = gamePanel.tileM.mapTileNum[caractereHautPlace][caractereDroitePlace];
			//System.out.println("haut:"+ caractereHautPlace+"bas:"+caractereBasPlace+"gauche:"+caractereGauchePlace+"droite:"+caractereDroitePlace);
			//System.out.println(tileNum1); 
			//System.out.println(tileNum2);
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				caractere.collision = true;
			}
			break;
		case "bas":
			caractereBasPlace = (caractereBas + caractere.speed)/gamePanel.finalSizeTile;
			tileNum1 = gamePanel.tileM.mapTileNum[caractereBasPlace][caractereGauchePlace];
			tileNum2 = gamePanel.tileM.mapTileNum[caractereBasPlace][caractereDroitePlace];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				caractere.collision = true;
			}
			break;
		case "gauche":
			caractereGauchePlace = (caractereGauche - caractere.speed)/gamePanel.finalSizeTile;
			tileNum1 = gamePanel.tileM.mapTileNum[caractereHautPlace][caractereGauchePlace];
			tileNum2 = gamePanel.tileM.mapTileNum[caractereBasPlace][caractereGauchePlace];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				caractere.collision = true;
			}
			break;
		case "droite":
			caractereDroitePlace = (caractereDroite + caractere.speed)/gamePanel.finalSizeTile;
			tileNum1 = gamePanel.tileM.mapTileNum[caractereHautPlace][caractereDroitePlace];
			tileNum2 = gamePanel.tileM.mapTileNum[caractereBasPlace][caractereDroitePlace];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				caractere.collision = true;
			}
			break;
		}
	}
	public int objCollision(Caracteres caractere,boolean player) {
		int index=999;
		for (int i=0;i<gamePanel.obj.length;i++) {
			if (gamePanel.obj[i]!= null) {
				int caractereZoneSolideX = caractere.zoneSolide.x;
	            int caractereZoneSolideY = caractere.zoneSolide.y;
	            int objZoneSolideX = gamePanel.obj[i].zoneSolide.x;
	            int objZoneSolideY = gamePanel.obj[i].zoneSolide.y;

	            caractere.zoneSolide.x = caractere.mondeX + caractere.zoneSolideDefautX;
	            caractere.zoneSolide.y = caractere.mondeY + caractere.zoneSolideDefautY;
	            gamePanel.obj[i].zoneSolide.x = gamePanel.obj[i].mondeX + gamePanel.obj[i].zoneSolideDefautX;
	            gamePanel.obj[i].zoneSolide.y = gamePanel.obj[i].mondeY + gamePanel.obj[i].zoneSolideDefautY;
	            switch (caractere.direction) {
	    		case "haut":
	    			caractere.zoneSolide.y = caractere.zoneSolide.y - caractere.speed;
	    			if (caractere.zoneSolide.intersects(gamePanel.obj[i].zoneSolide)) {
	    				if (gamePanel.obj[i].collision == true) {
	    					caractere.collision = true;
	    				}
	    				if (player == true) {
		 	                index = i;
	    					
	    				}	 	     
	    			}
	    			break;
	    		case "bas":
	    			caractere.zoneSolide.y = caractere.zoneSolide.y + caractere.speed;
	    			if (caractere.zoneSolide.intersects(gamePanel.obj[i].zoneSolide)) {
	    				if (gamePanel.obj[i].collision == true) {
	    					caractere.collision = true;
	    				}
	    				if (player == true) {
		 	                index = i;
	    					
	    				}	 	     
	    			}
	    			break;
	    		case "gauche":
	    			caractere.zoneSolide.x = caractere.zoneSolide.x - caractere.speed;
	    			if (caractere.zoneSolide.intersects(gamePanel.obj[i].zoneSolide)) {
	    				if (gamePanel.obj[i].collision == true) {
	    					caractere.collision = true;
	    				}
	    				if (player == true) {
		 	                index = i;
	    					
	    				}	 	     
	    			}
	    			break;
	    		case "droite":
	    			caractere.zoneSolide.x = caractere.zoneSolide.x + caractere.speed;
	    			if (caractere.zoneSolide.intersects(gamePanel.obj[i].zoneSolide)) {
	    				if (gamePanel.obj[i].collision == true) {
	    					caractere.collision = true;
	    				}
	    				if (player == true) {
		 	                index = i;
	    					
	    				}	 	     
	    			}
	    			break;
	    			}
	            caractere.zoneSolide.x = caractereZoneSolideX;
	            caractere.zoneSolide.y = caractereZoneSolideY;
	            gamePanel.obj[i].zoneSolide.x = objZoneSolideX;
	            gamePanel.obj[i].zoneSolide.y = objZoneSolideY;
			}
		}	
		return index;
	}
}
