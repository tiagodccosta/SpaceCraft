package main;

import java.awt.image.BufferedImage;

public class Textures {

	private SpriteSheet ss;
	
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures(){
		player[0] = ss.grabImage(1, 2, 64, 64);
		player[1] = ss.grabImage(1, 2, 64, 64);
		player[2] = ss.grabImage(1, 2, 64, 64);

		bullet[0] = ss.grabImage(2, 1, 32, 32);
		bullet[1] = ss.grabImage(2, 1, 32, 32);
		bullet[2] = ss.grabImage(2, 1, 32, 32);
		
		enemy[0] = ss.grabImage(3, 1, 32, 32);
		enemy[1] = ss.grabImage(3, 1, 32, 32);
		enemy[2] = ss.grabImage(3, 1, 32, 32);
	}
	
}
