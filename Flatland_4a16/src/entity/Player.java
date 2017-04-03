package entity;

import gfx.Color;
import gfx.Screen;
import gfx.Font;

public class Player extends Mob {

	private int col;
	private String psd = "";
	
	public Player(String psd, int col){
		this.psd = psd;
		this.col = col;
	}
	
	public void tick() {
		
	}

	public void render(Screen screen) {
		
		int xt = 0;
		int yt = 13;

		int flip1 = (walkDist >> 4) & 1;
		int flip2 = (walkDist >> 4) & 1;

		if (dir == 1) {
			xt = +2;
		}
		if (dir > 1) {
			flip1 = 0;
			flip2 = (walkDist >> 5) & 1;
			if (dir == 2) {
				flip1 = 1;
			}
			xt += 4 + ((walkDist >> 3) & 1) * 2;
		}

		int xo = x - 8;
		int yo = y - 12;
		screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col, flip1);
		screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col, flip1);
		screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col, flip2);
		screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col, flip2);
		
		Font.drawStyle(psd, screen, x - psd.length() *4, y - 22, Color.get(-1, 555, 555, 555), 1, 0);
		
		posX = x >> 3;
		posY = y >> 3;
		
	}
}
