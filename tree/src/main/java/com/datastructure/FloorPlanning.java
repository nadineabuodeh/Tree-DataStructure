package main.java.com.datastructure;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class FloorPlanning<E> extends AbstractBinaryTree<E> {

	// ---------------- nested Node class ----------------
	/** Nested static class for a binary tree node. */
	private static class Node<E> implements Position<E> {
		private Character element; // an element stored at this node
		private Node<E> parent; // a reference to the parent node (if any)
		private Node<E> left; // a reference to the left child (if any)
		private Node<E> right; // a reference to the right child (if any)
		private int height;
		private int width;

		public Node(Character e, Node<E> above, Node<E> leftChild, Node<E> rightChild, int h, int w) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
			width = w;
			height = h;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public E getElement() {
			return (E) element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		// update methods
		public void setElement(Character e) {
			element = e;
		}

		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}
	} // ----------- end of nested Node class -----------

	/** Factory function to create a new node storing element e. */
	protected Node<E> createNode(Character e, Node<E> parent, Node<E> left, Node<E> right, int h, int w) {
		return new Node<E>(e, parent, left, right, h, w);
	}

	// LinkedBinaryTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null; // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0; // number of nodes in the tree

	// nonpublic utility
	/**
	 * Verifies that a Position belongs to the appropriate class, and is not one
	 * that has been previously removed. Note that our current implementation does
	 * not actually verify that the position belongs to this particular list
	 * instance.
	 *
	 * @param p a Position (that should belong to this tree)
	 * @return the underlying Node instance for the position
	 * @throws IllegalArgumentException if an invalid position is detected
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	// accessor methods (not already implemented in AbstractBinaryTree)
	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return number of nodes in the tree
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the root Position of the tree (or null if tree is empty).
	 * 
	 * @return root Position of the tree (or null if tree is empty)
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * Returns the Position of p's parent (or null if p is root).
	 *
	 * @param p A valid Position within the tree
	 * @return Position of p's parent (or null if p is root)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree.
	 */
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	/**
	 * Returns the Position of p's left child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the left child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	/**
	 * Returns the Position of p's right child (or null if no child exists).
	 *
	 * @param p A valid Position within the tree
	 * @return the Position of the right child (or null if no child exists)
	 * @throws IllegalArgumentException if p is not a valid Position for this tree
	 */
	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	public FloorPlanning() {
		Node<E> node = createNode('A', null, null, null, 150, 150);
		this.root = node;
		this.size++;
	}

	public Position<E> decomposeHorizontal(Position<E> p, int h, Character c) {
		if (isInternal(p))
			throw new IllegalArgumentException();
		Node<E> node = validate(p);
		Character c1 = (Character) node.getElement();
		node.left = createNode(c1, node, null, null, h, node.width);
		Node<E> newNode = createNode(c, node, null, null, (node.height - h), node.width);
		node.setRight(newNode);
		node.setElement('-');
		return node.getRight();
	}

	public Position<E> decomposeVertical(Position<E> p, int w, Character c) {
		if (isInternal(p))
			throw new IllegalArgumentException();
		Node<E> node = validate(p);
		Character c1 = (Character) node.getElement();
		node.left = createNode(c1, node, null, null, node.height, w);
		Node<E> newNode = createNode(c, node, null, null, node.height, (node.width - w));
		node.setRight(newNode);
		node.setElement('|');
		return node.getRight();
	}

	public int getHeight(Position<E> p) {
		Node<E> node = validate(p);
		if (node == null)
			throw new IllegalArgumentException();
		if (isExternal(p))
			return node.height;
		if (isInternal(node) && (((Character) node.getElement()) == '|'))
			return Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
		else
			return getHeight(node.getLeft()) + getHeight(node.getRight());
	}

	public int getWidth(Position<E> p) {
		Node<E> node = validate(p);
		if (node == null)
			throw new IllegalArgumentException();
		if (isExternal(node))
			return node.width;
		else if (isInternal(node) && (((Character) node.getElement()) == '|'))
			return getWidth(node.getLeft()) + getWidth(node.getRight());
		else
			return Math.max(getWidth(node.getLeft()), getWidth(node.getRight()));
	}

	public String toString() throws IllegalArgumentException {
		return toString(root);
	}

	private String toString(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		String str = "";
		str += node.getElement() + "(";
		for (Position<E> child : children(node)) {
			if (child.getElement() != null)
				str += child.getElement() + ",";
		}
		str += ")\n";
		try {
			str += toString(node.getLeft());
			str += toString(node.getRight());
		} catch (Exception e) {
		}
		return str;
	}

} // ----------- end of LinkedBinaryTree class -----------

