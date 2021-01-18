package PracticeSwing;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MenuTest {
	public static void main(String[] args) {
		MenuFrame frame = new MenuFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class MenuFrame extends JFrame {
	public MenuFrame() {
		setTitle("MenuTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = fileMenu.add(new TestAction("New"));
		JMenuItem openItem = fileMenu.add(new TestAction("Open"));
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		
		fileMenu.addSeparator();
		
		saveAction = new TestAction("Save");
		JMenuItem saveItem = fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveAsAction = new TestAction("Save As");
		JMenuItem saveAsItem = fileMenu.add(saveAsAction);
		
		fileMenu.addSeparator();
		
		fileMenu.add(new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		readonlyItem = new JCheckBoxMenuItem("Read-only");
		readonlyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean saveOk = !readonlyItem.isSelected();
				saveAction.setEnabled(saveOk);
				saveAsAction.setEnabled(saveOk);
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
		
		group.add(insertItem);
		group.add(overtypeItem);
		
		Action cutAction = new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
		Action copyAction = new TestAction("Copy");
		copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
		Action pasteAction = new TestAction("Paste");
		pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));
		
		JMenu editMenu = new JMenu("edit");
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		
		JMenu optionMenu = new JMenu("Options");
		optionMenu.add(readonlyItem);
		optionMenu.addSeparator();
		optionMenu.add(insertItem);
		optionMenu.add(overtypeItem);

		editMenu.addSeparator();
		editMenu.add(optionMenu);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		JMenuItem indexItem = new JMenuItem("Index");
		indexItem.setMnemonic('I');
		helpMenu.add(indexItem);
		
		Action aboutAction = new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		helpMenu.add(aboutAction);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		popup = new JPopupMenu();
		popup.add(cutAction); 
		popup.add(copyAction);
		popup.add(pasteAction);
		
		JPanel panel = new JPanel();
		panel.setComponentPopupMenu(popup);
		add(panel);
	}
	
	private final int DEFAULT_WIDTH = 400, DEFAULT_HEIGHT = 400;
	
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
}

class TestAction extends AbstractAction {
	public TestAction(String name) {
		super(name);
	}
	
	public void actionPerformed(ActionEvent event) {
		System.out.println(getValue(Action.NAME) + " selected");
	}
}
