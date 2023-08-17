package project_04;

/**
 * Generic class that implements DFSActions methods. Creates a string of
 * vertexes that shows dependencies via parentheses and spaces. Uses a
 * StringBuilder to take advantage of its mutability. Primarily used in the DFS
 * algorithm.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 4
 * @Date 07/03/2023
 * @param <T>
 */
public class ParenthesizedList<T> implements DFSActions<T> {

	private StringBuilder graphFigure = new StringBuilder();

	/**
	 * Provides functionality when a circular dependency is found in a graph. Adds a
	 * marker '*' to distinguish when a circular dependency is found. Overridden
	 * from implemented interface.
	 */
	@Override
	public void cycleDetected() {

		// If a circular dependency is detected, we check if a parenthesis exists.
		// If so, than we delete that section by adjusting the length.
		if (graphFigure.charAt(graphFigure.length() - 2) == '(') {
			graphFigure.setLength(graphFigure.length() - 3);
		}

		graphFigure.append(" * ");
	}

	/**
	 * Primary method to create the string. Overridden from implemented interface.
	 */
	@Override
	public void processVertex(T vertex) {
		graphFigure.append(vertex.toString());
	}

	/**
	 * Provides functionality when the graph figure descends and adds a left
	 * parenthesis '('. Overridden from implemented interface.
	 */
	@Override
	public void descend(T vertex) {
		graphFigure.append(" ( ");
	}

	/**
	 * Provides functionality when the graph figure ascends and adds a right
	 * parenthesis ')'. Overridden from implemented interface.
	 */
	@Override
	public void ascend(T vertex) {
		if (graphFigure.charAt(graphFigure.length() - 2) != '*') {
			graphFigure.append(" ) ");
		}

	}

	/**
	 * Overridden toString that provides the finalized graph figure. Deletes any
	 * unnecessary parenthesis that do not align with assignment instructions.
	 */
	@Override
	public String toString() {

		// Iterate through StringBuilder to delete extra parenthesis.
		for (int i = 0; i < graphFigure.length(); i++) {
			if (graphFigure.charAt(i) == ')') {
				if (graphFigure.charAt(i - 3) == '(') {
					graphFigure.delete(i - 3, i + 1);
					i = i - 4;
				}
			}
		}

		return "( " + graphFigure.toString() + " )\n";
	}

	/**
	 * Provides a simple way to display the graph figure via console.
	 */
	public void displayParenthesizedList() {
		System.out.println(toString());
	}

}
