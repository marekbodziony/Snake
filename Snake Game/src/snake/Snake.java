package snake;

import java.awt.Color;
import processing.core.PApplet;

public class Snake{
	
	PApplet p;
	int grid;
	Color color = new Color(50, 40, 120); 
	
	int x = 100;
	int y = 100;
	int xSpeed;
	int ySpeed = 0;
	
	int size = 1;	
	int maxSize = 100;								// maximum snake size is set to 100
	int [][] snakeTrack = new int[2][maxSize];		
	
	public Snake(PApplet parent, int grid){
		this.p = parent;
		this.grid = grid;
		xSpeed = grid;
	};
	
	public void display(){
		p.fill(color.getRGB());
		p.stroke(255);
		for (int i = 0; i < size; i++){
			p.rect(snakeTrack[0][i], snakeTrack[1][i], grid, grid);
		}
	}
	
	public void move(){
		
		// move with specified speed
		x += xSpeed;
		y += ySpeed;
		
		// record snake track
		recordSnakeTrack();
				
		// game without borders
		if (x >= p.width){
			x = 0;
			snakeTrack[0][0] = x;
		}
		if (x < 0){
			x = p.width - grid;
			snakeTrack[0][0] = x;
		}
		if (y >= p.height){
			y = 0;
			snakeTrack[1][0] = y;
		}
		if (y < 0){
			y = p.height - grid;
			snakeTrack[1][0] = y;
		}
		
		// if DOWN is pressed and snake moves horizontally it turns down 
		if (p.keyCode == processing.core.PApplet.DOWN && ySpeed == 0 ){
			xSpeed = 0;
			ySpeed = grid;
		}
		// if UP is pressed and snake moves horizontally it turns down
		else if (p.keyCode == processing.core.PApplet.UP && ySpeed == 0){
			xSpeed = 0;
			ySpeed = -grid;			
		}
		// if LEFT is pressed and snake moves vertically it turns down
		else if (p.keyCode == processing.core.PApplet.LEFT && xSpeed == 0){
			xSpeed = -grid;
			ySpeed = 0;
		}
		// if RIGHT is pressed and snake moves vertically it turns down
		else if (p.keyCode == processing.core.PApplet.RIGHT && xSpeed == 0){
			xSpeed = grid;
			ySpeed = 0;
		}		
	}
	
	private void recordSnakeTrack(){
		if (size > 1){
			for (int i = 0; i < size; i++){
				if (size-i > 1){
					snakeTrack[0][size-1-i] = snakeTrack[0][size-2-i];
					snakeTrack[1][size-1-i] = snakeTrack[1][size-2-i];	
				}				
			}
		}
		snakeTrack[0][0] = x;
		snakeTrack[1][0] = y;	
	}
	
	public void eat(Food food){
		if (x == food.x && y == food.y ){
			size +=1;
			food.eaten = true;
		}
	}
		
	public boolean dead(){
		// check if snake hit the edge it's and of the game (options with borders)
//		if (x >= p.width - grid || x <= 0 || y >= p.height - grid || y <= 0){
//			xSpeed = 0;
//			ySpeed = 0;
//			color = new Color(179, 45, 45);
//			return true;
//			}
		
		// check if snake eat himself
		for (int i = 1; i < size; i++){
			if (x == snakeTrack[0][i] && y == snakeTrack[1][i]){
				xSpeed = 0;
				ySpeed = 0;
				color = new Color(179, 45, 45);
				return true;
			}
		}
		return false;
	}
}
