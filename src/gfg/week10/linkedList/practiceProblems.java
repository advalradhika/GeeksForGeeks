package gfg.week10.linkedList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class practiceProblems {

	public static void main(String[] args) {
		Node head = new Node(5);
		Node first = new Node(4);
		Node second = new Node(3);
		Node third = new Node(6);
		Node fourth = new Node(7);
		Node fifth = new Node(0);

		// traversal(head);
		head.next = first;
		first.next = second;
		second.next = third;
		third.next = fourth;
		fourth.next = fifth;
		// traversal(head);
		// head = swapkthnode(head, 6, 1);
		// traversal(head);

		XNode head1 = new XNode(1);
		XNode first1 = new XNode(2);
		XNode second1 = new XNode(3);
		XNode third1 = new XNode(4);
		XNode fourth1 = new XNode(5);
		head1.next = first1;
		first1.next = second1;
		second1.next = third1;
		third1.next = fourth1;
		head1.arb = third1;
		first1.arb = head1;
		second1.arb = fourth1;
		third1.arb = second1;
		fourth1.arb = first1;
		// traversal(head1);
		// head1 = copyList(head1);
		// traversal(head1);
		// head = mergeSort(head);
		// second.next = third;
		// traversal(head);
		// traversal(head2);
		// head = addLists(head, head2);
		// traversal(head);
		sNode headA = new sNode(0);
		sNode firstA = new sNode(2);
		sNode secondA = new sNode(4);
		sNode headB = new sNode(1);
		sNode firstB = new sNode(3);
		headA.next = firstA;
		firstA.prev = headA;
		firstA.next = secondA;
		secondA.prev = firstA;
		secondA.next = headB;
		headB.next = firstB;
		firstB.prev = headB;
		traversalDoubly(headA);
		traversalDoubly(headB);
		// sNode finalnode = sortedMergeDoubly(headA, headB);
		sNode finalnode = sortDoubly(headA);
		traversalDoubly(finalnode);
		// System.out.println(x);
		// second.prev = first;
		// first.prev = head;
		// traversal(head);
		// traversal(head2);
		// head = sortedMerge(head, head2);
		// traversal(head);
		// head = removeDuplicatesUnsorted(head);
		// traversal(head);
	}

	public static void traversal(Node head) {
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static void traversal(XNode head) {
		XNode curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static void traversalDoubly(sNode head) {
		sNode curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static int getCount(Node head) {
		int count = 0;
		for (Node curr = head; curr != null; curr = curr.next)
			count++;
		return count;
	}

	Node insertAtBeginning(Node head, int x) {
		Node temp = new Node(x);
		temp.next = head;
		return temp;
	}

	// Should insert a node at the end
	Node insertAtEnd(Node head, int x) {
		Node temp = new Node(x);
		if (head == null) {
			head = temp;
			return head;
		}
		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = temp;
		return head;
	}

	static void addNode(sNode head, int pos, int data) {
		sNode temp = new sNode(data);
		sNode curr = head;
		while (pos-- != 0) {
			curr = curr.next;
		}
		temp.next = curr.next;
		if (curr.next != null) {
			curr.next.prev = temp;
		}
		temp.prev = curr;
		curr.next = temp;
	}

	public static Node insertInMid(Node head, int data) {
		Node slow = head, fast = head;
		Node temp = new Node(data);
		while (fast != null && fast.next != null) {
			if (fast.next.next != null)
				slow = slow.next;
			fast = fast.next.next;
		}
		temp.next = slow.next;
		slow.next = temp;
		return head;
	}

	public boolean isIdentical(Node head1, Node head2) {
		Node curr1 = head1;
		Node curr2 = head2;
		while (curr1 != null && curr2 != null) {
			if (curr1.data != curr2.data)
				return false;
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		if (curr1 == null && curr2 == null)
			return true;
		else
			return false;
	}

	void deleteNode(Node node) {
		Node curr = node;
		curr.data = curr.next.data;
		curr.next = curr.next.next;
	}

	public static Node removeDuplicates(Node root) {
		Node prev = root;
		Node curr = prev.next;
		while (curr != null) {
			if (prev.data != curr.data) {
				prev = prev.next;
				curr = curr.next;
			} else {
				prev.next = curr.next;
				curr = curr.next;
			}
		}
		return root;
	}

	public static Node removeDuplicatesUnsorted(Node head) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Node prev = head;
		Node curr = prev.next;
		list.add(prev.data);
		while (curr != null) {
			if (!list.contains(curr.data)) {
				list.add(curr.data);
				prev = prev.next;
				curr = curr.next;
			} else {
				prev.next = curr.next;
				curr = curr.next;
			}
		}
		return head;
	}

	public static Node sortedMerge(Node headA, Node headB) {
		if (headA == null)
			return headB;
		if (headB == null)
			return headA;
		Node head = null;
		Node currtail = null;
		if (headA.data <= headB.data) {
			head = currtail = headA;
			headA = headA.next;
		} else {
			head = currtail = headB;
			headB = headB.next;
		}
		while (headA != null && headB != null) {
			if (headA.data <= headB.data) {
				currtail.next = headA;
				currtail = headA;
				headA = headA.next;
			} else {
				currtail.next = headB;
				currtail = headB;
				headB = headB.next;
			}
		}
		if (headA == null) {
			currtail.next = headB;
		} else {
			currtail.next = headA;
		}
		return head;
	}

	public static int getNthFromLast(Node head, int n) {
		Node slow = head;
		int count = 0;
		while (slow != null) {
			slow = slow.next;
			count++;
		}
		if (count < n) {
			return -1;
		} else {
			slow = head;
			for (int i = 1; i < count - n + 1; i++) {
				slow = slow.next;
			}
			return slow.data;
		}
	}

	public static Node getNthFromLastextnd(Node head, int n) {
		Node slow = head;
		int count = 0;
		while (slow != null) {
			slow = slow.next;
			count++;
		}
		slow = head;
		for (int i = 0; i < count - n; i++) {
			slow = slow.next;
		}
		return slow;
	}

	public static Node swapkthnode(Node head, int num, int K) {
		if (head == null) {
			return head;
		}
		if (K > num) {
			return head;
		}
		if (2 * K - 1 == num) {
			return head;
		}
		Node x = head;
		Node x_prev = null;
		for (int i = 1; i < K; i++) {
			x_prev = x;
			x = x.next;
		}

		Node y = head;
		Node y_prev = null;
		for (int i = 1; i < num - K + 1; i++) {
			y_prev = y;
			y = y.next;
		}

		if (x_prev != null)
			x_prev.next = y;

		if (y_prev != null)
			y_prev.next = x;

		Node temp = x.next;
		x.next = y.next;
		y.next = temp;
		if (K == 1)
			head = y;
		if (K == num)
			head = x;
		return head;
	}

	public static Node reverseList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node curr = head, prev = null, next = null;
		while (curr.next != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		curr.next = prev;
		return next;
	}

	public static boolean detectLoop(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				return true;
		}
		return false;
	}

	static int countNodesinLoop(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				int count = 0;
				do {
					slow = slow.next;
					count++;
				} while (slow != fast);
				return count;
			}
		}
		return 0;
	}

	public static void removeLoop(Node head) {
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				slow = head;
				while (slow.next != fast.next) {
					slow = slow.next;
					fast = fast.next;
				}
				fast.next = null;
			}
		}
	}

	public static Node rotate(Node head, int k) {
		Node curr = head, newhead;
		int count = 0;
		while (count < k - 1) {
			curr = curr.next;
			count++;
		}
		if (curr.next == null) {
			return head;
		}
		newhead = curr.next;
		Node newcurr = curr;
		while (newcurr.next != null) {
			newcurr = newcurr.next;
		}
		newcurr.next = head;
		curr.next = null;
		return newhead;
	}

	public static Node addLists(Node first, Node second) {
		first = reverseList(first);
		second = reverseList(second);
		int dfirst, dsecond, carry = 0, dsum;
		Node cfirst = first, csecond = second, head = null, curr = null;
		while (cfirst != null || csecond != null) {
			dfirst = cfirst == null ? 0 : cfirst.data;
			dsecond = csecond == null ? 0 : csecond.data;
			dsum = dfirst + dsecond + carry;
			Node temp = new Node(dsum % 10);
			carry = dsum / 10;
			if (head == null) {
				head = temp;
				curr = temp;
			} else {
				curr.next = temp;
				curr = curr.next;
			}
			if (cfirst != null)
				cfirst = cfirst.next;
			if (csecond != null)
				csecond = csecond.next;
		}
		if (carry > 0) {
			Node temp = new Node(carry);
			curr.next = temp;
			curr = curr.next;
		}
		curr.next = null;
		return reverseList(head);
	}

	public static Node findMiddle(Node head) {
		if (head == null)
			return null;
		Node slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static Node pairwise_swap(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node curr = head.next.next;
		Node prev = head;
		head = head.next;
		head.next = prev;
		while (curr != null && curr.next != null) {
			prev.next = curr.next;
			prev = curr;
			Node nxt = curr.next.next;
			curr.next.next = curr;
			curr = nxt;
		}
		prev.next = curr;
		return head;
	}

	static boolean isPalindrome(Node head) {
		Node mid = reverseList(findMiddle(head));
		Node curr = head;
		while (mid != null) {
			if (curr.data != mid.data) {
				return false;
			} else {
				curr = curr.next;
				mid = mid.next;
			}
		}
		return true;
	}

	public static Node getMiddleMerge(Node head) {
		if (head == null)
			return head;
		Node slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	static Node mergeSort(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node mid = getMiddleMerge(head);
		Node midnxt = mid.next;
		mid.next = null;
		Node a = mergeSort(head);
		Node b = mergeSort(midnxt);
		return sortedMerge(a, b);
	}

	static Node segregate(Node head) {
		Node s0, s1, s2, e0, e1, e2;
		s0 = s1 = s2 = e0 = e1 = e2 = null;
		Node curr = head;
		if (curr.data == 0) {
			s0 = e0 = curr;
			curr = curr.next;
		} else if (curr.data == 1) {
			s1 = e1 = curr;
			curr = curr.next;
		} else {
			s2 = e2 = curr;
			curr = curr.next;
		}
		while (curr != null) {
			if (curr.data == 0) {
				if (s0 == null) {
					s0 = e0 = curr;
				} else {
					e0.next = curr;
					e0 = e0.next;
				}
			}
			if (curr.data == 1) {
				if (s1 == null) {
					s1 = e1 = curr;
				} else {
					e1.next = curr;
					e1 = e1.next;
				}
			}
			if (curr.data == 2) {
				if (s2 == null) {
					s2 = e2 = curr;
				} else {
					e2.next = curr;
					e2 = e2.next;
				}
			}
			curr = curr.next;
		}
		if (s0 == null && s1 == null) {
			e2.next = null;
			return s2;
		}
		if (s2 == null && s1 == null) {
			e0.next = null;
			return s0;
		}
		if (s0 == null && s2 == null) {
			e1.next = null;
			return s1;
		}
		if (s0 == null) {
			e1.next = s2;
			e2.next = null;
			return s1;
		}
		if (s1 == null) {
			e0.next = s2;
			e2.next = null;
			return s0;
		}
		if (s2 == null) {
			e0.next = s1;
			e1.next = null;
			return s0;
		}
		e0.next = s1;
		e1.next = s2;
		e2.next = null;
		return s0;
	}

	static sNode sortDoubly(sNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		sNode mid = getMiddleMerge(head);
		sNode midnxt = mid.next;
		mid.next = null;
		sNode a = sortDoubly(head);
		sNode b = sortDoubly(midnxt);
		return sortedMergeDoubly(a, b);
	}

	public static sNode getMiddleMerge(sNode head) {
		if (head == null)
			return head;
		sNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static sNode sortedMergeDoubly(sNode headA, sNode headB) {
		if (headA == null)
			return headB;
		if (headB == null)
			return headA;
		sNode head = null;
		sNode currtail = null;
		if (headA.data <= headB.data) {
			head = currtail = headA;
			head.prev = null;
			headA = headA.next;
		} else {
			head = currtail = headB;
			head.prev = null;
			headB = headB.next;
		}
		while (headA != null && headB != null) {
			if (headA.data <= headB.data) {
				currtail.next = headA;
				headA.prev = currtail;
				currtail = headA;
				headA = headA.next;
			} else {
				currtail.next = headB;
				headB.prev = currtail;
				currtail = headB;
				headB = headB.next;
			}
		}
		if (headA == null) {
			currtail.next = headB;
			headB.prev = currtail;
		} else {
			currtail.next = headA;
			headA.prev = currtail;
		}
		return head;
	}

	Node mergeKList(Node[] a, int N) {
		Node curr = sortedMerge(a[0], a[1]);
		for (int i = 2; i < a.length; i++) {
			curr = sortedMerge(curr, a[i]);
		}
		return curr;
	}

	static int intersectPoint(Node head1, Node head2) {
		int c1 = getCount(head1);
		int c2 = getCount(head2);
		int d = Math.abs(c1 - c2);
		if (c1 < c2) {
			return getintersectPoint(head2, head1, d);
		} else {
			return getintersectPoint(head1, head2, d);
		}

	}

	static int getintersectPoint(Node head1, Node head2, int d) {
		int i;
		Node current1 = head1;
		Node current2 = head2;
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

	public static XNode copyList(XNode head) {
		XNode next;
		for (XNode curr = head; curr != null;) {
			next = curr.next;
			curr.next = new XNode(curr.data);
			curr.next.next = next;
			curr = next;
		}
		System.out.println("here");
		for (XNode curr = head; curr != null; curr = curr.next.next) {
			curr.next.arb = curr.arb != null ? curr.arb.next : null;
		}
		System.out.println("here");
		XNode original = head, copy = head.next;

		XNode temp = copy;

		while (original != null && copy != null) {
			original.next = original.next != null ? original.next.next : original.next;
			copy.next = copy.next != null ? copy.next.next : copy.next;
			original = original.next;
			copy = copy.next;
		}

		return temp;
	}

	Node cur; // Dont change the variable name, its used in main function
	int carry; // Dont change the variable name, its used in main function

	void addCarryToRemaining(Node head, LinkedList res) {
		if (head != cur) {
			addCarryToRemaining(head.next, res);
			int sum = carry + head.data;
			carry = sum / 10;
			sum %= 10;
			///// Uncomment for complete function
			// res.push(sum);
		}
	}

	void addSameSize(Node head1, Node head2, LinkedList res) {
		if (head1 == null)
			return;
		addSameSize(head1.next, head2.next, res);
		int sum = head1.data + head2.data + carry;
		carry = sum / 10;
		sum = sum % 10;
		///// Uncomment for complete function
		// res.push(sum);
	}
}

class sNode {
	int data;
	sNode next;
	sNode prev;

	sNode(int a) {
		data = a;
		next = null;
		prev = null;
	}
}

class XNode {
	int data;
	XNode next;
	XNode arb;

	XNode(int a) {
		data = a;
		next = null;
		arb = null;
	}
}

class LinkedList {
	Node head;

}

class LRUCache {
	LRUCache(int cap) {
		int c = cap;
	}

	// This method works in O(1)
	/*public static int get(int key) {
		// your code here
	}

	// This method works in O(1)
	public static void set(int key, int value) {
		// your code here
	}*/
}