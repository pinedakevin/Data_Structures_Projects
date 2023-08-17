package project_02;

/**
 * Unique exception that provides a message that is given when throwing the
 * error.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 2
 * @Date 06/08/2023
 */
public class InvalidPolynomialSyntax extends RuntimeException {

	@Override
	public String getMessage() {
		return "Incorrect Syntax: Negative exponents exists or incorrect order. Not allowed.";
	}
}
