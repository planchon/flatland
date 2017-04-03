package items;

import gfx.Color;
import gfx.Font;
import gfx.Screen;

public class Inventory {

	private String msg = "Inventory";
	public String[] items = new String[5];

	public Inventory() {
		for (int i = 0; i < items.length; i++)
			items[i] = "item " + i;
	}

	public void render(Screen screen) {
		int msgL = msg.length() * 8;
		int xs = (screen.w / 2) - msg.length() * 4;
		int ys = (screen.h / 2) - 8 * 5;
		int size = 4;
		int col = Color.get(005, 333, 444, 555);
		
		screen.render(xs - 8 * size, ys, 12 * 32, col, 0);
		screen.render(xs + 8 * size + msgL, ys, 12 * 32, col, 1);
		screen.render(xs - 8 * size, ys + items.length * 8 + 8, 12 * 32, col, 2);
		screen.render(xs + 8 * size + msgL, ys + items.length * 8 + 8, 12 * 32, col, 3);
		
		for (int i = 1; i < size + msg.length() + size; i++) {
			screen.render(xs - 8 * size + i * 8, ys, 12 * 32 + 1, col, 0);
			screen.render(xs - 8 * size + i * 8, ys + items.length * 8 + 8, 12 * 32 + 1, col, 2);
		}
		
		for (int i = 0; i < items.length; i++) {
			screen.render(xs - 8 * size, ys + i * 8 + 8, 12 * 32 + 2, col, 3);
			screen.render(xs + 8 * size + msgL, ys + i * 8 + 8, 12 * 32 + 2, col, 2);
		}
		
		for (int i = 0; i < items.length; i++) {
			Font.draw(items[i], screen, xs - 8 * size + 8, ys + i * 8 + 8, Color.get(005, 555, 555, 555));
			if (items[i].length() < xs + 8 * size + msgL - 8) {
				int space = 18 - items[i].length() - 2, y = 0;
				while (space != 0) {
					Font.draw(" ", screen, (xs - 8 * size + 8) + items[i].length() * 8 + y * 8, ys + i * 8 + 8, Color.get(005, 555, 555, 555));
					space--;
					y++;
				}
			}
		}
		Font.draw(msg, screen, xs, ys, Color.get(005, 555, 555, 555));
	}
}
