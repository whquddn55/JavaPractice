package Practice9;

import java.awt.event.*;

import javax.swing.*;

public class SwingTest {
	public static void main(String[] args) {
		SwingFrame frame = new SwingFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class SwingFrame extends JFrame {
	public SwingFrame() {
		setTitle("SwingTest");
		setSize(800, 300);
		
		ButtonPanel2 panel = new ButtonPanel2();
		add(panel);
		pack();
	}
}

class ButtonPanel2 extends JPanel {
	public ButtonPanel2() {
		add(new JButton(new ButtonAction("Add", "Add element")));
		add(new JButton(new ButtonAction("Remove First", "Remove element at first")));
		add(new JButton(new ButtonAction("Remove Last", "Remove element at last")));
		add(new JButton(new ButtonAction("Remove All", "Remove element all")));
		add(new JButton(new ButtonAction("Quit", "ByeBye")));
	}
	
	private class ButtonAction extends AbstractAction {
		public ButtonAction(final String name,final String tooltip) {
			putValue(AbstractAction.NAME, name);
			putValue(AbstractAction.SHORT_DESCRIPTION, tooltip);
		}
		public void actionPerformed(ActionEvent event) {
			if(getValue(AbstractAction.NAME).equals("Quit")) {
				System.exit(0);
			}
			else {
				JOptionPane.showMessageDialog(null, getValue(AbstractAction.NAME));
			}
		}
	}
}
