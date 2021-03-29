package gfg.week14.tree;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		tNode root = new tNode(4);
		root.left = new tNode(9);
		root.right = new tNode(2);
		root.left.left = new tNode(3);
		root.left.right = new tNode(5);
		root.right.right = new tNode(7);
		// root.right.right.right = new tNode(20);
		// int s = maxElement(root);
		// System.out.println(s);
		// checkBalancing(root);

		System.out.println(maximumValue(root));
		// System.out.println(preorder(root));
		// System.out.println(getMaxWidth(root));
	}

	static void InOrderTraversal(tNode root) {
		if (root == null) {
			return;
		}
		InOrderTraversal(root.left);
		System.out.println(root.key + " ");
		InOrderTraversal(root.right);
	}

	static void PreOrderTraversal(tNode root) {
		if (root == null) {
			return;
		}
		System.out.println(root.key + " ");
		PreOrderTraversal(root.left);
		PreOrderTraversal(root.right);
	}

	static void PostOrderTraversal(tNode root) {
		if (root == null) {
			return;
		}
		PostOrderTraversal(root.left);
		PostOrderTraversal(root.right);
		System.out.println(root.key + " ");
	}

	static int getHeight(tNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		}
	}

	static void KdistanceNodes(tNode root, int k) {
		if (root == null) {
			return;
		} else if (k == 0) {
			System.out.println(root.key + " ");
		} else {
			KdistanceNodes(root.left, k - 1);
			KdistanceNodes(root.right, k - 1);
		}
	}

	static void levelOrderTraversal(tNode root) {
		if (root == null)
			return;
		else {
			int height = getHeight(root);
			for (int i = 0; i < height; i++) {
				KdistanceNodes(root, i);
			}
		}
	}

	static void levelOrder(tNode root) {
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			tNode curr = q.poll();
			System.out.println(curr.key + "");
			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
		}
	}

	static void levelOrderLineByLineOne(tNode root) {
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		q.offer(null);
		while (q.size() > 1) {
			tNode curr = q.poll();
			if (curr == null) {
				System.out.println();
				q.offer(null);
			} else {
				System.out.print(curr.key + " ");
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}

	static void levelOrderLineByLineTwo(tNode root) {
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				tNode curr = q.poll();
				System.out.print(curr.key + " ");
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
			System.out.println();
		}
	}

	static ArrayList<Integer> maximumValue(tNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Queue<tNode> q = new LinkedList<tNode>();
		int max = 0;
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			max = 0;
			for (int i = 0; i < size; i++) {
				tNode curr = q.poll();
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
				System.out.println("curr :" + curr.key + " max:" + max);
				max = Math.max(max, curr.key);
			}
			res.add(max);
		}
		return res;
	}

	static int sizeOfTree(tNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
		}
	}

	static int maxElement(tNode root) {
		if (root == null)
			return Integer.MIN_VALUE;
		else {
			int currmax = root.key;
			return Math.max(currmax, Math.max(maxElement(root.left), maxElement(root.right)));
		}
	}

	static int maxlevel = 0;

	static void leftViewRecursive(tNode root) {
		printLeftView(root, 1);
	}

	static void printLeftView(tNode root, int level) {
		if (root == null) {
			return;
		}
		if (maxlevel < level) {
			System.out.println(root.key + " ");
			maxlevel = level;
		}
		printLeftView(root.left, level + 1);
		printLeftView(root.right, level + 1);
	}

	static void leftViewIterative(tNode root) {
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				tNode curr = q.poll();
				if (i == 0)
					System.out.print(curr.key + " ");
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}

	static void rightViewIterative(tNode root) {
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				tNode curr = q.poll();
				if (i == size - 1)
					System.out.print(curr.key + " ");
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}

	static boolean checkChildrenSumProperty(tNode root) {
		if (root == null)
			return true;
		if (root.left == null && root.right == null)
			return true;
		if ((root.left == null && root.key == root.right.key) || (root.right == null && root.key == root.left.key))
			return true;
		else if (root.key == root.left.key + root.right.key)
			return (checkChildrenSumProperty(root.left) && checkChildrenSumProperty(root.right));
		else
			return false;
	}

	static boolean checkBalancing(tNode root) {
		return checkBalancedTree(root) == -1 ? false : true;
	}

	static int checkBalancedTree(tNode root) {
		if (root == null)
			return 0;
		int left = checkBalancedTree(root.left);
		int right = checkBalancedTree(root.right);
		if (left == -1 || right == -1)
			return -1;
		else if (Math.abs(left - right) > 1)
			return -1;
		else
			return 1 + Math.max(left, right);
	}

	static int getMaxWidth(tNode root) {
		if (root == null)
			return 0;
		int max = 1, size = 1;
		Queue<tNode> q = new LinkedList<tNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			for (int i = 0; i < size; i++) {
				tNode curr = q.poll();
				if (curr.left != null)
					q.offer(curr.left);
				if (curr.right != null)
					q.offer(curr.right);
			}
			size = q.size();
			max = Math.max(size, max);
		}
		return max;
	}

	static tNode prev = null;

	static tNode convertToDoublyLinkedList(tNode root) {
		if (root == null) {
			return root;
		}
		tNode head = convertToDoublyLinkedList(root.left);
		if (prev == null) {
			head = root;
		} else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		convertToDoublyLinkedList(root.right);
		return head;
	}

	static int preIndex = 0;

	public static tNode cTree(int in[], int pre[], int is, int ie) {
		if (is > ie)
			return null;
		tNode root = new tNode(pre[preIndex++]);
		int inIndex = is;
		for (int i = is; i <= ie; i++) {
			if (in[i] == root.key) {
				inIndex = i;
				break;
			}
		}
		root.left = cTree(in, pre, is, inIndex - 1);
		root.right = cTree(in, pre, inIndex + 1, ie);
		return root;
	}

	/*
	 * public static void spiralTraversal(tNode root) { Queue<tNode> q = new
	 * LinkedList<tNode>(); q.offer(root); int level = 1, size; while (!q.isEmpty())
	 * { size = q.size(); for (int i = 0; i < size; i++) { tNode curr = q.poll();
	 * System.out.print(curr.key + " "); if (level % 2 == 0) { if (root.left !=
	 * null) q.offer(root.left); if (root.right != null) q.offer(root.right); } else
	 * { if (root.right != null) q.offer(root.right); if (root.left != null)
	 * q.offer(root.left); } } level++; } }
	 */

	public static void printSpiral(tNode root) {
		if (root == null)
			return;
		Queue<tNode> q = new LinkedList<>();
		Stack<Integer> s = new Stack<>();
		boolean reverse = false;
		q.add(root);
		while (q.isEmpty() == false) {
			int count = q.size();
			for (int i = 0; i < count; i++) {
				tNode curr = q.poll();
				if (reverse)
					s.add(curr.key);
				else
					System.out.print(curr.key + " ");
				if (curr.left != null)
					q.add(curr.left);
				if (curr.right != null)
					q.add(curr.right);
			}
			if (reverse) {
				while (s.isEmpty() == false) {
					System.out.print(s.pop() + " ");
				}
			}
			reverse = !reverse;
		}
	}

	public static void printSpiralEffective(tNode root) {
		if (root == null)
			return;
		Stack<tNode> s1 = new Stack<tNode>();
		Stack<tNode> s2 = new Stack<tNode>();
		s1.add(root);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.empty()) {
				tNode temp = s1.pop();
				System.out.print(temp.key + " ");
				if (temp.left != null)
					s2.add(temp.left);
				if (temp.right != null)
					s2.add(temp.right);
			}
			while (!s2.empty()) {
				tNode temp = s2.pop();
				System.out.print(temp.key + " ");
				if (temp.right != null)
					s1.add(temp.right);
				if (temp.left != null)
					s1.add(temp.left);
			}
		}
	}

	public static int getDiameterNaive(Node root) {
		if (root == null)
			return 0;
		int curr = 1 + getHeight(root.left) + getHeight(root.right);
		int lh = getDiameterNaive(root.left);
		int rh = getDiameterNaive(root.right);
		return Math.max(curr, Math.max(lh, rh));
	}

	static int getHeight(Node root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		}
	}

	static int res = 0;

	static int getDiameter(Node root) {
		if (root == null)
			return 0;
		else {
			getHeightForDiameter(root);
			return res;
		}
	}

	static int getHeightForDiameter(Node root) {
		if (root == null) {
			return 0;
		}
		int lh = getHeightForDiameter(root.left);
		int rh = getHeightForDiameter(root.right);
		res = Math.max(res, 1 + lh + rh);
		//// Here it returns the height
		//// res is the diameter
		return 1 + Math.max(lh, rh);
	}

	static Node findLCA(Node root, int n1, int n2) {
		ArrayList<Node> path1 = new ArrayList<Node>();
		ArrayList<Node> path2 = new ArrayList<Node>();
		if (!getPath(root, path1, n1) || !getPath(root, path2, n2)) {
			return null;
		}
		for (int i = 0; i < path1.size() - 1 && i < path2.size() - 1; i++) {
			if (path1.get(i + 1) != path2.get(i + 1))
				return path1.get(i);
		}
		return null;
	}

	static boolean getPath(Node root, ArrayList<Node> path, int n) {
		if (root == null)
			return false;
		path.add(root);
		if (root.data == n)
			return true;
		if (getPath(root.left, path, n) || getPath(root.right, path, n))
			return true;
		path.remove(path.size() - 1);
		return false;
	}

	//// Assumes both keys exists
	static Node getLCAEffective(Node root, int n1, int n2) {
		if (root == null)
			return null;
		if (root.data == n1 || root.data == n2)
			return root;
		Node lca1 = getLCAEffective(root.left, n1, n2);
		Node lca2 = getLCAEffective(root.right, n1, n2);
		if (lca1 != null && lca2 != null)
			return root;
		if (lca1 != null)
			return lca1;
		else
			return lca2;
	}

	static int countNodesinCompleteTreeNaive(Node root) {
		if (root == null)
			return 0;
		else
			return 1 + countNodesinCompleteTreeNaive(root.left) + countNodesinCompleteTreeNaive(root.right);
	}

	static int countNodesinCompleteTreeEffective(Node root) {
		Node curr = root;
		int lh = 0, rh = 0;
		while (curr != null) {
			curr = curr.left;
			lh++;
		}
		curr = root;
		while (curr != null) {
			curr = curr.right;
			rh++;
		}
		if (lh == rh)
			return (int) Math.pow(2, lh) - 1;
		else
			return 1 + countNodesinCompleteTreeEffective(root.left) + countNodesinCompleteTreeEffective(root.right);
	}

	public static void Serialize(ArrayList<Integer> arr, Node root) {
		if (root == null) {
			arr.add(-1);
			return;
		}
		arr.add(root.data);
		Serialize(arr, root.left);
		Serialize(arr, root.right);
	}

	static int index = 0;

	static Node Deserialize(ArrayList<Integer> arr) {
		if (index == arr.size())
			return null;
		int value = arr.get(index);
		index++;

		if (value == -1)
			return null;

		Node root = new Node(value);
		root.left = Deserialize(arr);
		root.right = Deserialize(arr);
		return root;
	}

	static void InOrderIterative(Node root) {
		Stack<Node> st = new Stack<Node>();
		Node curr = root;
		while (curr != null || !st.isEmpty()) {
			while (curr != null) {
				st.push(curr);
				curr = curr.left;
			}
			curr = st.pop();
			System.out.println(curr.data + " ");
			curr = curr.right;
		}
	}

	static void PreOrderIterative(Node root) {
		if (root == null)
			return;
		Stack<Node> st = new Stack<Node>();
		st.push(root);
		while (!st.isEmpty()) {
			Node curr = st.pop();
			System.out.println(curr.data);
			if (curr.right != null)
				st.push(curr.right);
			if (curr.left != null)
				st.push(curr.left);
		}
	}

	static void PreOrderIterativeEfficient(Node root) {
		if (root == null)
			return;
		Stack<Node> st = new Stack<Node>();
		Node curr = root;
		while (curr != null || !st.isEmpty()) {
			while (curr != null) {
				System.out.print(curr.data + "");
				if (curr.right != null)
					st.push(curr.right);
				curr = curr.left;
			}
			if (!st.isEmpty())
				curr = st.pop();
		}
	}

}

class tNode {
	int key;
	tNode left;
	tNode right;

	tNode(int k) {
		key = k;
	}
}
