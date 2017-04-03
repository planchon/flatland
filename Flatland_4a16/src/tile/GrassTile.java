package tile;

import gfx.Color;
import gfx.Screen;
import level.Level;

public class GrassTile extends Tile{

	public GrassTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {	
		screen.render(x * 16 + 0, y * 16 + 0, 0, Color.get(level.grassColor, level.grassColor, level.grassColor+111, level.grassColor+111), 0);
		screen.render(x * 16 + 8, y * 16 + 0, 0, Color.get(level.grassColor, level.grassColor, level.grassColor+111, level.grassColor+111), 0);
		screen.render(x * 16 + 0, y * 16 + 8, 0, Color.get(level.grassColor, level.grassColor, level.grassColor+111, level.grassColor+111), 0);
		screen.render(x * 16 + 8, y * 16 + 8, 0, Color.get(level.grassColor, level.grassColor, level.grassColor+111, level.grassColor+111), 0);
	}
}
