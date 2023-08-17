package project_01;

/**
 * Exception class that creates a a new throw RuntimeException for use in the
 * ExpressionConverter. Gives a message indicating the syntax is incorrect.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 1
 * @Date 05/24/2023
 */
public class StackSyntaxError extends RuntimeException {

	@Override
	public String getMessage() {
		return "Stack Syntax Error at Runtime. Stack is not empty at end of conversion. Syntax incorrect.";
	}
}
