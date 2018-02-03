package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import classes.EntityA;
import classes.EntityB;

public class Game extends Canvas implements Runnable{

	
	public static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH /12 * 9;
	public static final int SCALE = 2;
	public final String title = "SPACECRAFT"; 
	
	private boolean running = false;
	public Thread thread;
	
	private BufferedImage spritesheet = null;
	private BufferedImage background = null;
	
	private boolean isShooting = false;
	
	private Player p;
	private Controller c;
	private Textures tex;
	
	public Graphics g;
	
	public static int enemy_count = 5;
	public static int enemy_killed = 0;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	private Menu menu;
	private Help help;  
	
	public static int HEALTH = 200;
	
	public static enum STATE{
		MENU,
		GAME,
		HELP
	};
	
	public static STATE State = STATE.MENU;
	public static STATE State1 = STATE.HELP;

	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spritesheet = loader.loadImage("/spritesheet.png");
			background = loader.loadImage("/background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput());
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(280, 380, tex, this, c);
		menu = new Menu();
		help = new Help();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.spawnEnemy(enemy_count);
	}
	
	
	private synchronized void start(){
		if(running)
			return;
			
			running = true;
			thread = new Thread(this);
			thread.start();
		
	}
	
	private synchronized void stop(){
		if(!running)
			return;
			
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	
	private void tick(){
		if(State == STATE.GAME){
			p.tick();
			c.tick();
		}
		
		if(enemy_killed >= enemy_count){
			enemy_count += 2;
			enemy_killed = 0;
			c.spawnEnemy(enemy_count);
		}
	}
	
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		///////////////////////////////////////////////////////////////////////////
		
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.GAME){
			c.render(g);
			
			g.setColor(Color.GRAY);
			g.fillRect(10, 420, 200, 50);
			
			g.setColor(Color.green);
			g.fillRect(10, 420, HEALTH, 50);
			
			g.setColor(Color.white);
			g.drawRect(10, 420, 200, 50);
			
			p.render(g);
		}else if(State == STATE.MENU){
			menu.render(g);
		}else if( State == STATE.HELP){
			help.render(g);
		}
		///////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.GAME){
		if(key == KeyEvent.VK_D){
			p.setVelX(5);
		}else if(key == KeyEvent.VK_A){
			p.setVelX(-5);
		}else if(key == KeyEvent.VK_S){
			p.setVelY(5);
		}else if(key == KeyEvent.VK_W){
			p.setVelY(-5);
		}else if(key == KeyEvent.VK_J && !isShooting){
			isShooting = true;
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
		}else if(key == KeyEvent.VK_ESCAPE){
			Game.State = Game.STATE.MENU;
		}
	}
}

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_D){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_A){
			p.setVelX(0);
		}else if(key == KeyEvent.VK_S){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_W){
			p.setVelY(0);
		}else if(key == KeyEvent.VK_J){
			isShooting = false;
		}
	}
	
	public static void main(String[] args){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.title);
		
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	
		game.start();
	}
	
	public BufferedImage getSpriteSheet(){
		return spritesheet;
	}
	
	
	public int getEnemy_count() {
		return enemy_count;
	}


	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}


	public int getEnemy_killed() {
		return enemy_killed;
	}


	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}
