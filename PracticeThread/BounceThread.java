package PracticeThread;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class BounceThread {

	public static void main(String[] args) {
		JFrame frame = new BounceFrame2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class BallRunnable implements Runnable {
	public BallRunnable(Ball2 ball, Component component) {
		this.ball = ball;
		this.component = component;
	}
	
	public void run() {
		try {
			for (int i = 0; i < STEPS; ++i) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) { }
	}
	
	public static final int DELAY = 1;
	public static final int STEPS = 1000000;
	
	private Ball2 ball;
	private Component component;
}

class Ball2 {
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

class BounceFrame2 extends JFrame {
	public BounceFrame2() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("BounceTread");
		
		panel = new BallPanel2();
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
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						Ball2 ball = new Ball2();
						panel.add(ball);
						Runnable r = new BallRunnable(ball, panel);
						Thread t = new Thread(r);
						t.start();
						Thread.sleep(50);
					} catch(Exception e) { }
				}
			}
		}).start();
		
		
	}
	
	
	public static final int DEFAULT_WIDTH = 731, DEFAULT_HEIGHT = 400;
	private BallPanel2 panel;
}

class BallPanel2 extends JPanel {
	public void add(final Ball2 b) {
		balls.add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for (Ball2  b : balls)
			g2.fill(b.getShape());
	}
	
	private ArrayList<Ball2> balls = new ArrayList<Ball2>();
}
