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
	
	//int player = 1;
	int size = 1;
	int [][] snakeTrack = new int[2][100];		// maximum snake size is set to 100
	
	int up, down, left, right; 					// needed for 2 player game

	
	public Snake(PApplet parent, int grid){
		this.p = parent;
		this.grid = grid;
		xSpeed = grid;
	};
	
	public void display(){
		p.fill(color.getRGB());
		//if(player != 1) color = new Color(168,93,27);
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
		
		
		//if (player == 1){
			up = processing.core.PApplet.UP;
			down = processing.core.PApplet.DOWN;
			left = processing.core.PApplet.LEFT;
			right = processing.core.PApplet.RIGHT;
//		}
//		if (player == 2){
//			up = 87;
//			down = 83;
//			left = 65;
//			right = 68;
//		}
		
		// if DOWN is pressed and snake moves horizontally it turns down 
		if (p.keyCode == down && ySpeed == 0 ){
			xSpeed = 0;
			ySpeed = grid;
		}
		// if UP is pressed and snake moves horizontally it turns down
		if (p.keyCode == up && ySpeed == 0){
			xSpeed = 0;
			ySpeed = -grid;			
		}
		// if LEFT is pressed and snake moves vertically it turns down
		if (p.keyCode == left && xSpeed == 0){
			xSpeed = -grid;
			ySpeed = 0;
		}
		// if RIGHT is pressed and snake moves vertically it turns down
		if (p.keyCode == right && xSpeed == 0){
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
		if (x >= p.width - grid || x <= 0 || y >= p.height - grid || y <= 0){
//			xSpeed = 0;
//			ySpeed = 0;
//			color = new Color(179, 45, 45);
//			return true;
			}
		
		// check if snake eat himself
		for (int i = 1; i < size; i++){
			if (x == snakeTrack[0][i] && y == snakeTrack[1][i]){
				xSpeed = 0;
				ySpeed = 0;
				color = new Color(179, 45, 45);
				//if (player == 2) color = new Color(250, 123, 11);
				return true;
			}
		}
		return false;
	}

	public void eatAnotherSnake(Snake snake2){
		for (int i = 0; i < snake2.size; i++){
			if (x == snake2.snakeTrack[0][i] - xSpeed && y == snake2.snakeTrack[1][i] - ySpeed){
				xSpeed = 0;
				ySpeed = 0;
				color = new Color(179, 45, 45);
				//if (player == 2) color = new Color(250, 123, 11);
			}
		}
	}
}
