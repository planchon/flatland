package entity;

import java.util.Random;

import level.Level;
import gfx.Screen;

public class Entity {
	protected Random random = new Random();
	public int x, y;
	public int posX, posY;
	public boolean remove;
	public Level level;
	
	public void render(Screen screen){
	}
	
	public void remove(){
		remove = true;
	}
	
	public void tick(){
			
	}

	public final void init(Level level) {
		this.level = level;
	}
}
