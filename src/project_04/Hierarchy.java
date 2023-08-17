package project_04;

/**
 * Generic class that implements DFSActions methods. Creates a string of
 * vertexes that shows dependencies via tabs and new lines. Uses a StringBuilder
 * to take advantage of its mutability. Primarily used in the DFS algorithm.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 4
 * @Date 07/03/2023
 * @param <T>
 */
public class Hierarchy<T> implements DFSActions<T> {

	private StringBuilder graphFigure = new StringBuilder();
	private int indent = 0;

	/**
	 * Provides functionality when a circular dependency is found in a graph. Adds a
	 * marker '*' to distinguish when a circular dependency is found. Overridden
	 * from implemented interface.
	 */
	@Override
	public void cycleDetected() {
		graphFigure.setLength(graphFigure.length() - 1);
		graphFigure.append(" *");
		graphFigure.append("\n");
	}

	/**
	 * Primary method to create the string and adds a four space indentations based
	 * on if it descends or ascends. Overridden from implemented interface.
	 */
	@Override
	public void processVertex(T vertex) {
		for (int i = 0; i < indent; i++) {
			graphFigure.append("    ");
		}
		graphFigure.append(vertex.toString()).append("\n");
	}

	/**
	 * Provides functionality when the graph figure descends. Overridden from
	 * implemented interface.
	 */
	@Override
	public void descend(T vertex) {
		indent++;
	}

	/**
	 * Provides functionality when the graph figure ascends. Overridden from
	 * implemented interface.
	 */
	@Override
	public void ascend(T vertex) {
		indent--;
	}

	/**
	 * Overridden toString that provides the finalized graph figure.
	 * 
	 */
	@Override
	public String toString() {
		return graphFigure.toString();
	}

	/**
	 * Provides a simple way to display the graph figure via console.
	 * 
	 */
	public void displayHierarchy() {
		System.out.println(toString());
	}

}
