package gfg.week10.linkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class videoSolutions {

	public static void main(String[] args) {
		Node head = new Node(10);
		Node first = new Node(20);
		Node second = new Node(30);
		head.next = first;
		first.next = second;
		// NthNodefromEnd(head, 2);
		// second.next = head;
		// TraversalCircular(head);
		// head = delKthNodeCircular(head, 1);
		// TraversalCircular(head);
		// System.out.println();
		// int x = searchInLinkedList(head, 30);
		// System.out.println(x);
		// insertAtBeginning(head, 5);
		// System.out.println();
		// traversal(head);
		traversal(head);
		head = reverseLinkedListRecursive(head);
		traversal(head);

		DoubleNode dhead = new DoubleNode(10);
		DoubleNode dfirst = new DoubleNode(20);
		DoubleNode dsecond = new DoubleNode(45);
		dhead.next = dfirst;
		dfirst.prev = dhead;
		dfirst.next = dsecond;
		dsecond.prev = dfirst;
		dsecond.next = dhead;
		dhead.prev = dsecond;
		// TraversalCircular(dhead);
	}

	public static void traversal(Node head) {
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static Node insertAtBeginning(Node head, int data) {
		Node temp = new Node(data);
		temp.next = head;
		return temp;
	}

	public static Node insertAtEnd(Node head, int data) {
		Node temp = new Node(data);
		if (head == null) {
			head = temp;
		} else {
			Node curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = temp;
		}
		return head;
	}

	public static Node deleteFirstNode(Node head) {
		if (head == null)
			return head;
		return head.next;
	}

	public static Node deleteLastNode(Node head) {
		Node curr = head;
		if (head == null || head.next == null)
			return null;
		while (curr.next.next != null) {
			curr = curr.next;
		}
		curr.next = null;
		return head;
	}

	public static int searchInLinkedList(Node head, int key) {
		int curridx = 1;
		Node curr = head;
		while (curr != null) {
			if (curr.data == key)
				return curridx;
			curr = curr.next;
			curridx++;
		}
		return -1;
	}

	static int searchRecursive(Node head, int x) {
		if (head == null)
			return -1;
		if (head.data == x)
			return 1;
		else {
			int res = searchRecursive(head.next, x);
			if (res == -1)
				return -1;
			else
				return res + 1;
		}
	}

	public static DoubleNode insertAtBeginningDouble(DoubleNode head, int data) {
		DoubleNode temp = new DoubleNode(data);
		temp.next = head;
		if (head != null) {
			head.prev = temp;
		}
		return temp;
	}

	public static DoubleNode insertAtEndDouble(DoubleNode head, int data) {
		DoubleNode temp = new DoubleNode(data);
		if (head == null) {
			return temp;
		} else {
			DoubleNode curr = new DoubleNode(data);
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = temp;
			temp.prev = curr;
			return head;
		}
	}

	public static DoubleNode deleteFirstNodeDouble(DoubleNode head) {
		if (head == null || head.next == null)
			return null;
		head = head.next;
		head.prev = null;
		return head;
	}

	public static DoubleNode deleteLastNodeDouble(DoubleNode head) {
		DoubleNode curr = head;
		if (head == null || head.next == null)
			return null;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.prev.next = null;
		return head;
	}

	public static void TraversalCircular(Node head) {
		if (head == null)
			return;
		Node r = head;
		do {
			System.out.print(r.data + " ");
			r = r.next;
		} while (r != head);
	}

	public static void TraversalCircular(DoubleNode head) {
		if (head == null)
			return;
		DoubleNode r = head;
		do {
			System.out.print(r.data + " ");
			r = r.next;
		} while (r != head);
	}

	public static Node insertBeginCircular(Node head, int x) {
		Node temp = new Node(x);
		if (head == null)
			temp.next = temp;
		else {
			Node curr = head;
			while (curr.next != head)
				curr = curr.next;
			curr.next = temp;
			temp.next = head;
		}
		return temp;
	}

	public static Node insertEndCircular(Node head, int x) {
		Node temp = new Node(x);
		if (head == null) {
			temp.next = temp;
			return temp;
		} else {
			Node curr = head;
			while (curr.next != head)
				curr = curr.next;
			curr.next = temp;
			temp.next = head;
			return head;
		}
	}

	public static Node delHeadCircular(Node head) {
		if (head == null)
			return null;
		if (head.next == head)
			return null;
		Node curr = head;
		while (curr.next != head)
			curr = curr.next;
		curr.next = head.next;
		return (curr.next);
		//////// Alternate O(1) Solution
		/*
		 * head.next = head.next.data; head.next = head.next.next; return head;
		 */
	}

	public static Node delKthNodeCircular(Node head, int k) {
		if (k == 1)
			return delHeadCircular(head);
		Node curr = head;
		for (int i = 0; i < k - 2; i++) {
			curr = curr.next;
		}
		curr.next = curr.next.next;
		return head;
		//////// Alternate O(1) Solution
		/*
		 * head.next = head.next.data; head.next = head.next.next; return head;
		 */
	}

	public static DoubleNode insertAtHeadCircularDouble(DoubleNode head, int x) {
		DoubleNode temp = new DoubleNode(x);
		if (head == null) {
			temp.next = temp;
			temp.prev = temp;
			return temp;
		}
		temp.prev = head.prev;
		temp.next = head;
		head.prev.next = temp;
		head.prev = temp;
		return temp;
	}

	public static DoubleNode insertAtEndCircularDouble(DoubleNode head, int x) {
		DoubleNode temp = new DoubleNode(x);
		if (head == null) {
			temp.next = temp;
			temp.prev = temp;
			return temp;
		}
		temp.prev = head.prev;
		temp.next = head;
		head.prev.next = temp;
		head.prev = temp;
		return head;
	}

	public static void findMiddle(Node head) {
		if (head == null)
			return;
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		System.out.println("Middle : " + slow.data);
	}

	public static void NthNodefromEnd(Node head, int n) {
		if (head == null)
			return;
		Node first = head, second = head;
		for (int i = 0; i < n; i++) {
			if (second == null)
				return;
			second = second.next;
		}
		while (second != null) {
			second = second.next;
			first = first.next;
		}
		System.out.println(first.data);
	}

	public static Node reverseLinkedListIterative(Node head) {
		if (head == null)
			return null;
		Node prev = null, curr = head;
		Node next;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	public static Node reverseLinkedListRecursive(Node head) {
		if (head == null || head.next == null)
			return head;
		Node resthead = reverseLinkedListRecursive(head.next);
		Node resttail = head.next;
		resttail.next = head;
		head.next = null;
		return resthead;
	}

	/*
	 * public static Node reverseEachKNodeGroup(Node head, int k) { if (head ==
	 * null) return null; Node prev = null, curr = head, next; int count = 0; while
	 * (curr != null && count < k) { next = curr.next; curr.next = prev; prev =
	 * curr; curr = next; count++; } }
	 */

	public static boolean detectLoop(Node head) {
		Node temp = new Node(0);
		Node curr = head;
		while (curr != null) {
			if (curr.next == temp)
				return true;
			if (curr.next == null)
				return false;
			Node currnxt = curr.next;
			curr.next = temp;
			curr = currnxt;
		}
		return false;
	}

	public static boolean detectLoophashing(Node head) {
		HashSet<Node> hash = new HashSet<Node>();
		for (Node i = head; i != null; i = i.next) {
			if (hash.contains(i))
				return true;
			hash.add(i);
		}
		return false;
	}

	public static boolean detectLoopFloydCycle(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				return true;
		}
		return false;
	}

	public static int lengthOfLoop(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				slow = head;
				int count = 0;
				while (slow.next != fast.next) {
					slow = slow.next;
					fast = fast.next;
					count++;
				}
				return count;
			}
		}
		return 0;
	}

	static void detectRemoveLoop(Node head) {
		Node slow = head, fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}
		if (slow != fast)
			return;
		slow = head;
		while (slow.next != fast.next) {
			slow = slow.next;
			fast = fast.next;
		}
		fast.next = null;
	}

	static void deleteNode(Node ptr) {
		Node temp = ptr.next;
		ptr.data = temp.data;
		ptr.next = temp.next;
	}

	static Node segregate(Node head) {
		Node eS = null, eE = null, oS = null, oE = null;
		for (Node curr = head; curr != null; curr = curr.next) {
			int x = curr.data;
			if (x % 2 == 0) {
				if (eS == null) {
					eS = curr;
					eE = eS;
				} else {
					eE.next = curr;
					eE = eE.next;
				}
			} else {
				if (oS == null) {
					oS = curr;
					oE = oS;
				} else {
					oE.next = curr;
					oE = oE.next;
				}
			}
		}
		if (oS == null || eS == null)
			return head;
		eE.next = oS;
		oE.next = null;
		return eS;
	}

	static int getIntersectionHashing(Node head1, Node head2) {
		HashSet<Node> s = new HashSet<>();
		Node curr = head1;
		while (curr != null) {
			s.add(curr);
			curr = curr.next;
		}
		curr = head2;
		while (curr != null) {
			if (s.contains(curr))
				return curr.data;
			curr = curr.next;
		}
		return -1;
	}

	int getNode(Node head1, Node head2) {
		int c1 = getCount(head1);
		int c2 = getCount(head2);
		int d;

		if (c1 > c2) {
			d = c1 - c2;
			return _getIntesectionNode(d, head1, head2);
		} else {
			d = c2 - c1;
			return _getIntesectionNode(d, head2, head1);
		}
	}

	int _getIntesectionNode(int d, Node node1, Node node2) {
		int i;
		Node current1 = node1;
		Node current2 = node2;
		for (i = 0; i < d; i++) {
			if (current1 == null) {
				return -1;
			}
			current1 = current1.next;
		}
		while (current1 != null && current2 != null) {
			if (current1 == current2) {
				return current1.data;
			}
			current1 = current1.next;
			current2 = current2.next;
		}

		return -1;
	}

	int getCount(Node node) {
		Node current = node;
		int count = 0;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	static void pairwiseSwapData(Node head) {
		Node curr = head;
		while (curr != null && curr.next != null) {
			int temp = curr.data;
			curr.data = curr.next.data;
			curr.next.data = temp;
			curr = curr.next.next;
		}
	}

	static Node pairwiseSwapReference(Node head) {
		if (head == null || head.next == null)
			return head;
		Node curr = head.next.next;
		Node prev = head;
		head = head.next;
		head.next = prev;
		while (curr != null && curr.next != null) {
			prev.next = curr.next;
			prev = curr;
			Node next = curr.next.next;
			curr.next.next = curr;
			curr = next;
		}
		prev.next = curr;
		return head;
	}

	public static cNode clone(cNode head) {
		HashMap<cNode, cNode> hm = new HashMap<cNode, cNode>();
		for (cNode curr = head; curr != null; curr = curr.next)
			hm.put(curr, new cNode(curr.data));

		for (cNode curr = head; curr != null; curr = curr.next) {
			cNode cloneCurr = hm.get(curr);
			cloneCurr.next = hm.get(curr.next);
			cloneCurr.random = hm.get(curr.random);
		}
		cNode head2 = hm.get(head);

		return head2;
	}

	public static cNode cloneEfficent(cNode head) {
		cNode next, temp;
		for (cNode curr = head; curr != null;) {
			next = curr.next;
			curr.next = new cNode(curr.data);
			curr.next.next = next;
			curr = next;
		}
		for (cNode curr = head; curr != null; curr = curr.next.next) {
			curr.next.random = (curr.random != null) ? (curr.random.next) : null;
		}

		cNode original = head, copy = head.next;

		temp = copy;

		while (original != null && copy != null) {
			original.next = original.next != null ? original.next.next : original.next;
			copy.next = copy.next != null ? copy.next.next : copy.next;
			original = original.next;
			copy = copy.next;
		}

		return temp;
	}

}

class cNode {
	int data;
	cNode next;
	cNode random;

	cNode(int x) {
		data = x;
		next = null;
		random = null;
	}

}

class tNode {
	int key;
	int value;
	tNode pre;
	tNode next;

	public tNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

class LRU {
	private HashMap<Integer, tNode> map;
	private int capacity, count;
	private tNode head, tail;

	LRU(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		head = new tNode(0, 0);
		tail = new tNode(0, 0);
		head.next = tail;
		tail.pre = head;
		head.pre = null;
		tail.next = null;
		count = 0;
	}

	public void deleteNode(tNode tNode) {
		tNode.pre.next = tNode.next;
		tNode.next.pre = tNode.pre;
	}

	public void addToHead(tNode tNode) {
		tNode.next = head.next;
		tNode.next.pre = tNode;
		tNode.pre = head;
		head.next = tNode;
	}

	public int get(int key) {
		if (map.get(key) != null) {
			tNode tNode = map.get(key);
			int result = tNode.value;
			deleteNode(tNode);
			addToHead(tNode);
			System.out.println("Got the value : " + result + " for the key: " + key);
			return result;
		}
		System.out.println("Did not get any value" + " for the key: " + key);
		return -1;
	}

	public void set(int key, int value) {
		System.out.println("Going to set the (key, " + "value) : (" + key + ", " + value + ")");
		if (map.get(key) != null) {
			tNode tNode = map.get(key);
			tNode.value = value;
			deleteNode(tNode);
			addToHead(tNode);
		} else {
			tNode tNode = new tNode(key, value);
			map.put(key, tNode);
			if (count < capacity) {
				count++;
				addToHead(tNode);
			} else {
				map.remove(tail.pre.key);
				deleteNode(tail.pre);
				addToHead(tNode);
			}
		}
	}
}