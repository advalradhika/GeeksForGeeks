package gfg.week13.dequeue;

import java.util.*;

public class practiceProblems {
	public static void main(String[] args) {
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		dq.addLast(45);
		dq.addLast(25);
		dq.addLast(39);
		System.out.println(dq);
		eraseInRange(dq, 0, 1);
		System.out.println(dq);
	}

	public static void push_back_pb(ArrayDeque<Integer> dq, int x) {
		dq.offerLast(x);
	}

	public static void pop_back_ppb(ArrayDeque<Integer> dq) {
		dq.pollLast();
	}

	public static int front_dq(ArrayDeque<Integer> dq) {
		if (dq.size() == 0)
			return -1;
		return dq.peekFirst();
	}

	public static void push_front_pf(ArrayDeque<Integer> dq, int x) {
		dq.offerFirst(x);
	}

	static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		for (int i = 0; i < k; i++) {
			while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
				dq.pollLast();
			}
			dq.offerLast(i);
		}
		for (int i = k; i < n; i++) {
			res.add(arr[dq.peekFirst()]);
			while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
				dq.pollFirst();
			}
			while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
				dq.pollLast();
			}
			dq.offerLast(i);
		}
		res.add(arr[dq.peekFirst()]);
		return res;
	}

	public static void printDeque(ArrayDeque<Integer> deq) {
		for (int e : deq) {
			System.out.print(e + " ");
		}

	}

	public static void left_Rotate_Deq_ByK(ArrayDeque<Integer> deque, int n, int k) {
		while (k-- > 0) {
			deque.offerLast(deque.pollFirst());
		}
	}

	public static void right_Rotate_Deq_ByK(ArrayDeque<Integer> deque, int n, int k) {
		while (k-- > 0) {
			deque.offerFirst(deque.pollLast());
		}
	}

	public static ArrayDeque<Integer> deque_Init(int arr[], int n) {
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		for (int i = 0; i < n; i++)
			deque.addLast(arr[i]);
		return deque;
	}

	public static void eraseAt(ArrayDeque<Integer> deq, int X) {
		Stack<Integer> s = new Stack<Integer>();
		while (X-- > 0) {
			s.push(deq.removeFirst());
		}
		deq.removeFirst();
		while (!s.isEmpty()) {
			deq.offerFirst(s.pop());
		}
	}

	public static void eraseInRange(ArrayDeque<Integer> deq, int start, int end) {
		Stack<Integer> stack = new Stack<Integer>();
		int c = 0;
		while (deq.isEmpty() == false) {
			if (c >= start && c < end) {
				deq.pollFirst();
			} else {
				stack.push(deq.pollFirst());
			}
			c++;
		}
		deq.clear();
		while (stack.isEmpty() == false) {
			deq.addFirst(stack.pop());
		}
	}

	public static void eraseAll(ArrayDeque<Integer> deq) {
		deq.removeAll(deq);
	}
}
