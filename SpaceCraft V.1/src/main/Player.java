package main;

import java.awt.Graphics;
import java.awt.Rectangle;

import Anim.Animation;
import classes.EntityA;
import classes.EntityB;

public class Player extends GameObject implements EntityA{

	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	Game game;
	Animation anim;
	Controller c;
	
	public Player(double x, double y, Textures tex, Game game, Controller c){
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.c = c;
		
		anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2]);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(x >= 640 - 52)
			x = 640 - 52;
		if(x <= 0)
			x = 0;
		if(y >= 480 - 67)
			y = 480 - 67;
		if(y <= 0)
			y = 0;
		
		for(int i = 0; i < game.eb.size(); i++){
			EntityB tempEnt = game.eb.get(i);
				if(Physics.Collision(this, tempEnt)){
					c.removeEntity(tempEnt);
					Game.HEALTH -= 10;
					game.setEnemy_killed(game.getEnemy_killed() + 1);
				}
			}
		
		if(Game.HEALTH <= 0){
			Game.State = Game.STATE.MENU;
			Game.HEALTH = 200;
			}
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}
}
