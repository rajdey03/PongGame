package Ping_Pong;
import java.awt.*;
import java.util.*;

//This class handles the functionality and design of the ball
public class PongBall extends Rectangle {
	
	Random random;
	int x_velocity;
	int y_velocity;
	int initial_speed = 3;

	PongBall(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int random_x = random.nextInt(2);
		
		if (random_x == 0) {
			random_x--;
		}
		
		setX(random_x*initial_speed);
		
		int random_y = random.nextInt(2);
		
		if (random_y == 0) {
			random_y--;
		}
		
		setY(random_y*initial_speed);
	}
	
	// Method to set the random x direction of the ball
	public void setX(int random_x) {
		x_velocity = random_x;
	}
	
	// Method to set the random y direction of the ball
	public void setY(int random_y) {
		y_velocity = random_y;
	}
	
	public void move() {
		x += x_velocity;
		y += y_velocity;
	}
	
	// Method to draw the ball
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
