package gfg.week15.binarySearchTree;

import java.text.DecimalFormat;
import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		float b = 12.5679f;
		// DecimalFormat df = new DecimalFormat("###.##");
		// System.out.println("Rounded double (DecimalFormat) : " + df.format(b));
		// float a = Math.round(14.2456, 2);
		// System.out.println(a);
		float k1 = 1.0f;
		float k2 = 1.0f;
		float k3 = 1.0f;
		float v = 9.59f;
		DecimalFormat df = new DecimalFormat("###.##");
		float res = Float.parseFloat(df.format(100f / (k1 * k2 * k3 * v)));
		if (res > 9.58f) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
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

	public Node delete(Node root, int key) {
		if (root == null)
			return null;
		else if (root.data > key)
			root.left = delete(root.left, key);
		else if (root.data < key)
			root.right = delete(root.right, key);
		else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			else {
				Node succ = getSucc(root);
				root.data = succ.data;
				root.right = delete(root.right, succ.data);
			}
		}
		return root;
	}

	public Node getSucc(Node root) {
		Node curr = root.right;
		while (curr != null && curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	Node floor(Node root, int x) {
		Node res = null;
		while (root != null) {
			if (root.data == x) {
				return root;
			} else if (root.data > x) {
				root = root.left;
			} else {
				res = root;
				root = root.right;
			}
		}
		return res;
	}

	Node Ceil(Node root, int x) {
		Node res = null;
		while (root != null) {
			if (root.data == x) {
				return root;
			} else if (root.data < x) {
				root = root.right;
			} else {
				res = root;
				root = root.left;
			}
		}
		return res;
	}

	void ceilingOnLeft(int[] arr) {
		int n = arr.length;
		System.out.print("-1 ");
		TreeSet<Integer> s = new TreeSet<Integer>();
		s.add(arr[0]);
		for (int i = 1; i < n; i++) {
			if (s.ceiling(arr[i]) != null) {
				System.out.print(s.ceiling(arr[i]) + " ");
			} else {
				System.out.print("-1 ");
			}
			s.add(arr[i]);
		}
	}

	NodeUp KthSmallest(NodeUp root, int k) {
		if (root == null)
			return null;
		int count = root.lcount + 1;
		if (count == k)
			return root;
		if (count > k)
			return KthSmallest(root.left, k);
		else
			return KthSmallest(root.right, k - count);
	}

	boolean checkForBST(Node root) {
		return checkForBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean checkForBSTUtil(Node root, int min, int max) {
		if (root == null)
			return true;
		else
			return (root.data > min && root.data < max && checkForBSTUtil(root.left, min, root.data)
					&& checkForBSTUtil(root.right, root.data, max));
	}

	static int prev = Integer.MIN_VALUE;

	boolean checkBST(Node root) {
		if (root == null)
			return true;
		if (!checkBST(root.left))
			return false;
		if (root.data <= prev)
			return false;
		prev = root.data;
		return checkBST(root.right);
	}

	static Node prevN = null, first = null, second = null;

	void fixSwapped(Node root) {
		fixSwappedUtil(root);
		swapDataUtil(first, second);
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

	static HashSet<Integer> h = new HashSet<Integer>();

	boolean checkPairSum(Node root, int sum) {
		if (root == null)
			return false;
		if (checkPairSum(root.left, sum))
			return true;
		if (h.contains(sum - root.data))
			return true;
		h.add(root.data);
		return checkPairSum(root.right, sum);
	}

	static TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

	void findVerticalSum(Node root) {
		findVerticalSumUtil(root, 0);
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			System.out.print(e.getValue() + " ");
		}
	}

	void findVerticalSumUtil(Node root, int dis) {
		if (root == null)
			return;
		findVerticalSumUtil(root.left, dis - 1);
		if (map.containsKey(dis)) {
			map.put(dis, root.data + map.get(dis));
		} else {
			map.put(dis, root.data);
		}
		findVerticalSumUtil(root.right, dis + 1);
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

}

class Node {
	int data;
	Node left;
	Node right;

	Node(int k) {
		data = k;
	}
}

class NodeUp {

	int data;
	NodeUp left;
	NodeUp right;
	int lcount;

	NodeUp(int k) {
		data = k;
	}

}