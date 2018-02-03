package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 90, 150, 150, 70);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 90, 250, 150, 70);
	public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 90, 350, 150, 70);

	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font f0 = new Font("arial", Font.BOLD, 50);
		
		g.setFont(f0);
		g.setColor(Color.WHITE);
		g.drawString("SPACECRAFT", Game.WIDTH - 165, 100);
		
		Font f1 = new Font("arial", Font.BOLD, 40);
		
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Play", playButton.x + 30, playButton.y + 45);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 30, helpButton.y + 45);
		g2d.draw(helpButton);
		g.drawString("Exit", exitButton.x + 35, exitButton.y + 45);
		g2d.draw(exitButton);
	}
	
	
}
