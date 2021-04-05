package gfg.week15.binarySearchTree;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {
		Node root = new Node(5);
		root.left = new Node(4);
		root.right = new Node(6);
		root.left.left = new Node(3);
		root.right.right = new Node(7);
		int arr[] = { 40, 30, 35, 80, 100 };
		Node node = constructTree(arr, 3);
		PostOrderTraversal(node);
		// System.out.println(getCount(root, 1, 5));
	}

	static void PostOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		PostOrderTraversal(root.left);
		PostOrderTraversal(root.right);
		System.out.println(root.data + " ");
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

	static int res;

	int floor(Node root, int key) {
		res = -1;
		return floorUtil(root, key);
	}

	int floorUtil(Node root, int key) {
		while (root != null) {
			if (root.data == key) {
				return root.data;
			} else if (root.data > key) {
				root = root.left;
			} else {
				res = root.data;
				root = root.right;
			}
		}
		return res;
	}

	int findCeil(Node root, int key) {
		int res = -1;
		while (root != null) {
			if (root.data == key) {
				return root.data;
			} else if (root.data < key) {
				root = root.right;
			} else {
				res = root.data;
				root = root.left;
			}
		}
		return res;
	}

	static ArrayList<Integer> verticalOrder(Node root) {
		TreeMap<Integer, ArrayList<Integer>> t = new TreeMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		verticalOrderUtil(root, res, t);
		for (Map.Entry<Integer, ArrayList<Integer>> p : t.entrySet()) {
			ArrayList<Integer> al = p.getValue();
			for (int x : al)
				res.add(x);
		}
		return res;
	}

	static void verticalOrderUtil(Node root, ArrayList<Integer> res, TreeMap<Integer, ArrayList<Integer>> t) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(root, 0));
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			Node curr = pair.node;
			int h = pair.dis;
			if (t.containsKey(h))
				t.get(h).add(curr.data);
			else {
				ArrayList<Integer> al = new ArrayList<>();
				al.add(curr.data);
				t.put(h, al);
			}
			if (curr.left != null) {
				q.add(new Pair(curr.left, h - 1));
			}
			if (curr.right != null) {
				q.add(new Pair(curr.right, h + 1));
			}
		}
	}

	static ArrayList<Integer> topView(Node root) {
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		topViewUtil(root, res, t);
		for (Map.Entry<Integer, Integer> p : t.entrySet()) {
			res.add(p.getValue());
		}
		return res;
	}

	static void topViewUtil(Node root, ArrayList<Integer> res, TreeMap<Integer, Integer> t) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(root, 0));
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			Node curr = pair.node;
			int h = pair.dis;
			if (!t.containsKey(h))
				t.put(h, curr.data);
			if (curr.left != null) {
				q.add(new Pair(curr.left, h - 1));
			}
			if (curr.right != null) {
				q.add(new Pair(curr.right, h + 1));
			}
		}
	}

	public ArrayList<Integer> bottomView(Node root) {
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		bottomViewUtil(root, res, t);
		for (Map.Entry<Integer, Integer> p : t.entrySet()) {
			res.add(p.getValue());
		}
		return res;
	}

	static void bottomViewUtil(Node root, ArrayList<Integer> res, TreeMap<Integer, Integer> t) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(root, 0));
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			Node curr = pair.node;
			int h = pair.dis;
			t.put(h, curr.data);
			if (curr.left != null) {
				q.add(new Pair(curr.left, h - 1));
			}
			if (curr.right != null) {
				q.add(new Pair(curr.right, h + 1));
			}
		}
	}

	boolean isBST(Node root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean isBSTUtil(Node root, int min, int max) {
		if (root == null)
			return true;
		else
			return (root.data > min && root.data < max && isBSTUtil(root.left, min, root.data)
					&& isBSTUtil(root.right, root.data, max));
	}

	static int result;

	static int maxDiff(Node root, int K) {
		result = Integer.MAX_VALUE;
		maxDiffUtil(root, K);
		System.out.println(result);
		return result;
	}

	static void maxDiffUtil(Node root, int key) {
		if (root == null)
			return;
		if (root.data == key) {
			result = 0;
		} else {
			int diff = Math.abs(key - root.data);
			result = Math.min(diff, result);
			System.out.println("res : " + result);
		}
		maxDiffUtil(root.left, key);
		maxDiffUtil(root.right, key);
	}

	public Node constructBST(int[] arr) {
		if (arr.length == 0)
			return null;
		else {
			Node head = new Node(arr[0]);
			Queue<Node> q = new LinkedList<Node>();
			q.add(head);
			for (int i = 1; i < arr.length; i = i + 2) {
				Node curr = q.poll();
				curr.left = new Node(arr[i]);
				q.add(curr.left);
				if (i + 1 < arr.length) {
					curr.right = new Node(arr[i + 1]);
					q.add(curr.right);
				}
			}
			return head;
		}
	}

	static int count;

	static int getCount(Node root, int l, int h) {
		count = 0;
		getCountUtil(root, l, h);
		return count;
	}

	static void getCountUtil(Node root, int l, int h) {
		if (root == null) {
			return;
		} else if (root.data >= l && root.data <= h) {
			count++;
			System.out.println("Inside count : " + root.data + "  :  " + count);
		}
		getCountUtil(root.left, l, h);
		getCountUtil(root.right, l, h);
	}

	static int preidx;

	public static Node constructTree(int pre[], int size) {
		if (size == 0)
			return null;
		return constructTreeUtil(pre, size, 0, size - 1);
	}

	public static Node constructTreeUtil(int pre[], int size, int start, int end) {
		if (start > end) {
			return null;
		} else if (start == end) {
			return new Node(pre[start]);
		} else {
			Node head = new Node(pre[start]);
			int mid = -1;
			for (int i = start + 1; i <= end; i++) {
				if (pre[i] > head.data) {
					mid = i;
					break;
				}
			}
			if (mid != -1) {
				head.left = constructTreeUtil(pre, size, start + 1, mid - 1);
				head.right = constructTreeUtil(pre, size, mid, end);
			}
			if (mid == -1) {
				head.left = constructTreeUtil(pre, size, start + 1, end);
				head.right = null;
			}
			return head;
		}
	}

	public List<Integer> merge(Node root1, Node root2) {
		List<Integer> res = new ArrayList<Integer>();
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		insertInTreeSet(root1, t);
		insertInTreeSet(root2, t);
		for (Map.Entry<Integer, Integer> e : t.entrySet()) {
			int count = e.getValue();
			while (count-- > 0) {
				res.add(e.getKey());
			}
		}
		return res;
	}

	public void insertInTreeSet(Node root, TreeMap<Integer, Integer> t) {
		if (root == null)
			return;
		insertInTreeSet(root.left, t);
		if (t.containsKey(root.data)) {
			t.put(root.data, t.get(root.data) + 1);
		} else {
			t.put(root.data, 1);
		}
		insertInTreeSet(root.right, t);
	}

	static Node prevN = null, first = null, second = null;

	public Node correctBST(Node root) {
		fixSwappedUtil(root);
		swapDataUtil(first, second);
		return root;
	}

	void fixSwappedUtil(Node root) {
		if (root == null)
			return;
		fixSwappedUtil(root.left);
		if (prevN != null && root.data < prevN.data) {
			if (first == null)
				first = prevN;
			second = root;
		}
		prevN = root;
		fixSwappedUtil(root.right);
	}

	void swapDataUtil(Node node1, Node node2) {
		int temp = node1.data;
		node1.data = node2.data;
		node2.data = temp;
	}

}

class Pair {
	Node node;
	int dis;

	Pair(Node node, int dis) {
		this.node = node;
		this.dis = dis;
	}
}