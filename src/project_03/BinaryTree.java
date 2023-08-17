package project_03;

import java.util.Stack;

/**
 * Creates a binary tree using a Node class. The tree is created by parsing a
 * string into a (A(G(j)(1))(z(5))) type of format. Can find if the given binary
 * tree String is balanced, full, and proper. Provides the number of nodes of
 * the binary tree, the height of the tree, and the in order format for the
 * binary tree.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 3
 * @Date 06/12/2023
 */
public class BinaryTree {
	private Node root;

	/**
	 * Creates a binary tree with the String input. Uses the helper method makeTree
	 * to create the tree.
	 * 
	 * @param inputTree - String
	 * @throws InvalidTreeSyntax
	 */
	public BinaryTree(String inputTree) throws InvalidTreeSyntax {
		this.root = makeTree(inputTree);
	}

	/**
	 * Parses binary tree given as a string. Checks if the string has issues when
	 * creating the tree by throwing an InvalidTreeSyntax.
	 * 
	 * @param inputTree - String
	 * @return Node node
	 * @throws InvalidTreeSyntax
	 */
	private Node makeTree(String inputTree) throws InvalidTreeSyntax {

		// Checking input first if it is empty or null
		if (inputTree == null || inputTree.isEmpty()) {
			throw new InvalidTreeSyntax("Invalid Tree Format: Input is empty");
		}

		Node node = null;
		char previous = '-';

		// Last In First Out is the best way to manipulate the data that enters by
		// holding the parent before the child.
		Stack<Node> stack = new Stack<>();

		// Iterate through the string
		for (Character nodeData : inputTree.toCharArray()) {

			// Checks if nothing is between ()
			if (previous == '(' && nodeData == ')') {
				throw new InvalidTreeSyntax("Invalid Tree Format: Empty data in between ( )");
			}

			// Beginning of 'node'. Ignore the opening
			if (nodeData == '(') {

				// If the next char is a valid node, than proceed.
			} else if (Character.isLetterOrDigit(nodeData)) {
				node = new Node(nodeData);
				stack.push(node);

				// If the next char is a closing, than we check the stack if it is empty.
				// This would mean that there was no valid node. Throw unique exception.
			} else if (nodeData == ')') {
				if (stack.isEmpty()) {
					throw new InvalidTreeSyntax("Invalid Tree Format: Empty data in between ( ) or invalid character");
				}

				// Else we add the data in the stack to the node
				Node leaf = stack.pop();

				// If Stack still isn't empty, than there is a parent node.
				if (!stack.isEmpty()) {
					Node subRoot = stack.peek();

					// Checking if the left child is empty,
					if (subRoot.left == null) {
						subRoot.left = leaf;
					} else {
						subRoot.right = leaf;
					}

					// Done with the branch. Return the node
				} else {
					return leaf;
				}

				// Not a proper node or tree. Throw
			} else {
				throw new InvalidTreeSyntax("Invalid Tree Format: Invalid character");
			}

			previous = nodeData;
		}

		// After processing tree, if there is something left in the stack, than an extra
		// character exists or not formatted correctly
		if (stack.size() > 1) {
			throw new InvalidTreeSyntax("Invalid Tree Format: Extra character included or not correctly formated");
		}

		// Return something, "Should" never get here with all the other checks
		return node;
	}

	/**
	 * Node class to hold the information of the binary tree. Uses Character based
	 * on assignment instructions.
	 * 
	 */
	private static class Node {
		char data;
		Node left;
		Node right;

		Node(char data) {
			this.data = data;
			right = null;
			left = null;
		}
	}

	/**
	 * Creates a binary tree in order format. Recursively, iterates through all
	 * available nodes and returns a String with the tree in the format.
	 * 
	 * @return String
	 */
	public String InOrder() {

		// Using string creates extra memory since a new string is made each time.
		// StringBuilder creates an mutable string to work with.
		StringBuilder tree = new StringBuilder();
		InOrderHelper(root, tree);
		return tree.toString();
	}

	/**
	 * Checks if the tree is balanced based on its height and set nodes for each
	 * branch.
	 * 
	 * @return boolean
	 */
	public boolean isBalanced() {

		// Iterate through recursion the height of the Tree.
		if (treeHeightCheck(root) - 1 >= 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the trees height based on the number of levels or branches.
	 * 
	 * @return integer
	 */
	public int treeHeight() {
		return treeHeightCheck(root) - 1;
	}

	/**
	 * Checks if a tree is in proper format. A tree needs to have either 0 or 2
	 * child (leaf) nodes for each of them.
	 * 
	 * @return boolean
	 */
	public boolean isProper() {
		return properHelper(root);
	}

	/**
	 * Checks if a binary tree is full. A binary tree is full if the maximum number
	 * of nodes for a tree of 'n' level are created.
	 * 
	 * @return boolean
	 */
	public boolean isFull() {
		// Recursively get the height of tree and number of nodes of tree.
		int heightOfTree = treeHeight();
		int numOfNodes = getNumNodes();

		// Calculate the maximum height that a tree of n level can reach.
		int maximumHeight = (int) (Math.pow(2, heightOfTree + 1) - 1);

		// If an empty tree, than it is full.
		if (heightOfTree == 0) {
			return true;
		}

		// If a tree's maximum height equals the number of nodes, than it is full
		if (maximumHeight == numOfNodes) {
			return true;
		}

		return false;
	}

	/**
	 * Gets the number of nodes in a binary tree. Recursively iterates through the
	 * tree and checks all nodes.
	 * 
	 * @return integer
	 */
	public int getNumNodes() {
		return numOfNodes(root);
	}

	// *********** HELPER METHODS ***************************//

	/**
	 * Helper method to check the number of nodes in a binary tree using recursion..
	 * 
	 * @param node - Node
	 * @return integer
	 */
	private int numOfNodes(Node node) {
		// Base case. If node is null then it has no nodes.
		if (node == null) {
			return 0;
		}

		// Recursively iterate through each branch of the tree and
		// obtain the number.
		int leftNodes = numOfNodes(node.left);
		int rightNodes = numOfNodes(node.right);

		int total = leftNodes + rightNodes + 1;

		return total;
	}

	/**
	 * Helper method for checking if a binary tree is proper using recursion.
	 * 
	 * @param node - Node
	 * @return integer
	 */
	private boolean properHelper(Node node) {

		// Base case. If the node is empty, than it is proper
		if (node == null) {
			return true;
		}

		// If the left and right nodes of the subtrees are empty (leafs),
		// than it is proper equaling 0
		if (node.left == null && node.right == null) {
			return true;

			// If the nodes are not empty, than recursively iterate through the
			// subtree until you find them 0. Confirming its is either 0 or 2
		} else if (node.left != null && node.right != null) {
			boolean leftSub = properHelper(node.left);
			boolean rightSub = properHelper(node.right);
			boolean fullTree = (leftSub && rightSub);

			return fullTree;
		}

		return false;
	}

	/**
	 * Helper method to check the height of a binary tree using recursion.
	 * 
	 * @param node - Node
	 * @return integer
	 */
	private int treeHeightCheck(Node node) {
		// If the node is empty, the height is zero
		if (node == null) {
			return 0;
		}

		// Calculating the left and right side of the tree
		int leftHeight = treeHeightCheck(node.left);
		int rightHeight = treeHeightCheck(node.right);

		int difference = Math.abs(leftHeight - rightHeight);

		// Checking if both branches have some or no height
		// If negative, than its unbalanced.
		// If the difference between the two is none or only one level.
		// Both need to be true.
		if ((leftHeight >= 0 && rightHeight >= 0) && (difference <= 1)) {
			int height = Math.max(leftHeight, rightHeight) + 1;
			return height;

			// If not than it is unbalanced.
		} else {
			return -1;
		}
	}

	/**
	 * Helper method for creating a binary tree in order format using recursion.
	 * 
	 * @param node       - Node
	 * @param remadeTree - String
	 */
	private void InOrderHelper(Node node, StringBuilder remadeTree) {

		// Base case
		// If node is null, than we reach the end of the tree
		if (node != null) {
			// Opens
			remadeTree.append("(");
			InOrderHelper(node.left, remadeTree);

			// adds data
			remadeTree.append(" " + node.data + " ");
			InOrderHelper(node.right, remadeTree);

			// Closes
			remadeTree.append(")");
		}
	}

	/**
	 * Testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BinaryTree bt = new BinaryTree("(A(G(j)(1))(z(5)))");
			BinaryTree bt2 = new BinaryTree("(A(G(j)(1))(z(5)(d)))");

			String tree = bt.InOrder();
			System.out.println(tree);
			System.out.println("is the tree balanced: " + bt.isBalanced());
			System.out.println("bt tree height: " + bt.treeHeight());
			System.out.println("number of nodes: " + bt.getNumNodes());
			System.out.println("is the tree proper: " + bt.isProper());
			System.out.println("Is the tree full: " + bt.isFull());
			System.out.println();
			String tree2 = bt2.InOrder();
			System.out.println(tree2);
			System.out.println("is the tree balanced: " + bt2.isBalanced());
			System.out.println("bt tree height: " + bt2.treeHeight());
			System.out.println("number of nodes: " + bt2.getNumNodes());
			System.out.println("is the tree proper: " + bt2.isProper());
			System.out.println("Is the tree full: " + bt2.isFull());

		} catch (InvalidTreeSyntax e) {
			e.printStackTrace();
		}
	}

}
