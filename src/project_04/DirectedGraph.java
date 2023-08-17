package project_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates a generic directed graph that adds edges to vertices. Vertices and
 * edges are tracked via nested node class. Implemented Depth First Search by
 * given psuedocode from assignment instructions.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 4
 * @Date 07/03/2023
 * @param <T>
 */
public class DirectedGraph<T> {

	private Map<T, NodeVertex> vertices;
	private Hierarchy<T> hierarchy;
	private ParenthesizedList<T> parenthesizedList;
	private String startingVertex;

	/**
	 * Creates a directed graph and adds the hierarchy and parenthesizedList classes
	 * implemented by DFSActions Interface. Keeps the same instance of DFSAction
	 * classes by providing in constructor.
	 * 
	 * @param hierarchy
	 * @param parenthesizedList
	 */
	public DirectedGraph(Hierarchy<T> hierarchy, ParenthesizedList<T> parenthesizedList) {
		this.vertices = new HashMap<>();
		this.hierarchy = hierarchy;
		this.parenthesizedList = parenthesizedList;
	}

	/**
	 * Adds the edge dependency from each vertex and adds them to the mapped
	 * Vertices.
	 * 
	 * @param src  - Source vertex
	 * @param dest - Destination vertex
	 */
	public void addEdge(T src, T dest) {
		NodeVertex srcNode;
		NodeVertex destNode;

		srcNode = vertices.get(src);

		// checking if given node is null
		// if null, than we add a new node where it should be
		// add it to the map holding vertices
		if (srcNode == null) {
			srcNode = new NodeVertex(src);
			vertices.put(src, srcNode);
		}

		destNode = vertices.get(dest);

		// checking if given node is null
		// if null, than we add a new node where it should be
		// add it to the map holding vertices
		if (destNode == null) {
			destNode = new NodeVertex(dest);
			vertices.put(dest, destNode);
		}

		srcNode.adjacencyList.add(destNode);
	}

	/**
	 * Depth first search main method to start recursively searching with a helper
	 * message.
	 * 
	 * 
	 * @param s
	 */
	public void depthFirstSearch(T s) {

		// Initialize all vertices to undiscovered state.
		for (NodeVertex vertex : vertices.values()) {
			vertex.vertexState = VertexState.UNDISCOVERED;
		}

		// Get starting vertex to for DFS
		NodeVertex startingVertex = vertices.get(s);

		// Process depth first search recursively
		dfs(startingVertex);

		// Obtain starting vertex and initializes to global variable
		this.startingVertex = (String) startingVertex.ClassName;
	}

	/**
	 * Helper method for depth first search. Implemented by following provided
	 * psuedocde.
	 * 
	 * @param s
	 */
	private void dfs(NodeVertex s) {

		// If s is discovered
		if (s.vertexState == VertexState.DISCOVERED) {

			// Perform cycle detected action and return
			hierarchy.cycleDetected();
			parenthesizedList.cycleDetected();
			return;
		}

		// Perform add vertex action
		hierarchy.processVertex(s.ClassName);
		parenthesizedList.processVertex(s.ClassName);

		// mark s as discovered
		s.vertexState = VertexState.DISCOVERED;

		// Perform descend action
		hierarchy.descend(s.ClassName);
		parenthesizedList.descend(s.ClassName);

		// for all adjacent vertices v, dfs(v)
		for (NodeVertex v : s.adjacencyList) {
			dfs(v);
		}

		// Perform ascend action
		hierarchy.ascend(s.ClassName);
		parenthesizedList.ascend(s.ClassName);

		// Mark s as finished
		s.vertexState = VertexState.FINISHED;
	}

	/**
	 * Displays all unreachable classes after DFS was made. Checks for any vertexes
	 * labeled UNDISCOVERED.
	 * 
	 */
	public void displayUnreachableClasses() {

		// Iterate through all vertices
		for (NodeVertex vertex : vertices.values()) {
			// Find any undiscovered vertex after DFS and print
			if (vertex.vertexState == VertexState.UNDISCOVERED) {
				System.out.println(vertex.ClassName + " is unreachable");
			}
		}
	}

	/**
	 * Gets the starting vertex and returns it as a String.
	 * 
	 * @return
	 */
	public String getStartingVertex() {
		return this.startingVertex;
	}

	/**
	 * Enum to be able to label vertex node classes based on a vertex that was
	 * discovered, undiscovered, or finished used primarily in the DFS algorithm.
	 * 
	 * @author Kevin Pineda
	 */
	private enum VertexState {
		UNDISCOVERED, DISCOVERED, FINISHED
	}

	/**
	 * Local node classes to keep a vertex label, name, and adjacent vertexes.
	 * Created using an adjacency list.
	 * 
	 * @author Kevin Pineda
	 */
	private class NodeVertex {
		T ClassName;
		VertexState vertexState;
		List<NodeVertex> adjacencyList;

		NodeVertex(T ClassName) {
			this.ClassName = ClassName;
			this.vertexState = VertexState.UNDISCOVERED;
			this.adjacencyList = new ArrayList<>();
		}
	}
}
