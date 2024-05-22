package Ping_Pong;
import java.awt.*;
import javax.swing.*;

// Represents the main frame of the game
// JFrame is a container for other GUI components
public class PongGameFrame extends JFrame{
	
	PongGamePanel panel;
	
	PongGameFrame(){
		panel = new PongGamePanel();
		this.add(panel);
		this.setTitle("Ping Pong Game");
		this.setResizable(false); // Putting it false disallows the user to resize the frame.
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack(); // Size automatically changes relative to the content size of the frame
		this.setVisible(true);
		this.setLocationRelativeTo(null); // Centers the frame relative to the screen
	}
}
