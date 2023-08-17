package project_03;

/**
 * Unique exception that provides a message that is given when throwing the
 * error.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 3
 * @Date 06/12/2023
 */
public class InvalidTreeSyntax extends Exception {

	private String msg;

	public InvalidTreeSyntax(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
