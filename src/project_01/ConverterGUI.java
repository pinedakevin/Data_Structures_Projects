package project_01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Graphical User Interface for the expression converter. Accepts a user input
 * and provides an output dependent on the option the user chooses.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 1
 * @Date 05/24/2023
 */
public class ConverterGUI extends JFrame {
	private JLabel EnterLabel;
	private JLabel ResultLabel;
	private JButton prefixButton;
	private JButton postfistButton;
	private JTextField inputTextField;
	private JTextField outputTextField;
	private String input;

	public ConverterGUI() {

		// Creating panel
		JPanel content = new JPanel();
		content.setLayout(null);

		// adding panel to frame
		setContentPane(content);

		// Creating Labels
		EnterLabel = new JLabel("Enter Expression");
		EnterLabel.setBounds(25, 14, 121, 25);

		ResultLabel = new JLabel("Result");
		ResultLabel.setBounds(25, 118, 78, 20);

		// Creating Buttons
		prefixButton = new JButton("Prefix to PostFix");
		prefixButton.setBounds(51, 59, 144, 25);

		postfistButton = new JButton("PostFix to Prefix");
		postfistButton.setBounds(210, 59, 144, 25);

		// Creating TextField
		inputTextField = new JTextField();
		inputTextField.setBounds(154, 14, 164, 25);
		inputTextField.setColumns(10);

		outputTextField = new JTextField();
		outputTextField.setEditable(false);
		outputTextField.setBounds(83, 116, 164, 25);
		outputTextField.setColumns(10);

		// Adding Components
		content.add(EnterLabel);
		content.add(ResultLabel);
		content.add(prefixButton);
		content.add(postfistButton);
		content.add(inputTextField);
		content.add(outputTextField);

		// Frame setup
		setTitle("Expression Converter");
		setSize(430, 217);
		setLocation(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Action Listeners
		postfistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = inputTextField.getText();
				safetyCheck(input, true);
			}
		});

		prefixButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = inputTextField.getText();
				safetyCheck(input, false);
			}
		});
	}

	/**
	 * Helper method to refactor similar functionality. Checks if the conversion is
	 * valid, otherwise throws the proper exception.
	 * 
	 * @param input
	 * @param postOrPre
	 */
	private void safetyCheck(String input, boolean postOrPre) {
		ExpressionConverter converter = new ExpressionConverter(input);
		try {
			if (postOrPre) {
				String output = converter.convertToPostFix();
				outputTextField.setText(output);
			} else {
				String output = converter.convertToPreFix();
				outputTextField.setText(output);
			}
		} catch (StackSyntaxError ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Stack syntax eror. Message: " + ex.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (EmptyStackException ex) {
			JOptionPane.showMessageDialog(new JFrame(), "Stack is empty! Attempted to pop.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JFrame(), "System Issue. Message: " + ex.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new ConverterGUI();
	}
}
