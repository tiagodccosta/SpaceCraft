package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Anim.Animation;
import classes.EntityA;
import classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Textures tex;
	
	Random r = new Random();
	private int speed = (r.nextInt(3) + 2);
	
	Animation anim;
	
	private Controller c;
	private Game game;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}
	
	public void tick(){
		y += speed;
		
		if(y >(Game.HEIGHT * Game.SCALE)){
			y = -10;
			x = r.nextInt((Game.WIDTH * Game.SCALE) - 33);
		}
		
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
			
		}
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y = y;
	}

	
	public double getX() {
		return x;
	}
}
