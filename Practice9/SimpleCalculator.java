package Practice9;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.Vector;

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
		add(new JButton(new OperateAction("(")));
		add(new JButton(new OperateAction(")")));
		
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
		Vector<Integer> postfixExpression = new Vector<Integer>();
		Stack<Integer> s = new Stack<Integer>();
		
		boolean wasDigit = false;
		for(int i = 0; i < expression.length(); ++i) {
			int c = expression.charAt(i);
			if (Character.isDigit(c)) {
				c -= '0';
				int lastIndex = postfixExpression.size() - 1;
				if (wasDigit)
						postfixExpression.set(lastIndex, (postfixExpression.elementAt(lastIndex) * 10 + c));
				else 
					postfixExpression.add(c);
				wasDigit = true;
			}
			else {
				wasDigit = false;
				if (s.isEmpty() || (c == '('))
					s.push(c);
				else if (c == ')') {
					while(true) {
						int top = s.pop();
						if (top == '(')
							break;
						if (top == '+')
							top = -1;
						else if (top == '-')
							top = -2;
						else if (top == '*')
							top = -3;
						else
							top = -4;
						postfixExpression.add(top);
					}
				}
				else if (c == '+' || c == '-'){
					s.push(c);
				}
				else {
					int top = s.pop();
					s.push(top);
					if (top == '+')
						top = -1;
					else if (top == '-')
						top = -2;
					else if (top == '*')
						top = -3;
					else
						top = -4;
					while(!s.empty() && top == -1 && top == -2) 
						postfixExpression.add(s.pop());
					s.push(c);
				}
			}
		}
		
		while(!s.isEmpty()) {
			int top = s.pop();
			if (top == '+')
				top = -1;
			else if (top == '-')
				top = -2;
			else if (top == '*')
				top = -3;
			else
				top = -4;
			postfixExpression.add(top);
		}
		
		s.clear();
		for (int t : postfixExpression) {
			if (t >= 0)
				s.push(t);
			else {
				int num1 = s.pop(), num2 = s.pop();
				int temp = 0;
				 if (t == -1)
					temp = num1 + num2;
				else if (t == -2)
					temp = num2 - num1;
				else if (t == -3)
					temp = num1 * num2;
				else
					temp = num2 / num1;
				 s.push(temp);
			}
		}
		
		return s.pop();
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

