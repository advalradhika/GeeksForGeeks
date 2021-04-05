package gfg.week14.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class practiceProblems {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		System.out.println(verticalWidth(root));
	}

	static ArrayList<Integer> preorder(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		else
			return PreOrderTraversal(root, res);
	}

	static ArrayList<Integer> PreOrderTraversal(Node root, ArrayList<Integer> res) {
		if (root == null) {
			return res;
		}
		res.add(root.data);
		PreOrderTraversal(root.left, res);
		PreOrderTraversal(root.right, res);
		return res;
	}

	ArrayList<Integer> inOrder(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		else
			return InOrderTraversal(root, res);
	}

	static ArrayList<Integer> InOrderTraversal(Node root, ArrayList<Integer> res) {
		if (root == null) {
			return res;
		}
		PreOrderTraversal(root.left, res);
		res.add(root.data);
		PreOrderTraversal(root.right, res);
		return res;
	}

	ArrayList<Integer> postOrder(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		else
			return PostOrderTraversal(root, res);
	}

	static ArrayList<Integer> PostOrderTraversal(Node root, ArrayList<Integer> res) {
		if (root == null) {
			return res;
		}
		PostOrderTraversal(root.left, res);
		PostOrderTraversal(root.right, res);
		res.add(root.data);
		return res;
	}

	int height(Node node) {
		if (node == null)
			return 0;
		else
			return 1 + Math.max(height(node.left), height(node.right));
	}

	boolean isIdentical(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;
		else if (root1 == null || root2 == null)
			return false;
		else
			return root1.data == root2.data && isIdentical(root1.left, root2.left)
					&& isIdentical(root1.right, root2.right);
	}

	public static int isSumProperty(Node root) {
		if (root == null)
			return 1;
		if (root.left == null && root.right == null)
			return 1;
		int sum = 0;
		if (root.left != null) {
			sum += root.left.data;
		}
		if (root.right != null) {
			sum += root.right.data;
		}
		if (root.data == sum && isSumProperty(root.left) == 1 && isSumProperty(root.right) == 1)
			return 1;
		else
			return 0;
	}

	public static ArrayList<Integer> levelOrder(Node node) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		levelOrderUtil(res, node);
		return res;
	}

	public static void levelOrderUtil(ArrayList<Integer> arr, Node root) {
		Queue<Node> q = new LinkedList<>();
		Node curr = null;
		q.offer(root);
		while (!q.isEmpty()) {
			curr = q.poll();
			arr.add(curr.data);
			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
		}
	}

	static ArrayList<ArrayList<Integer>> levelOrderLine(Node node) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		levelOrderUtilLine(res, node);
		return res;
	}

	public static void levelOrderUtilLine(ArrayList<ArrayList<Integer>> arr, Node root) {
		Queue<Node> q = new LinkedList<>();
		Node curr = null;
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			ArrayList<Integer> l = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				curr = q.poll();
				l.add(curr.data);
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
			arr.add(l);
		}
	}

	ArrayList<Integer> findSpiral(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		findSpiralutil(res, root);
		return res;
	}

	public static void findSpiralutil(ArrayList<Integer> arr, Node root) {
		if (root == null)
			return;
		boolean i = true;
		Queue<Node> q = new LinkedList<>();
		Stack<Integer> st = new Stack<Integer>();
		Node curr = null;
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				curr = q.poll();
				if (i) {
					st.add(curr.data);
				} else {
					arr.add(curr.data);
				}
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
			if (i) {
				while (!st.isEmpty()) {
					arr.add(st.pop());
				}
			}
			i = !i;
		}

	}

	int getMaxWidth(Node root) {
		int res = 0;
		Queue<Node> q = new LinkedList<>();
		Node curr = null;
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			res = Math.max(res, size);
			for (int i = 0; i < size; i++) {
				curr = q.poll();
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
		return res;
	}

	boolean isBalanced(Node root) {
		int out = isBalancedUtil(root);
		if (out != -1)
			return true;
		else
			return false;
	}

	public static int isBalancedUtil(Node root) {
		if (root == null)
			return 0;
		int left = isBalancedUtil(root.left);
		if (left == -1)
			return -1;
		int right = isBalancedUtil(root.right);
		if (right == -1)
			return -1;
		if (Math.abs(left - right) > 1)
			return -1;
		else
			return 1 + Math.max(left, right);
	}

	ArrayList<Integer> leftView(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		leftViewUtil(res, root);
		return res;
	}

	public static void leftViewUtil(ArrayList<Integer> arr, Node root) {
		if (root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node curr = q.poll();
				if (i == 0) {
					arr.add(curr.data);
				}
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}

	ArrayList<Integer> rightView(Node node) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		rightViewUtil(res, node);
		return res;
	}

	public static void rightViewUtil(ArrayList<Integer> arr, Node root) {
		if (root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node curr = q.poll();
				if (i == size - 1) {
					arr.add(curr.data);
				}
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
	}

	Node lca(Node root, int n1, int n2) {
		if (root == null)
			return null;
		if (root.data == n1 || root.data == n2)
			return root;
		Node lc1 = lca(root.left, n1, n2);
		Node lc2 = lca(root.right, n1, n2);
		if (lc1 != null && lc2 != null)
			return root;
		if (lc1 != null)
			return lc1;
		else
			return lc2;
	}

	static int diameter;

	int diameter(Node root) {
		diameter = 0;
		calculateHeight(root);
		return diameter;
	}

	int calculateHeight(Node root) {
		if (root == null)
			return 0;
		int lh = calculateHeight(root.left);
		int rh = calculateHeight(root.right);
		diameter = Math.max(diameter, 1 + lh + rh);
		return 1 + Math.max(lh, rh);
	}

	static HashSet<Integer> map = null;

	public static int verticalWidth(Node root) {
		map = new HashSet<Integer>();
		findVerticalUtil(root, 0);
		return map.size();
	}

	static void findVerticalUtil(Node root, int dis) {
		if (root == null)
			return;
		findVerticalUtil(root.left, dis - 1);
		if (!map.contains(dis)) {
			map.add(dis);
		}
		findVerticalUtil(root.right, dis + 1);
	}

	void mirror(Node node) {
		if (node == null)
			return;
		Node curr = node.left;
		node.left = node.right;
		node.right = curr;
		mirror(node.left);
		mirror(node.right);
	}

	////// TLE
	public static boolean isSubtreeTLE(Node T, Node S) {
		if (S == null)
			return true;
		if (T == null)
			return false;
		if (checkIdentical(S, T))
			return true;
		else
			return isSubtreeTLE(T.left, S) || isSubtreeTLE(T.right, S);
	}

	public static boolean checkIdentical(Node node1, Node node2) {
		if (node1 == null && node2 == null)
			return true;
		if (node1 == null || node2 == null)
			return false;
		else
			return ((node1.data == node2.data) && checkIdentical(node1.left, node2.left)
					&& checkIdentical(node1.right, node2.right));
	}

	/*
	 * 1) Find inorder and preorder traversals of T, store them in two auxiliary
	 * arrays inT[] and preT[].
	 * 
	 * 2) Find inorder and preorder traversals of S, store them in two auxiliary
	 * arrays inS[] and preS[].
	 * 
	 * 3) If inS[] is a subarray of inT[] and preS[] is a subarray preT[], then S is
	 * a subtree of T. Else not.
	 * 
	 */

	public static Tree convert(Node2 head, Tree node) {
		if (head == null)
			return null;
		Queue<Tree> q = new ArrayDeque<Tree>();
		node = new Tree(head.data);
		head = head.next;
		q.offer(node);
		while (head != null) {
			Tree qout = q.poll();
			if (head != null) {
				qout.left = new Tree(head.data);
				head = head.next;
			}
			q.offer(qout.left);
			if (head != null) {
				qout.right = new Tree(head.data);
				head = head.next;
			}
			q.offer(qout.right);
		}
		return node;
	}

	Node prev = null;

	Node bToDLL(Node root) {
		if (root == null)
			return root;
		Node head = bToDLL(root.left);
		if (prev == null)
			head = root;
		else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		bToDLL(root.right);
		return head;
	}

	Node bTreeToClist(Node root) {
		Node head = bToDLL(root);
		Node curr = head;
		while (curr.right != null) {
			curr = curr.right;
		}
		curr.right = head;
		head.left = curr;
		return head;
	}

	public static void connect(Nodex root) {
		if (root == null)
			return;
		Queue<Nodex> q = new LinkedList<Nodex>();
		q.offer(root);
		q.offer(null);
		while (q.size() > 1) {
			Nodex curr = q.poll();
			Nodex currnext;
			if (curr == null) {
				q.offer(null);
			} else {
				currnext = q.peek();
				curr.nextRight = currnext;
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
		System.out.println("out");
	}

	public static Node createTree(int parent[], int N) {
		Node[] arr = new Node[parent.length];
		for (int i = 0; i < parent.length; i++) {
			arr[i] = new Node(i);
		}
		Node head = null, parentnode;
		for (int i = 0; i < parent.length; i++) {
			if (parent[i] == -1) {
				head = arr[i];
			} else {
				parentnode = arr[parent[i]];
				if (parentnode.left == null) {
					parentnode.left = arr[i];
				} else {
					parentnode.right = arr[i];
				}
			}
		}
		return head;
	}

	static int postIndex = 0;

	public static Node buildTree(int in[], int post[], int n) {
		postIndex = n - 1;
		return buildTreeUtil(in, post, 0, in.length - 1);
	}

	public static Node buildTreeUtil(int in[], int[] post, int is, int ie) {
		if (is > ie)
			return null;
		Node root = new Node(post[postIndex--]);
		int inIndex = is;
		for (int i = is; i <= ie; i++) {
			if (in[i] == root.data) {
				inIndex = i;
				break;
			}
		}
		root.right = buildTreeUtil(in, post, inIndex + 1, ie);
		root.left = buildTreeUtil(in, post, is, inIndex - 1);
		return root;
	}

	boolean IsFoldable(Node node) {
		if (node == null)
			return true;
		if (node.left == null && node.right == null)
			return true;
		if (node.left == null || node.right == null)
			return false;
		mirror(node.left);
		boolean res = checkStruct(node.left, node.right);
		mirror(node.left);
		return res;
	}

	boolean checkStruct(Node root1, Node root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		return checkStruct(root1.left, root2.left) && checkStruct(root1.right, root2.right);
	}

	static int res = Integer.MIN_VALUE;

	public static int findMaxSum(Node node) {
		res = Integer.MIN_VALUE;
		findMaxSumrec(node);
		return res;
	}

	public static int findMaxSumrec(Node node) {
		if (node == null) {
			return 0;
		} else if (node.left == null && node.right == null) {
			res = Math.max(res, node.data);
			return node.data;
		} else if (node.left == null) {
			int r = findMaxSumrec(node.right);
			res = Math.max(res, Math.max(node.data, r + node.data));
			return Math.max(node.data, r + node.data);
		} else if (node.right == null) {
			int l = findMaxSumrec(node.left);
			res = Math.max(res, Math.max(node.data, l + node.data));
			return Math.max(node.data, l + node.data);
		} else {
			int data = node.data;
			int leftmax = findMaxSumrec(node.left);
			int rightmax = findMaxSumrec(node.right);
			res = Math.max(res,
					Math.max(data, Math.max(data + leftmax, Math.max(data + rightmax, data + leftmax + rightmax))));
			return Math.max(data, Math.max(data + leftmax, data + rightmax));
		}
	}

	public static int maxDiff(Node root) {
		res = Integer.MIN_VALUE;
		maxDiffUtil(root);
		return res;
	}

	public static int maxDiffUtil(Node root) {
		if (root.left == null && root.right == null) {
			return root.data;
		} else if (root.left == null) {
			res = Math.max(res, root.data - maxDiffUtil(root.right));
			return maxDiffUtil(root.right);
		} else if (root.right == null) {
			res = Math.max(res, root.data - maxDiffUtil(root.left));
			return maxDiffUtil(root.left);
		} else {
			res = Math.max(res, Math.max(root.data - maxDiffUtil(root.left), root.data - maxDiffUtil(root.right)));
			return Math.min(maxDiffUtil(root.left), maxDiffUtil(root.right));
		}
	}

	public void serialize(Node root, ArrayList<Integer> arr) {
		if (root == null) {
			arr.add(-1);
			return;
		}
		arr.add(root.data);
		serialize(root.left, arr);
		serialize(root.right, arr);
	}

	static int index;

	public Node deSerialize(ArrayList<Integer> arr) {
		index = 0;
		return deSerializeUtil(arr);

	}

	public Node deSerializeUtil(ArrayList<Integer> arr) {
		if (index == arr.size())
			return null;
		int value = arr.get(index);
		index++;
		if (value == -1)
			return null;
		Node root = new Node(value);
		root.left = deSerializeUtil(arr);
		root.right = deSerializeUtil(arr);
		return root;
	}

	static int count;

	int countSubtreesWithSumX(Node root, int X) {
		count = 0;
		countSubtreesWithSumXUtil(root, X);
		return count;
	}

	int countSubtreesWithSumXUtil(Node root, int X) {
		if (root == null)
			return 0;
		int lsum = countSubtreesWithSumXUtil(root.left, X);
		int rsum = countSubtreesWithSumXUtil(root.right, X);
		int sum = lsum + rsum + root.data;
		if (sum == X)
			count++;
		return sum;
	}

	int printKDistantfromLeaf(Node root, int k) {
		return 0;
	}

	ArrayList<Integer> zigZagTraversal(Node root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		zigZagTraversalUtil(root, res);
		return res;
	}

	void zigZagTraversalUtil(Node root, ArrayList<Integer> res) {
		if (root == null)
			return;
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		s1.add(root);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.empty()) {
				Node temp = s1.pop();
				res.add(temp.data);
				if (temp.left != null)
					s2.add(temp.left);
				if (temp.right != null)
					s2.add(temp.right);
			}
			while (!s2.empty()) {
				Node temp = s2.pop();
				res.add(temp.data);
				if (temp.right != null)
					s1.add(temp.right);
				if (temp.left != null)
					s1.add(temp.left);
			}
		}
	}

	static int getMaxSum(Node root) {
		Pair res = getMaxSumUtil(root);
		return Math.max(res.exsum, res.incsum);
	}

	static Pair getMaxSumUtil(Node root) {
		if (root == null) {
			Pair sum = new Pair(0, 0);
			return sum;
		}
		Pair leftsum = getMaxSumUtil(root.left);
		Pair rightsum = getMaxSumUtil(root.right);
		Pair sum = new Pair(0, 0);
		sum.incsum = root.data + leftsum.exsum + rightsum.exsum;
		sum.exsum = Math.max(leftsum.incsum, leftsum.exsum) + Math.max(rightsum.incsum, rightsum.exsum);
		return sum;
	}

}

class Pair {
	int incsum;
	int exsum;

	Pair(int incsum, int exsum) {
		this.incsum = incsum;
		this.exsum = exsum;
	}
}

class Nodex {
	int data;
	Nodex left;
	Nodex right;
	Nodex nextRight;

	Nodex(int data) {
		this.data = data;
		left = null;
		right = null;
		nextRight = null;
	}
}

class Tree {
	int data;
	Tree left;
	Tree right;

	Tree(int d) {
		data = d;
		left = null;
		right = null;
	}
}

class Node2 {
	int data;
	Node2 next;

	Node2(int d) {
		data = d;
		next = null;
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