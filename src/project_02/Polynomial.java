package project_02;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Creates a Polynomial object from files line in a locally implemented in a
 * singly LinkedList data structure. Can compare each polynomial object to one
 * another. Polynomial can be iterated through each node. Provides a unique
 * toString that displays in a ax^2 + bx + c format.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 2
 * @Date 06/08/2023
 */
public class Polynomial implements Iterable<Polynomial.Node>, Comparable<Polynomial> {

	protected Node head;

	/**
	 * Constructor that creates a Polynomial object by receiving a String from a
	 * file line. Checks if an exponent is negative or if the polynomial is not in
	 * order. Will throw InvalidPolynomialSyntax.
	 * 
	 * @param fileLine - String
	 */
	public Polynomial(String fileLine) {
		head = null;

		try (Scanner scan = new Scanner(fileLine)) {

			while (scan.hasNext()) {
				double coeff = scan.nextDouble();
				int exp = scan.nextInt();

				if (exp < 0) {
					throw new InvalidPolynomialSyntax();
				}
				addNode(coeff, exp);
			}
		} catch (Exception e) {
			throw new InvalidPolynomialSyntax();
		}
	}

	/**
	 * Compares two Polynomial objects by their coefficients and exponents. Iterates
	 * through the singly linked list until the last node.
	 */
	@Override
	public int compareTo(Polynomial o) {
		Node current = this.head;
		Node other = o.head;

		while (current.next != null || other.next != null) {
			if (current.next != null) {
				current = current.next;
			}
			if (other.next != null) {
				other = other.next;
			}
		}
		if (current.exp > other.exp || current.coeff > other.coeff) {
			return 1;
		} else if (current.exp < other.exp || current.coeff < other.coeff) {
			return -1;
		}
		return 0;
	}

	/**
	 * Iterates through the singly linked list. Checks if a node has a next node.
	 * Gets the next node.
	 */
	@Override
	public Iterator<Node> iterator() {
		return new Iterator<>() {

			private Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Node next() {
				Node temp = current;
				current = current.next;
				return temp;
			}
		};
	}

	/**
	 * Creates a unique string from the singly linked list that is based on ax^2 +
	 * bx + c. Created using StringBuilder to allow mutability and creation by check
	 * the numbers sign, coefficient, and exponent.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Node terms : this) {
			if (sb.length() > 0) {
				if (terms.coeff < 0) {
					sb.append(" - ");
				} else {
					sb.append(" + ");
				}
			}

			if (terms.coeff < 0 && sb.length() > 0) {
				sb.append(Math.abs(terms.coeff));
			} else {
				sb.append(terms.coeff);
			}

			if (terms.exp > 1) {
				sb.append("x^").append(terms.exp);
			} else if (terms.exp == 1)
				sb.append('x');
		}
		return sb.toString();
	}

	/**
	 * Node class that allows the creation of a singly linked list. Node data is the
	 * polynomials coefficient and exponent. Has getter methods for data.
	 */
	public static class Node {
		double coeff;
		int exp;
		Node next;

		Node(double d, int e) {
			this.coeff = d;
			this.exp = e;
			this.next = null;
		}

		public double getCoeff() {
			return this.coeff;
		}

		public int getExp() {
			return this.exp;
		}
	}

	/**
	 * Adds nodes to the linked list by receiving a coefficient and an exponent.
	 * Throws an InvalidPolynomialSyntax error if there is no coefficient or a
	 * negative exponent.
	 * 
	 * @param coeff
	 * @param exp
	 */
	private void addNode(double coeff, int exp) {
		if (exp < 0 || coeff == 0) {
			throw new InvalidPolynomialSyntax();
		}

		if (head == null) {
			head = new Node(coeff, exp);
		} else {
			Node last = head;

			while (last.next != null) {
				last = last.next;
			}
			last.next = new Node(coeff, exp);
		}
	}
}
