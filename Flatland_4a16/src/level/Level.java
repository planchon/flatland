// 		LIFEGATE COPYRIGHT PAULDOR 

package level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Entity;
import gfx.Screen;
import tile.Tile;

public class Level {
	public int w, h;

	public int[] tiles;
	public byte[] data;

	public int grassColor = 141;

	public List<Entity> entities = new ArrayList<Entity>();

	public Random random = new Random();

	public Level(int w, int h) {
		this.w = w;
		this.h = h;
		tiles = new int[w * h];
		data = new byte[w * h];

		for (int i = 0; i < w * h; i++) {
			if (random.nextInt(10) == 0) {
				if (random.nextInt(2) == 0) {
					tiles[i] = 1;
				} else {
					tiles[i] = 2;
				}
			} else {
				tiles[i] = 0;
			}

		tiles[0] = tiles[1] = tiles[h] = tiles[h+1] =2;
		}
	}

	public void renderBackground(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				getTile(x, y).render(screen, this, x, y);
			}
		}
		screen.setOffset(0, 0);
	}

	public void renderSprites(Screen screen, int xScroll, int yScroll) {
		screen.setOffset(xScroll, yScroll);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		screen.setOffset(0, 0);
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) return Tile.rock;
		return Tile.tiles[tiles[x + y * w]];
	}

	public void add(Entity entity) {
		entities.add(entity);
		entity.init(this);
	}
}