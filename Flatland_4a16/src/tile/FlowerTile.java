package tile;

import entity.Entity;
import gfx.Color;
import gfx.Screen;
import level.Level;

public class FlowerTile extends Tile {

	public FlowerTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(0, level.grassColor, 550, 554);
		screen.render(x * 16 + 0, y * 16 + 0, 32 + 1, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 32 + 1, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 32 + 1, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 32 + 1, col, 0);
	}
}
