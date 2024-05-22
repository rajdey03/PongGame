package Ping_Pong;
import java.awt.*;

// This class displays and updates the scores
public class PongScore extends Rectangle {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;
	
	PongScore(int width, int height){
		PongScore.GAME_WIDTH = width;
		PongScore.GAME_HEIGHT = height;
	}
	
	// Method to draw the score and the a simple dashed line to divide the scores for clear display.
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		// Creates the dash line
        Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{25}, 30);
        g2d.setStroke(dashed);
        g2d.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
	}
}
