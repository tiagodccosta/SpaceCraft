package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		/**	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 90, 150, 150, 70);
			public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 90, 250, 150, 70);
			public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 90, 350, 150, 70);
			public Rectangle backButton = new Rectangle(Game.WIDTH / 2 + 110, 50, 100, 40);

		*/
		
		//Play Button
		if(mx >= Game.WIDTH / 2 + 90 && mx <= Game.WIDTH / 2 + 190){
			if(my >= 150 && my <= 220){
				//Pressed Play Button
				Game.State = Game.STATE.GAME;
			}
		}
		
		//Help Button
		if(mx >= Game.WIDTH / 2 + 90 && mx <= Game.WIDTH / 2 + 190){
			if(my >= 250 && my <= 320){
				//Pressed help button
				Game.State = Game.STATE.HELP;
			}
		}
		
		//Exit Button
				if(mx >= Game.WIDTH / 2 + 90 && mx <= Game.WIDTH / 2 + 190){
					if(my >= 350 && my <= 420){
						//Pressed Exit Button
						System.exit(0);
					}
				}
				
				if(mx >= Game.WIDTH / 2 + 110 && mx <= Game.WIDTH / 2 + 210){
					if(my >= 50 && my <= 90){
						//Pressed back button
						Game.State = Game.STATE.MENU;
					}
				}
				
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	
	
	
	
}
