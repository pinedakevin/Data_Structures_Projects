package project_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Represents a converter for postfix and prefix expressions. Provides the
 * opposite expression when inputed. Notifies the user if the syntax is
 * incorrect or if it did not full complete.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 1
 * @Date 05/24/2023
 */
public class ExpressionConverter {
	private String input;
	private ArrayList<String> tokens;
	private Stack<String> reversalStack;
	private Stack<String> operandStack;
	private ArrayList<String> operator;

	/**
	 * Creates the expression converter object that takes in a String - input.
	 * Assigns the operators and tokenizes the String to be used in the main
	 * methods.
	 * 
	 * @param input - String
	 */
	public ExpressionConverter(String input) {
		this.input = input;

		tokens = new ArrayList<>();
		operator = new ArrayList<>(Arrays.asList("/*-+".split("")));
		reversalStack = new Stack<>();
		operandStack = new Stack<>();

		tokenize(input);
	}

	/**
	 * Converts the input String from a Prefix expression to a Postfix expression.
	 * Checks if the syntax is correct otherwise throws the unique StackSyntaxError
	 * exception. Returns the new expression in a String.
	 * 
	 * @return String - Postfix conversion
	 */
	public String convertToPostFix() {
		while (!tokens.isEmpty()) {
			String nextToken = tokens.listIterator().next();
			reversalStack.push(nextToken);
			tokens.remove(tokens.indexOf(nextToken));
		}

		while (!reversalStack.isEmpty()) {
			String newToken = reversalStack.pop();

			if (!operator.contains(newToken)) {
				operandStack.push(newToken);
			} else {
				String operand1 = operandStack.pop();
				String operand2 = operandStack.pop();
				String prefixStr = operand1 + " " + operand2 + " " + newToken;

				operandStack.push(prefixStr);
			}
		}
		String output = operandStack.pop();

		if (!operandStack.isEmpty() || !reversalStack.isEmpty()) {
			throw new StackSyntaxError();
		}
		return output;
	}

	/**
	 * Converts the input String from a Postfix expression to a Prefix expression.
	 * Checks if the syntax is correct otherwise throws the unique StackSyntaxError
	 * exception. Returns the new expression in a String.
	 * 
	 * @return String - Prefix conversion
	 */
	public String convertToPreFix() {

		while (!tokens.isEmpty()) {
			String nextToken = tokens.listIterator().next();

			if (!operator.contains(nextToken)) {
				operandStack.push(nextToken);
			} else {
				String operand1 = operandStack.pop();
				String operand2 = operandStack.pop();
				String postfixStr = nextToken + " " + operand2 + " " + operand1;

				operandStack.push(postfixStr);
			}
			tokens.remove(tokens.indexOf(nextToken));
		}
		String output = operandStack.pop();

		if (!operandStack.isEmpty()) {
			throw new StackSyntaxError();
		}
		return output;
	}

	/**
	 * Helper method to tokenize the inputed string. Adds the contents to an
	 * arraylist of strings.
	 * 
	 * @param input - given expression
	 */
	private void tokenize(String input) {
		String curr = "";
		for (String token : input.split("")) {
			if (!curr.isBlank()) {
				if (operator.contains(curr)) {
					tokens.add(curr);
					curr = "";
				} else if (token.isBlank() || operator.contains(token)) {
					tokens.add(curr);
					curr = "";
				}
			}
			if (!token.isBlank()) {
				curr += token;
			}
		}
		if (!curr.isBlank()) {
			tokens.add(curr);
		}
	}
}
