package gfg.week15.binarySearchTree;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {

	}

	static ArrayList<Integer> inOrder(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		inOrderUtil(root, res);
		return res;
	}

	static void inOrderUtil(Node root, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			inOrderUtil(root.left, res);
		}
		res.add(root.data);
		if (root.right != null) {
			inOrderUtil(root.right, res);
		}
	}

	static ArrayList<Integer> levelOrder(Node node) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		levelOrderUtil(node, res);
		return res;
	}

	static void levelOrderUtil(Node root, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		q.offer(root);
		while (!q.isEmpty()) {
			Node curr = q.pop();
			res.add(curr.data);
			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
		}
	}

	Node insert(Node root, int Key) {
		if (root == null) {
			return new Node(Key);
		} else if (root.data > Key) {
			root.left = insert(root.left, Key);
		} else if (root.data < Key) {
			root.right = insert(root.right, Key);
		}
		return root;
	}

	boolean search(Node root, int x) {
		if (root == null)
			return false;
		if (root.data == x)
			return true;
		if (root.data > x)
			return search(root.left, x);
		else
			return search(root.right, x);
	}

	int minValue(Node node) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		minValueUtil(node, res);
		return res.size() > 0 ? res.get(0) : -1;
	}

	void minValueUtil(Node root, ArrayList<Integer> res) {
		if (res.size() > 1)
			return;
		if (root == null) {
			return;
		}
		if (root.left != null) {
			minValueUtil(root.left, res);
		}
		res.add(root.data);
		if (root.right != null) {
			minValueUtil(root.right, res);
		}
	}

	public static ArrayList<Integer> findCommon(Node root1, Node root2) {
		ArrayList<Integer> res1 = new ArrayList<Integer>();
		inOrderUtil(root1, res1);
		ArrayList<Integer> res2 = new ArrayList<Integer>();
		inOrderUtil(root2, res2);
		res1.retainAll(res2);
		return res1;
	}

	public static Node deleteNode(Node root, int X) {
		if (root == null) {
			return root;
		} else if (root.data > X) {
			root.left = deleteNode(root.left, X);
		} else if (root.data < X) {
			root.right = deleteNode(root.right, X);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else {
				Node succ = getSucc(root);
				root.data = succ.data;
				root.right = deleteNode(root.right, succ.data);
			}
			return root;
		}
		return root;
	}

	public static Node getSucc(Node root) {
		Node curr = root.right;
		while (curr != null && curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	Node LCA(Node root, int n1, int n2) {
		if (root.data == n1 || root.data == n2) {
			return root;
		} else if (root.data > n1 && root.data > n2) {
			return LCA(root.left, n1, n2);
		} else if (root.data < n1 && root.data < n2) {
			return LCA(root.right, n1, n2);
		} else {
			return root;
		}
	}

	public static ArrayList<Integer> printNearNodes(Node root, int low, int high) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		printNearNodesUtil(root, res, low, high);
		return res;
	}

	static void printNearNodesUtil(Node root, ArrayList<Integer> res, int low, int high) {
		if (root == null) {
			return;
		}
		if (root.left != null && root.left.data >= low) {
			printNearNodesUtil(root.left, res, low, high);
		}
		if (root.left != null && root.left.data < low) {
			printNearNodesUtil(root.left.right, res, low, high);
		}
		if (root.data >= low && root.data <= high)
			res.add(root.data);
		if (root.right != null && root.right.data <= high) {
			printNearNodesUtil(root.right, res, low, high);
		}
		if (root.right != null && root.right.data > high) {
			printNearNodesUtil(root.right.left, res, low, high);
		}
	}

	static boolean findPair(Node root, int X) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		return findPairUtil(root, res, X);
	}

	static boolean findPairUtil(Node root, ArrayList<Integer> res, int X) {
		if (root == null) {
			return false;
		}
		if (findPairUtil(root.left, res, X)) {
			return true;
		}
		if (res.contains(X - root.data)) {
			return true;
		}
		res.add(root.data);
		return findPairUtil(root.right, res, X);
	}

}
