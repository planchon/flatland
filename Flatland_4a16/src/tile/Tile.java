package tile;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import level.Level;
import gfx.Screen;
import tile.Tile;

public class Tile {
	public static Tile[] tiles = new Tile[256];
	public static Tile grass = new GrassTile(0);
	public static Tile rock = new RockTile(1);
	public static Tile flower = new FlowerTile(2);
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public static int id;
	
	public Tile(int id){
		this.id = id;
		tiles[id] = this;
	}

	public void render(Screen screen, Level level, int x, int y) {	
	}
	
	public boolean mayPass (Level level, int x, int y, Entity e){
		return true;
	}
}
