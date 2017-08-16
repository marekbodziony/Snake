package snake;

import processing.core.PApplet;

public class Food {
	
	PApplet p;
	
	int x;
	int y;
	int grid;
	boolean eaten = false;
	
	public Food(PApplet p, int grid){
		this.p = p;
		this.grid = grid;
		x = (int)p.random(p.width / grid) * grid;
		y = (int)p.random(p.height / grid) * grid;
	}
	
	public void display(){
		p.fill(0,255,0);
		p.rect(x, y, grid, grid);
		if (eaten){
			x = (int)p.random(p.width / grid) * grid;
			y = (int)p.random(p.height / grid) * grid;
			eaten = false;
		}
	}

}
