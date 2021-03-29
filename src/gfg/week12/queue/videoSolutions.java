package gfg.week12.queue;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		Queue q = null;

	}

	static void reversequeue() {
		Queue<Integer> queue = null;
		Stack<Integer> stack = new Stack<>();
		while (!queue.isEmpty()) {
			stack.add(queue.peek());
			queue.remove();
		}
		while (!stack.isEmpty()) {
			queue.add(stack.peek());
			stack.pop();
		}
	}

	static void reverse(Queue<Integer> q) {
		if (q.isEmpty())
			return;

		int x = q.peek();
		q.remove();
		reverse(q);
		q.add(x);
	}

	static void printFirstN(int n) {
		Queue<String> q = new LinkedList<>();
		q.add("5");
		q.add("6");
		for (int i = 0; i < n; i++) {
			String curr = q.poll();
			System.out.print(curr + " ");
			q.add(curr + "5");
			q.add(curr + "6");
		}
	}

}

class QueueA {
	int front, rear, size;
	int capacity;
	int array[];

	public QueueA(int capacity) {
		this.capacity = capacity;
		front = this.size = 0;
		rear = capacity - 1;
		array = new int[this.capacity];
	}

	boolean isFull(QueueA queue) {
		return (queue.size == queue.capacity);
	}

	boolean isEmpty(QueueA queue) {
		return (queue.size == 0);
	}

	void enqueue(int item) {
		if (isFull(this))
			return;
		this.rear = (this.rear + 1) % this.capacity;
		this.array[this.rear] = item;
		this.size = this.size + 1;
		System.out.println(item + " enqueued to queue");
	}

	int dequeue() {
		if (isEmpty(this))
			return Integer.MIN_VALUE;
		int item = this.array[this.front];
		this.front = (this.front + 1) % this.capacity;
		this.size = this.size - 1;
		return item;
	}

	int front() {
		if (isEmpty(this))
			return Integer.MIN_VALUE;
		return this.array[this.front];
	}

	int rear() {
		if (isEmpty(this))
			return Integer.MIN_VALUE;
		return this.array[this.rear];
	}
}

class Node {
	int key;
	Node next;

	public Node(int key) {
		this.key = key;
		this.next = null;
	}
}

class QueueLL {
	Node front, rear;

	public QueueLL() {
		this.front = this.rear = null;
	}

	void enqueue(int key) {

		Node temp = new Node(key);

		if (this.rear == null) {
			this.front = this.rear = temp;
			return;
		}

		this.rear.next = temp;
		this.rear = temp;
	}

	void dequeue() {

		if (this.front == null)
			return;

		this.front = this.front.next;

		if (this.front == null)
			this.rear = null;
	}
}

class StackX {
	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();

	int top() {
		return q1.peek();
	}

	int size() {
		return q1.size();
	}

	int pop() {
		return q1.poll();
	}

	void push(int x) {
		while (!q1.isEmpty()) {
			q2.offer(q1.poll());
		}
		q1.offer(x);
		while (!q2.isEmpty()) {
			q1.offer(q2.poll());
		}
	}

}