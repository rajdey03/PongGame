package Ping_Pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// This class represents as the actual content of the game, where the logic is implemented
// JPanel is a container that organizes the components within the frame
public class PongGamePanel extends JPanel implements Runnable{
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25; 
	static final int PADDLE_HEIGHT = 100;
	
	// Thread for game loop
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	PongPaddle paddle1;
	PongPaddle paddle2;
	PongBall ball;
	PongScore score;
	
	PongGamePanel(){
		newPaddles();
		newBall();
		score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); // Retrieves data from keyboard
		this.addKeyListener(new AL()); // Retrieves the input specific keyboard inputs
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new PongBall((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void newPaddles() {
		paddle1 = new PongPaddle(0, (GAME_HEIGHT/2-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PongPaddle((GAME_WIDTH - PADDLE_WIDTH), (GAME_HEIGHT/2-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
		
	}
	
	// Method to create a canvas for the game
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	// Method to draw the game elements. Each game component class already has their own draw method implementation
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	// Method to check collisions between the ball, the paddles and the walls
	public void checkCollision() {
		// Lines 75 to 81 checks if ball hits the top and bottom walls
		if(ball.y <= 0) {
			ball.setY(-ball.y_velocity);
		}
		
		if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setY(-ball.y_velocity);
		}
		
		//Lines 84 to 114 checks if ball hits any of the paddles
		if (ball.intersects(paddle1)) {
			ball.x_velocity = Math.abs(ball.x_velocity);
			ball.x_velocity++;
			
			if (ball.y_velocity > 0) {
				ball.y_velocity++;
			}
			
			else {
				ball.y_velocity--;
			}
			
			ball.setX(ball.x_velocity);
			ball.setY(ball.y_velocity);
		}
		
		if (ball.intersects(paddle2)) {
			ball.x_velocity = Math.abs(ball.x_velocity);
			ball.x_velocity++;
			
			if (ball.y_velocity > 0) {
				ball.y_velocity++;
			}
			
			else {
				ball.y_velocity--;
			}
			
			ball.setX(-ball.x_velocity);
			ball.setY(ball.y_velocity);
		}
		
		// Lines 117 to 131 checks if the paddles are hitting the top and bottom walls
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}
		
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}
		
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		// Lines 134 to 144 awards points to either player, depending on which wall the ball crosses. 
		// After ball crosses the left or right wall, new paddles and ball are created, and score is updated.
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		
		if(ball.x > GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	// Method of the Runnable interface that creates the game loop.
	public void run() {
		long lastTime = System.nanoTime();
		double numTicks = 60;
		double ns = 1000000000 / numTicks;
		double delta = 0;
		
		while (true) {
			
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	// Inner class to handle keyboard inputs
	public class AL extends KeyAdapter{
		
		public void keyPressed(KeyEvent ke) {
			paddle1.keyPressed(ke);
			paddle2.keyPressed(ke);
		}
		
		public void keyReleased(KeyEvent ke) {
			paddle1.keyReleased(ke);
			paddle2.keyReleased(ke);
		}
	}
}
