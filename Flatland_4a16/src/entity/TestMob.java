package entity;

import java.util.Random;

import gfx.Color;
import gfx.Screen;

public class TestMob extends Mob {

	private int xa, ya;
	private int col;
	
	public TestMob(){
		col = random.nextInt(100);
	}
	
	public void tick(){
		
		if (random.nextInt(40000) == 0) {
			xa = random.nextInt(2) - 1;
			ya = random.nextInt(2) - 1;
		}
		move(xa, ya);
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

		screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, Color.get(-1, 111, col, 444), flip1);
		screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, Color.get(-1, 111, col, 444), flip1);
		screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, Color.get(-1, 111, col, 444), flip2);
		screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, Color.get(-1, 111, col, 444), flip2);
	
	}
}