package snake;

import java.awt.Color;

import processing.core.PApplet;

public class TheSnakeGame extends PApplet{
	
	private Snake snake;
	private Food food;
	private int grid = 20;
	private boolean running = true;
	
	public static void main (String[] args){
		
		PApplet.main("snake.TheSnakeGame");	
	}
	
	public void settings(){
		size(600,600);
	}
	
	public void setup(){
		snake = new Snake(this, grid);		
		food = new Food(this, grid);
		frameRate(8);
	}
	
	public void draw(){
		background(255);
		fill(new Color(208,201,195).getRGB());
		textSize(13); ;
		text("Author: Marek Bodziony", 10, height - 10);
		
		if (running){
			if (!snake.dead()) snake.move();
			snake.eat(food);
			food.display();
		}
		snake.display();
	}

}
