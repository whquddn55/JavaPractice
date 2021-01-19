package Practice10;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.*;

import javax.swing.*;

public class ThreadBallBounce_Advanced {
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static class MainFrame extends JFrame {
		public MainFrame() {
			setTitle("ThreadBallBounce_Advanced");
			setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			
			
			MainPanel mainPanel = new MainPanel();
			add(mainPanel ,BorderLayout.CENTER);
			add(new ButtonPanel(mainPanel), BorderLayout.SOUTH);
		}
		
		public static final int DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 400;
	}
	
	private static class MainPanel extends JPanel {
		public void addBall() {
			Ball ball = new Ball(this);
			balls.add(ball);
			
			Thread t = new Thread(new BallRunnable(ball, this));
			t.start();
			threads.add(t);
		}
		public void deleteBall() {
			threads.removeFirst().interrupt();
			balls.removeFirst();
			repaint();
		}
		public void deleteAllBall() {
			while(!threads.isEmpty())
				threads.removeLast().interrupt();
			balls.clear();
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			for (Ball ball : balls)
				g2.fill(ball.getShape());
		}
		
		private LinkedList<Ball> balls = new LinkedList<Ball>();
		private LinkedList<Thread> threads = new LinkedList<Thread>();
		
		class BallRunnable implements Runnable {
			public BallRunnable(Ball ball, MainPanel panel) {
				this.ball = ball;
				this.panel = panel;
			}
			public void run() {
				while (true) {
					try {
						ball.move();
						Thread.sleep(50);
						panel.repaint();
					} catch (InterruptedException e) { 
						panel.repaint(); break;
					}
				}
			}
		
			private Ball ball;
			private MainPanel panel;
		}
	}
	
	private static class ButtonPanel extends JPanel {
		public ButtonPanel(MainPanel mainPanel) {
			this.mainPanel = mainPanel;
			add(new JButton(new AbstractAction("Add") {
				public void actionPerformed(ActionEvent event) {
					mainPanel.addBall();
				}
			}));
			add(new JButton(new AbstractAction("Delete") {
				public void actionPerformed(ActionEvent event) {
					mainPanel.deleteBall();
				}
			}));
			add(new JButton(new AbstractAction("Delete All") {
				public void actionPerformed(ActionEvent event) {
					mainPanel.deleteAllBall();
				}
			}));
			add(new JButton(new AbstractAction("Close") {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
			}));
		}
		
		private MainPanel mainPanel;
	}
	
	private static class Ball {
		public Ball(JPanel panel) {
			this.panel = panel;
		}
		
		public void move() {
			int minX = (int) panel.getBounds().getMinX();
			int maxX = (int) panel.getBounds().getMaxX();
			int minY = (int) panel.getBounds().getMinY();
			int maxY = (int) panel.getBounds().getMaxY();
			
			x += dx;
			y += dy;
			
			if (x < minX) {
				x = minX;
				dx = -dx;
			}
			if (x + SIZE > maxX) {
				x = maxX - SIZE;
				dx = -dx;
			}
			if (y < minY) {
				y = minY;
				dy = -dy;
			}
			if (y + SIZE > maxY) {
				y = maxY - SIZE;
				dy = -dy;
			}
		}
		
		public Ellipse2D getShape() {
			return new Ellipse2D.Double(x, y, SIZE, SIZE);
		}
		
		private JPanel panel;
		private int x = 0, y = 0, dx = 5, dy = 5;
		public static final int SIZE = 15;
	}
}

