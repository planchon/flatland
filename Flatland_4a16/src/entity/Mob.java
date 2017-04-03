package entity;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Entity {
	
	protected int walkDist = 0;
	protected int dir = 0;
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Mob(){
		x = y = 8;
		posX = posY = 0;
	}
	
	public void move(int xa, int ya) {
		if (xa != 0 || ya != 0) {
			walkDist++;
			if(xa<0) dir =2;
			if(xa>0) dir =3;
			if(ya<0) dir =1;
			if(ya>0) dir =0;
		}
		if (xa != 0) move2(xa, 0);
		if (ya != 0) move2(0, ya);
	}

	public void move2(int xa, int ya){
		if (xa != 0 && ya != 0) throw new IllegalArgumentException("ne peut bouger que sur 1 axe");
		int xr = 5;
		int yr = 2;
		boolean mayPass = true;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + (c % 2 * 2 - 1) * xr) >> 4;
			int yt = ((y + ya) + (c / 2 * 2 - 1) * yr) >> 4;
			if(!level.getTile(xt, yt).mayPass(level, xt, yt, this)){
				mayPass = false;
			}
		}
		if (mayPass){
			x+=xa;
			y+=ya;
		}
		
	}
}
