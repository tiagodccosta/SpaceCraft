package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	
	
	public Rectangle backButton = new Rectangle(Game.WIDTH / 2 + 110, 50, 100, 40);


	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;

		
		Font f1 = new Font("arial", Font.BOLD, 20);
		g.setFont(f1);
		g.setColor(Color.WHITE);
		g.drawString("Press the keys W, S, A, D, for moving the Space Ship." , Game.WIDTH - 250, 130);
		
		g.setFont(f1);
		g.setColor(Color.WHITE);
		g.drawString("Press the J key for shooting the cosmic missiles!", Game.WIDTH - 230, 180);
		
		Font f2 = new Font("arial", Font.BOLD, 15);
		g.setFont(f2);
		g.setColor(Color.WHITE);
		g.drawString("Try to survive the most has possible and destroy the most enemy Space Ships has you can!!", Game.WIDTH - 320, 230);
		
		Font f3 = new Font("arial", Font.BOLD, 42);
		g.setFont(f3);
		g.setColor(Color.WHITE);
		g.drawString("GOOD LUCK SPACE CRAFTER!", Game.WIDTH - 315, 300);
		
		Font f4 = new Font("arial", Font.BOLD, 35);
		g.setFont(f4);
		g.setColor(Color.WHITE);
		g.drawString("Back", backButton.x + 10, backButton.y + 30);
		g2d.draw(backButton);
	}
	
	
}
