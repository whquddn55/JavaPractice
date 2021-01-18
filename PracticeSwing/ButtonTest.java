package PracticeSwing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest {
	public static void main(String[] args) {
		ButtonFrame frame = new ButtonFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class ButtonFrame extends JFrame {
	public ButtonFrame() {
		setTitle("Button Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		ButtonPanel panel = new ButtonPanel();
		add(panel);
	}
	
	private final int DEFAULT_WIDTH = 400;
	private final int DEFAULT_HEIGHT = 400;
}

class ButtonPanel extends JPanel {
	public ButtonPanel() {
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		
		add(yellowButton);
		add(blueButton);
		add(redButton);
		
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}
}

class ColorAction implements ActionListener {
	private Color backgroundColor;
	public ColorAction(final Color c) {
		backgroundColor = c;
	}
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		button.getParent().setBackground(backgroundColor);
	}
}
