package Ping_Pong;
import java.awt.*;
import java.awt.event.*;

//This class handles the functionality and design of the paddles
public class PongPaddle extends Rectangle {
	
	int id;
	int y_velocity;
	int speed = 10;
	
	PongPaddle(int x, int y, int paddle_width, int paddle_height, int id){
		super(x, y, paddle_width, paddle_height);
		this.id = id;
		
	}
	
	// Method to handle when certain keys are pressed
	public void keyPressed(KeyEvent ke) {
		switch(id) {
		case 1:
			if (ke.getKeyCode()== KeyEvent.VK_W) {
				setY(-speed);
				move();
			}
			
			if (ke.getKeyCode()== KeyEvent.VK_S) {
				setY(speed);
				move();
			}
			break;
		
		case 2:
			if (ke.getKeyCode()== KeyEvent.VK_UP) {
				setY(-speed);
				move();
			}
			
			if (ke.getKeyCode()== KeyEvent.VK_DOWN) {
				setY(speed);
				move();
			}
			
			break;
		}
	}
	
	// Method to handle when certain keys are released
	public void keyReleased(KeyEvent ke) {
		switch(id) {
		case 1:
			if (ke.getKeyCode()== KeyEvent.VK_W) {
				setY(0);
				move();
			}
			
			if (ke.getKeyCode()== KeyEvent.VK_S) {
				setY(0);
				move();
			}
			break;
		
		case 2:
			if (ke.getKeyCode()== KeyEvent.VK_UP) {
				setY(0);
				move();
			}
			
			if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
				setY(0);
				move();
			}
			
			break;
		}
	}
	
	// Method to set the y direction of the paddle
	public void setY(int y) {
		y_velocity = y;
	}
	
	public void move() {
		y = y + y_velocity;
	}
	
	// Method to draw the paddle
	public void draw(Graphics g) {
		if(id==1) {
			g.setColor(Color.blue);
		}
		
		else {
			g.setColor(Color.red);
		}
		
		g.fillRect(x, y, width, height);
	}
}
