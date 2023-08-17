package project_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main class that creates a list of polynomials based on a given file. Uses
 * JFileChooser to search for the file to open. Compares polynomials and checks
 * if the file is in weak and/or strong order. Prints the polynomial in ax^2 +
 * bx + c format
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 2
 * @Date 06/08/2023
 */
public class PolynomialDriver {

	public static void main(String[] args) throws FileNotFoundException {
		List<Polynomial> polynomials = new ArrayList<>();

		JFileChooser fileChoose = new JFileChooser();
		int r = fileChoose.showOpenDialog(null);

		if (r == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChoose.getSelectedFile();

			try {
				Scanner scanner = new Scanner(selectedFile);
				while (scanner.hasNextLine()) {

					Polynomial polynomial = new Polynomial(scanner.nextLine());
					polynomials.add(polynomial);
					System.out.println(polynomial.toString());
				}
			} catch (InvalidPolynomialSyntax e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			}
		}

		Comparator<Polynomial> orderComparator = (p1, p2) -> {
			Polynomial.Node node1 = getLastNode(p1);
			Polynomial.Node node2 = getLastNode(p2);
			return Integer.compare(node1.exp, node2.exp);
		};

		boolean isStrong = true;
		boolean isWeak = true;

		for (int i = 1; i < polynomials.size(); i++) {
			Polynomial p1 = polynomials.get(i - 1);
			Polynomial p2 = polynomials.get(i);
			if (p1.compareTo(p2) > 0) {
				isStrong = false;
			}
			if (orderComparator.compare(p1, p2) < 0) {
				isWeak = false;
			}
		}

		System.out.println("\nIs in strong order? " + isStrong);
		System.out.println("Is in weak order? " + isWeak);
	}

	/**
	 * Helper method to get the last node of a polynomial
	 * 
	 * @param polynomial - Polynomial Object
	 * @return node - Node
	 */
	private static Polynomial.Node getLastNode(Polynomial polynomial) {
		Polynomial.Node node = polynomial.head;
		while (node.next != null) {
			node = node.next;
		}
		return node;
	}
}
