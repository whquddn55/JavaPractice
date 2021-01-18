package PracticeSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlafTest {
	public static void main(String[] args) {
		PlafFrame frame = new PlafFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class PlafFrame extends JFrame {
	private static int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 400;
	public PlafFrame() {
		setTitle("PlafTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		PlafPanel panel = new PlafPanel();
		add(panel);
	}
}

class PlafPanel extends JPanel {
	public PlafPanel() {
		/*JButton metalButton = new JButton("Metal");
		JButton nimbusButton = new JButton("Nimbus");
		JButton motifButton = new JButton("CDE/Motif");
		JButton windowsButton = new JButton("WindowsButton");
		JButton wClassicButton = new JButton("Windows Classic");
		
		add(metalButton); add(nimbusButton); add(motifButton); add(windowsButton); add(wClassicButton);*/
		
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos)
			makeButton(info.getName(), info.getClassName());
	}
	
	private void makeButton(final String name, final String className) {
		JButton button = new JButton(name);
		add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					UIManager.setLookAndFeel(className);
					SwingUtilities.updateComponentTreeUI(PlafPanel.this);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 }