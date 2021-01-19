package Practice10;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.*;

public class ThreadBallBounce {
	public static void main(String[] args) {
		JFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	private static class MainFrame extends JFrame {
		public MainFrame() {
			setTitle("ThreadBAllBounce");
			setSize(700, 500);
			
			mainPanel = new MainPanel();
			add(mainPanel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new ButtonPanel();
			add(buttonPanel, BorderLayout.SOUTH);
			
			JMenuBar menubar = new JMenuBar();
			JMenu speedMenu = new JMenu("Speed");
			JMenuItem faster = new JMenuItem(new AbstractAction("Faster") {
				public void actionPerformed(ActionEvent e) {
					mainPanel.ballSpeedUp();
				}
			});
			JMenuItem slower = new JMenuItem(new AbstractAction("Slower") {
				public void actionPerformed(ActionEvent e) {
					mainPanel.ballSpeedDown();
				}
			});
			
			speedMenu.add(faster);
			speedMenu.add(slower);
			menubar.add(speedMenu);
			setJMenuBar(menubar);
		}
		public final MainPanel mainPanel;
	}

	private static class Ball {
		public Ball(int speed, Component component) {
			this.speed = dx = dy = speed;
			this.component = component;
		}
		
		public void move() {
			int maxX = (int) component.getBounds().getMaxX();
			int minX = (int) component.getBounds().getMinX();
			int maxY = (int) component.getBounds().getMaxY();
			int minY = (int) component.getBounds().getMinY();
			x += dx;
			y += dy;
			
			if (x < minX) {
				x = 0;
				dx = -dx;
			}
			if (x + size >= maxX) {
				x = maxX - size;
				dx = -dx;
			}
			if (y < minY) {
				y = 0;
				dy = -dy;
			}
			if (y + size >= maxY) {
				y = maxY - size;
				dy = -dy;
			}
			component.repaint();
		}
		
		public Ellipse2D getShape() {
			return new Ellipse2D.Double(x, y, size, size);
		}
		
		private final int size = 15;
		private final int speed;
		private final Component component;
		private int x = 0, y = 0, dx, dy;
	}

	private static class BallRun implements Runnable {
		public BallRun(final Ball ball) {
			this.ball = ball;
		}
		public void run() {
			while(true) {
				try {
					ball.move();
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private final Ball ball;
	}

	private static class MainPanel extends JPanel {
		public MainPanel() {
			
		}
		
		public void addBall() {
			Ball ball = new Ball(ballSpeed, this);
			balls.add(ball);
			Thread t = new Thread(new BallRun(ball));
			t.start();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			for(Ball ball : balls)
				g2.fill(ball.getShape());
		}
		
		public void ballSpeedUp() {
			ballSpeed++;
		}
		public void ballSpeedDown() {
			ballSpeed--;
			ballSpeed = Math.max(1, ballSpeed);
		}
		
		private int ballSpeed = 1;
		private ArrayList<Ball> balls = new ArrayList<Ball>();
	}

	private static class ButtonPanel extends JPanel {
		public ButtonPanel() {
			add(new JButton(new AddAction("Add 1", 1)));
			add(new JButton(new AddAction("Add 2", 2)));
			add(new JButton(new AbstractAction("Close") {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}));
		}
		
		class AddAction extends AbstractAction {
			public AddAction(String name, int num) {
				putValue(AbstractAction.NAME, name);
				this.num = num;
			}
			
			public void actionPerformed(ActionEvent event) {
					(new Thread(new Runnable() {
						public void run() {
							try {
								for (int i = 0; i < num; ++i) {
										addBall();
										Thread.sleep(500);
									}
							} catch(InterruptedException e) {
								
							}
						}})).start();
				}
			
			private void addBall() {
				((MainFrame)(getParent().getParent().getParent().getParent())).mainPanel.addBall();
			}
			
			private int num = 0;
		}
	}
}

