/* 			
 * 			pdubs' RENDER MOTOR v1
 * 				 do not copy
 * 
 * 			    copyright: 2015
 * 
 */

package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.DataBufferInt;
import entity.Entity;
import entity.Player;
import entity.TestMob;
import gfx.Font;
import gfx.Screen;
import gfx.SpriteSheet;
import gfx.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import level.Level;
import items.Inventory;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int HEIGHT = 150;
	public static final int WIDTH = 200;

	public static final String NAME = "flatland";
	public static final String VERSION = "alpha 0.2-4a16";
	public static final int SCALE = 3;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colors = new int[256];
	private InputHandler Input = new InputHandler(this);

	private boolean running = false;
	public int tickCount;

	public List<Entity> entities = new ArrayList<Entity>();
	public static final boolean debug = true;
	int framesAff;
	int ticksAff;
	int dir;
	int walkDist;
	int pp = 0;
	boolean walked;
	boolean isPaused = false;
	boolean pressed = false;
	private Screen screen;
	public Level level;
	private Player player;
	private Inventory inventory;
	public TestMob testMob;
	
	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void init() {
		level = new Level(64, 64);
		testMob = new TestMob();
		player = new Player("", Color.get(-1, 0, 500, 532));
		for(int i =0; i<10;i++) level.add(testMob);
		level.add(player);
		
		inventory = new Inventory();
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					// on garde les teintes ?

					int mid = (rr * 30 + gg * 59 + bb * 11) / 100;
					rr = (rr + mid) / 2;
					gg = (gg + mid) / 2;
					bb = (bb + mid) / 2;

					colors[pp++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		init();
		long LT = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int ticked = 0;
		double delta = 0;

		while (running) {
			long NT = System.nanoTime();
			delta += (NT - LT) / ns;
			LT = NT;
			if (delta > 1) {
				tick();
				ticked++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("fps : " + frames + ", ticks : " +
				// ticked);
				ticksAff = ticked;
				framesAff = frames;
				frames = ticked = 0;
			}
		}
		stop();
	}

	public void tick() {
		if(!isPaused){
			tickCount++;

			level.tick();

			int xa = 0;
			int ya = 0;

			if (Input.up.down) ya--;
			if (Input.down.down) ya++;
			if (Input.right.down) xa++;
			if (Input.left.down) xa--;

			if (xa != 0 || ya != 0) {
				player.move(xa, ya);
				walkDist++;
			}

		}	
}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}

		int xScroll = player.x - screen.w / 2;
		int yScroll = player.y - screen.h / 2;

		level.renderBackground(screen, xScroll, yScroll);
		level.renderSprites(screen, xScroll, yScroll);

		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				pixels[x + y * WIDTH] = colors[screen.pixels[x + y * screen.w]];
			}
		}

		screen.clear();

		inputF3();
		focusGUI();
		InvenGUI();
		
		
		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				int cc = screen.pixels[x + y * screen.w];
				if (cc < 255) pixels[x + y * WIDTH] = colors[cc];

			}
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();

	}

	public void InvenGUI(){
			//inventory.render(screen);
	}
	
	public void inputF3() {
		if (Input.f3.down) {
			String msg = player.posX + " , " + player.posY;
			Font.drawStyle(NAME + " , " + VERSION, screen, 0, 0, Color.get(0, 555, 555, 555), 1, 0);
			Font.draw("by pdubs", screen, 0, 8, Color.get(0, 555, 555, 555));
			Font.draw(msg, screen, 0, 8 * 2 + 2, Color.get(0, 555, 555, 555));
			Font.draw("FPS : " + framesAff, screen, 0, 8 * 3 + 4, Color.get(0, 555, 555, 555));
			Font.draw("TICKS : " + ticksAff, screen, 0, 8 * 4 + 4, Color.get(0, 555, 555, 555));
		}
	}

	public void focusGUI() {
		if (!hasFocus()) {
			String msg = "Cliquer pour jouer";
			if(!debug){
				if ((tickCount / 40) % 2 == 0) {
					Font.draw(msg, screen, (WIDTH - msg.length() * 8) / 2, (HEIGHT - 8) / 2, Color.get(123, 555, 555, 555));

					screen.render((WIDTH - msg.length() * 8 - 16) / 2, (HEIGHT / 2) - 12, 12 * 32, Color.get(-1, 123, 111, 555), 0);
					screen.render((WIDTH - msg.length() * 8 - 16) / 2, (HEIGHT / 2) + 4, 12 * 32, Color.get(-1, 123, 111, 555), 2);
					screen.render((WIDTH + msg.length() * 8) / 2, (HEIGHT / 2) - 12, 12 * 32, Color.get(-1, 123, 111, 555), 1);
					screen.render((WIDTH + msg.length() * 8) / 2, (HEIGHT / 2) + 4, 12 * 32, Color.get(-1, 123, 111, 555), 3);

					for (int i = 0; i < msg.length(); i++) {
						screen.render(((WIDTH - msg.length() * 8 - 16) / 2 + 8) + i * 8, (HEIGHT / 2) - 12, 12 * 32 + 1, Color.get(-1, 123, 111, 555), 0);
						screen.render(((WIDTH - msg.length() * 8 - 16) / 2 + 8) + i * 8, (HEIGHT / 2) + 4, 12 * 32 + 1, Color.get(-1, 123, 111, 555), 2);
						screen.render((WIDTH + msg.length() * 8) / 2, (HEIGHT / 2) - 4, 12 * 32 + 2, Color.get(-1, 123, 111, 555), 0);
						screen.render((WIDTH - msg.length() * 8 - 16) / 2, (HEIGHT / 2) - 4, 12 * 32 + 2, Color.get(-1, 123, 111, 555), 1);
					}
				}
			} else {
			}
			}

	}

	public void print(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		if (debug){
			JFrame frame = new JFrame(NAME + " - " + VERSION + "debug");
			initFrame(frame, game);
		}else{
			JFrame frame = new JFrame(NAME + " - " + VERSION);
			initFrame(frame, game);
		}
		

	}
	
	public static void initFrame(JFrame frame, Game game){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}