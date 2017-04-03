package tile;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import gfx.Color;
import gfx.Screen;
import level.Level;

public class RockTile extends Tile{
	
	public List<Entity> entities = new ArrayList<Entity>();

	public RockTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {	
		int rc1 = 111;
		int rc2 = 333;
		int rc3 = 555;
		
		screen.render(x * 16 + 0, y * 16 + 0, 32, Color.get(rc1, level.grassColor, rc2, rc3), 0);
		screen.render(x * 16 + 8, y * 16 + 0, 32, Color.get(rc1, level.grassColor, rc2, rc3), 0);
		screen.render(x * 16 + 0, y * 16 + 8, 32, Color.get(rc1, level.grassColor, rc2, rc3), 0);
		screen.render(x * 16 + 8, y * 16 + 8, 32, Color.get(rc1, level.grassColor, rc2, rc3), 0);
	
	}
	
	public boolean mayPass (Level level, int x, int y, Entity e){
		return false;
	}
}
