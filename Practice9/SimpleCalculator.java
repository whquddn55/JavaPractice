package Practice9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleCalculator {
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class MainFrame extends JFrame {
	public MainFrame() {
		setTitle("Simple Calculator");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		ButtonPanel panel = new ButtonPanel();
		panel.setLayout(new GridLayout(4,4));
		add(panel);
	}
	
	final static int DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 400;
}

class ButtonPanel extends JPanel {
	private StringBuilder expression = new StringBuilder();
	
	public ButtonPanel() {
		for (int i = 1; i < 10; ++i)
			add(new JButton(new NumberAction(String.valueOf(i))));
		add(new JButton(new NumberAction(String.valueOf(0))));
		
		add(new JButton(new OperateAction("+")));
		add(new JButton(new OperateAction("-")));
		add(new JButton(new OperateAction("*")));
		add(new JButton(new OperateAction("/")));
		
		add(new JButton(new AbstractAction("Ok") {
			public void actionPerformed(ActionEvent event) {
				System.out.println(expression.toString());
				System.out.println(calculate());
				
				expression.delete(0, expression.length());
				
			}
		}));
		add(new JButton(new AbstractAction("C") {
			public void actionPerformed(ActionEvent event) {
				expression.delete(0, expression.length());
			}
		}));
	}
	
	public int calculate() {
		boolean isLastIndexNumber = true;
		int result = 0;
		
		int num1 = 0;
		int num2 = -1;
		char operation = '0';
		for (int i = 0; i < expression.length(); ++i ) {
			char c = expression.charAt(i);
			
			if (Character.isDigit(c)) {
				if (num2 == -1)
					num1 = num1 * 10 + c - '0';
				else
					num2 = num2 * 10 + c - '0';
			}
			else {
				num2 = 0;
				operation = c;
			}
		}
		switch(operation) {
		case '+' :
			result = num1 + num2;
			break;
		case '-' :
			result = num1 - num2;
			break;
		case '*' :
			result = num1 * num2;
			break;
		case '/' :
			result = num1 / num2;
			break;
		}
		
		return result;
	}
	
	class NumberAction extends AbstractAction {
		public NumberAction(final String name) {
			putValue(Action.NAME, name);
		}
		public void actionPerformed(ActionEvent event) {
			String number = ((JButton)event.getSource()).getText();
			expression.append(number);
		}
	}

	class OperateAction extends AbstractAction {
		public OperateAction(final String name) {
			putValue(Action.NAME, name);
		}
		
		public void actionPerformed(ActionEvent event) {
			String operation = ((JButton)event.getSource()).getText();
			expression.append(operation);
		}
	}
}

