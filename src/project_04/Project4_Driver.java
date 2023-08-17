package project_04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Main class for processing a Directed Graph. Provides a directed graph that
 * prints it out in a hierarchy and parenthesized list. Prints given string via
 * the console.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 4
 * @Date 07/03/2023
 */
public class Project4_Driver {

	public static void main(String[] args) throws FileNotFoundException {

		JFileChooser fileChoose = new JFileChooser();
		int r = fileChoose.showOpenDialog(null);

		if (r == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChoose.getSelectedFile();

			// Initializing classes to access
			Hierarchy<String> hierarchy = new Hierarchy<>();
			ParenthesizedList<String> parenthesizedList = new ParenthesizedList<>();
			DirectedGraph<String> graph = new DirectedGraph<>(hierarchy, parenthesizedList);

			// Initializing starting vertex variables to use in search
			boolean foundFirst = false;
			String startingVertex = "";

			// Parsing through file and adding edges to graph
			try (Scanner scanner = new Scanner(selectedFile)) {
				while (scanner.hasNextLine()) {
					// Split classes and added them to an array.
					// Split by line to show dependencies.
					String[] fileLines = scanner.nextLine().split(" ");
					for (int i = 1; i < fileLines.length; i++) {

						// Grabbing first class to start DFS
						if (i == 1 && !foundFirst) {
							startingVertex = fileLines[0];
							foundFirst = true;
						}
						graph.addEdge(fileLines[0], fileLines[i]);
					}
				}
			}

			// DFS for the graph using the first vertex
			graph.depthFirstSearch(startingVertex);

			// Display the hierarchy
			hierarchy.displayHierarchy();

			// Display the Parenthesized List
			parenthesizedList.displayParenthesizedList();

			// Display unreachable classes
			graph.displayUnreachableClasses();

		}
	}

}
