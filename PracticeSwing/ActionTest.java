package PracticeSwing;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class ActionTest {
	public static void main(String[] args) {
		ActionFrame frame = new ActionFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class ActionFrame extends JFrame {
	public ActionFrame() {
		setTitle("ActionTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		ActionPanel panel = new ActionPanel();
		add(panel);
	}
	
	private final int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 400;
}

class ActionPanel extends JPanel {
	public ActionPanel() {
		ColorAction2 yellowAction = new ColorAction2("Yellow", new ImageIcon("yellow-ball.gjf"), Color.YELLOW);
		ColorAction2 blueAction = new ColorAction2("Blue", new ImageIcon("blue-ball.gjf"), Color.BLUE);
		ColorAction2 redAction = new ColorAction2("Red", new ImageIcon("red-ball.gjf"), Color.RED);
				
		JButton yellowButton = new JButton(yellowAction);
		JButton blueButton = new JButton(blueAction);
		JButton redButton = new JButton(redAction);
		
		add(yellowButton); add(blueButton); add(redButton);
		
		InputMap imap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		ActionMap amap = getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}
}

class ColorAction2 extends AbstractAction {
	public ColorAction2(final String name, final Icon icon, final Color c) {
		putValue(Action.NAME, name);
		putValue(Action.SMALL_ICON, icon);
		putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
		putValue("color", c);
	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) {
			JButton temp = (JButton)event.getSource();
			temp.getParent().setBackground((Color)getValue("color"));
		}
		else if (event.getSource() instanceof JPanel) {
			((JPanel)event.getSource()).setBackground((Color)getValue("color"));
		}
	}
}