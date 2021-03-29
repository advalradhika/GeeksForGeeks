package gfg.week13.dequeue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class videoSolutions {

	public static void main(String[] args) {
		Deque<Integer> d = new LinkedList<Integer>();
		d.offerFirst(10);
		d.offerLast(20);
		d.offerFirst(5);
		d.offerLast(15);
		// System.out.println(d.peekFirst());
		// System.out.println(d.peekLast());
		d.pollFirst();
		d.pollLast();
		// System.out.println(d.peekFirst());
		// System.out.println(d.peekLast());
		Iterator it = d.iterator();
		/*
		 * while (it.hasNext()) System.out.print(it.next() + " "); System.out.println();
		 */
		// Traversal using for-each
		/*
		 * for (int x : d) System.out.print(x + " "); System.out.println();
		 */
		int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
		getMaxKEffiecient(arr, 3);

	}

	void getMaxKNaive(int[] arr, int k) {
		int n = arr.length;
		for (int i = 0; i < n - k + 1; i++) {
			int mx = arr[i];
			for (int j = i + 1; j < i + k; j++) {
				mx = Math.max(mx, arr[j]);
			}
			System.out.println(mx + " ");
		}
	}

	/////// Keep size of dequeue as k
	/////// front of dequeue stores max of current window
	/////// Before insertion delete all smaller elements
	static void getMaxKEffiecient(int[] arr, int k) {
		int n = arr.length;
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		for (int i = 0; i < k; i++) {
			while (!dq.isEmpty() && arr[i] >= arr[(int) dq.peekLast()]) {
				dq.removeLast();
			}
			dq.addLast(i);
		}
		System.out.println("dq : " + dq);
		for (int i = k; i < n; i++) {
			System.out.println(arr[dq.peekFirst()] + " ");
			while (!dq.isEmpty() && (int) dq.peekFirst() <= i - k) {
				dq.removeFirst();
			}
			while (!dq.isEmpty() && arr[i] >= arr[(int) dq.peekLast()]) {
				dq.removeLast();
			}
			dq.addLast(i);
		}
		System.out.print(arr[dq.peekFirst()] + " ");
	}

	int firstCircularTourNaive(int[] petrol, int[] dis) {
		for (int start = 0; start < petrol.length; start++) {
			int end = start;
			int currpet = 0;
			while (true) {
				currpet += petrol[end] - dis[end];
				if (currpet < 0)
					break;
				end = (end + 1) % petrol.length;
				if (end == start)
					return start + 1;
			}
		}
		return -1;
	}

	int firstCircularTourBetter(int[] petrol, int[] dis) {
		int start = 0, prevpet = 0, currpet = 0;
		for (int i = 0; i < petrol.length; i++) {
			currpet += petrol[i] - dis[i];
			if (currpet < 0) {
				start = i + 1;
				prevpet += currpet;
				currpet = 0;
			}
		}
		return (currpet + prevpet) >= 0 ? start + 1 : -1;
	}
}

class Dequeue {
	int[] arr;
	int cap, size, front;

	Dequeue(int c) {
		arr = new int[c];
		cap = c;
		size = 0;
		front = 0;
	}

	boolean isEmpty() {
		return size == 0;
	}

	boolean isFull() {
		return size == cap;
	}

	int getFront() {
		if (isEmpty())
			return -1;
		else
			return arr[front];
	}

	int getRear() {
		if (isEmpty())
			return -1;
		else
			return (front + size - 1) % cap;
	}

	void insertFront(int x) {
		if (isFull())
			return;
		front = (front - 1 + cap) % cap;
		arr[front] = x;
		size++;
	}

	void insertRear(int x) {
		if (isFull())
			return;
		int newrear = (front + size) % cap;
		arr[newrear] = x;
		size++;
	}

	int deleteFront() {
		if (isEmpty())
			return -1;
		int s = arr[front];
		front = (front + 1) % cap;
		size--;
		return s;
	}

	int deleteRear() {
		int rear = (front + size - 1) % cap;
		int s = arr[rear];
		size--;
		return s;
	}
}

class ArrayDequeCustom {

	Deque<Integer> dq;

	ArrayDequeCustom() {
		dq = new ArrayDeque<Integer>();
	}

	void insertMin(int x) {
		dq.offerFirst(x);
	}

	void insertMax(int x) {
		dq.offerLast(x);
	}

	int getMin() {
		return dq.peekFirst();
	}

	int getMax() {
		return dq.peekLast();
	}

	int extractMin() {
		return dq.pollFirst();
	}

	int extractMax() {
		return dq.pollLast();
	}
}
