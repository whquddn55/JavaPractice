package PracticeThread;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class Bounce {

	public static void main(String[] args) {
		JFrame frame = new BounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class Ball {
	public void move(Rectangle2D bounds) {
		x += dx; y += dy;
		if (x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE > bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = - dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE > bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
	
	private static final int XSIZE = 15, YSIZE = 15;	
	private double x = 0, y = 0, dx = 1, dy = 1;
}

class BounceFrame extends JFrame {
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");
		
		panel = new BallPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addBall();
			}
		});
		
		addButton(buttonPanel, "close", new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addButton(final JPanel panel, final String buttonName, final ActionListener actionListener) {
		JButton newButton = new JButton(buttonName);
		newButton.addActionListener(actionListener);
		panel.add(newButton);
	}
	
	public void addBall() {
		try {
			Ball ball = new Ball();
			panel.add(ball);
			for (int i = 0; i < STEPS; ++i) {
				ball.move(panel.getBounds());
				panel.paint(panel.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) { }
	}
	
	public static final int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 400;
	public static final int DELAY = 3;
	public static final int STEPS = 1000;
	private BallPanel panel;
}

class BallPanel extends JPanel {
	public void add(final Ball b) {
		balls.add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for (Ball  b : balls)
			g2.fill(b.getShape());
	}
	
	private ArrayList<Ball> balls = new ArrayList<Ball>();
}
