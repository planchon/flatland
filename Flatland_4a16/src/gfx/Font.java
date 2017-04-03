package gfx;

import gfx.Color;
import java.util.Random;

public class Font {
	private static String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
			"0123456789.,!?'\"-+=/\\%()<>:;     " + //
			"";
	
	/*public static void setMap(String msg, Screen screen, int x, int y, int col) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			if (ix >= 0) {
				screen.setTile(x + i, y, ix + 30 * 32, col, 0);
			}
		}
	}*/
	
	public static void draw(String msg, Screen screen, int x, int y, int col) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			if (ix >= 0) {
				screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
			}
		}
	}
	
	public static void drawStyle(String msg, Screen screen, int x, int y, int col, int type, int bg) {
		msg = msg.toUpperCase();
		if (type == 2){ //disco
			for (int i = 0; i < msg.length(); i++){
				int ix = chars.indexOf(msg.charAt(i));
				Random random = new Random();
				screen.render(x + i * 8, y, ix + 30 * 32, Color.get( bg, 0, 0, random.nextInt(555)), 0);
			}
		}
		for (int i = 0; i < msg.length(); i++) {
			int ix = chars.indexOf(msg.charAt(i));
			if (type == 1){
				screen.render(1 + x + i * 8, 1 + y, ix + 30 * 32, Color.get(-1, 000, 000, 000), 0);
			}
			if (ix >= 0 && type != 2) {
				screen.render(x + i * 8, y, ix + 30 * 32, col, 0);
			}
		}
	}
}
