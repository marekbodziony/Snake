package snake;

import processing.core.PApplet;

public class Info {
	
	PApplet p;
	boolean border = true;
	
	public Info(PApplet p){
		this.p = p;
	}
	
	public void show (boolean menu){
		if (menu){
			p.fill(56);
			p.textSize(13);
			p.text("Author: Marek Bodziony", 10, p.height - 10);
		}
	}

}
