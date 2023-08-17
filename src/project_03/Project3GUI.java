package project_03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI for a binary tree using a given String input.
 * 
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 3
 * @Date 06/12/2023
 */
public class Project3GUI extends JFrame {
	private JLabel EnterLabel;
	private JLabel OutputLabel;
	private JButton MakeTreeButton;
	private JButton balanceButton;
	private JButton fullButton;
	private JButton properButton;
	private JButton heightButton;
	private JButton nodesButton;
	private JButton inOrderButton;
	private JTextField inputTextField;
	private JTextField outputTextField;
	private String input;
	private BinaryTree bt;

	public Project3GUI() {

		// Creating panel
		JPanel content = new JPanel();
		content.setLayout(null);

		// adding panel to frame
		setContentPane(content);

		// Creating Labels
		EnterLabel = new JLabel("Enter Tree:");
		EnterLabel.setBounds(76, 14, 70, 25);

		OutputLabel = new JLabel("Output:");
		OutputLabel.setBounds(76, 118, 47, 20);

		// Creating Buttons
		MakeTreeButton = new JButton("Make Tree");
		MakeTreeButton.setBounds(0, 59, 101, 25);

		balanceButton = new JButton("Is Balanced?");
		balanceButton.setBounds(103, 59, 113, 25);

		fullButton = new JButton("Is Full?");
		fullButton.setBounds(214, 59, 101, 25);

		properButton = new JButton("Is Proper?");
		properButton.setBounds(314, 59, 113, 25);

		heightButton = new JButton("Height");
		heightButton.setBounds(406, 59, 113, 25);

		nodesButton = new JButton("Nodes");
		nodesButton.setBounds(518, 59, 101, 25);

		inOrderButton = new JButton("Inorder");
		inOrderButton.setBounds(618, 59, 103, 25);

		// Creating TextField
		inputTextField = new JTextField();
		inputTextField.setBounds(154, 14, 305, 25);
		inputTextField.setColumns(10);

		outputTextField = new JTextField();
		outputTextField.setEditable(false);
		outputTextField.setBounds(154, 116, 305, 25);
		outputTextField.setColumns(10);

		// Action Handlers
		MakeTreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = inputTextField.getText();
				try {
					bt = new BinaryTree(input);
					JOptionPane.showMessageDialog(new JFrame(), "Tree made Successfully!", "SUCCESS",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (InvalidTreeSyntax ex) {
					JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		balanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText("" + bt.isBalanced());
			}
		});

		fullButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText("" + bt.isFull());
			}
		});

		properButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText("" + bt.isProper());
			}
		});

		heightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText("" + bt.treeHeight());
			}
		});

		nodesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText("" + bt.getNumNodes());
			}
		});

		inOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputTextField.setText(bt.InOrder());
			}
		});

		// Adding Components
		content.add(EnterLabel);
		content.add(OutputLabel);
		content.add(MakeTreeButton);
		content.add(balanceButton);
		content.add(fullButton);
		content.add(properButton);
		content.add(heightButton);
		content.add(nodesButton);
		content.add(inOrderButton);
		content.add(inputTextField);
		content.add(outputTextField);

		// Frame setup
		setTitle("Binary Tree Categorizer");
		setSize(735, 220);
		setLocation(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Project3GUI();
	}
}
