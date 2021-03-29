package gfg.week15.binarySearchTree;

public class videoSolutions {

	public static void main(String[] args) {

	}

	public boolean searchRecursive(Node root, int key) {
		if (root == null)
			return false;
		if (root.data == key)
			return true;
		if (root.data > key)
			return searchRecursive(root.left, key);
		else
			return searchRecursive(root.right, key);
	}

	public boolean searchIterative(Node root, int key) {
		while (root != null) {
			if (root.data == key)
				return true;
			if (root.data > key)
				root = root.left;
			else
				root = root.right;
		}
		return false;
	}

	public Node InsertRecursive(Node root, int key) {
		if (root == null)
			return new Node(key);
		if (root.data > key)
			root.left = InsertRecursive(root.left, key);
		else
			root.right = InsertRecursive(root.right, key);
		return root;
	}

	public Node InsertIterative(Node root, int key) {
		Node temp = new Node(key);
		Node parent = null, curr = root;
		while (curr != null) {
			parent = curr;
			if (curr.data > key)
				curr = curr.left;
			else if (curr.data < key)
				curr = curr.right;
			else
				return root;
		}
		if (parent == null)
			return temp;
		if (parent.data > key) {
			parent.left = temp;
		} else {
			parent.right = temp;
		}
		return root;
	}

}

class Node {
	int data;
	Node left;
	Node right;

	Node(int k) {
		data = k;
	}
}