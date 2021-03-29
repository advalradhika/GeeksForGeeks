package gfg.week12.queue;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Queue<Integer> rev(Queue<Integer> q) {
		Stack<Integer> s = new Stack<Integer>();
		while (!q.isEmpty()) {
			s.push(q.poll());
		}
		while (!s.isEmpty()) {
			q.offer(s.pop());
		}
		return q;
	}

	static ArrayList<String> generate(long N) {
		ArrayList<String> res = new ArrayList<String>();
		Queue<String> s = new LinkedList<String>();
		s.offer("1");
		String x;
		while (N-- > 0) {
			x = s.poll();
			s.offer(x + "0");
			s.offer(x + "1");
			res.add(x);
		}
		return res;
	}

	public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
		Queue<Integer> temp = new LinkedList<Integer>();
		while (k-- > 0) {
			temp.offer(q.poll());
		}
		q = Reverse(q);
		while (!temp.isEmpty()) {
			q.offer(temp.poll());
		}
		return Reverse(q);
	}

	public Queue<Integer> Reverse(Queue<Integer> q) {
		Stack<Integer> s = new Stack<Integer>();
		while (!q.isEmpty()) {
			s.push(q.poll());
		}
		while (!s.isEmpty()) {
			q.offer(s.pop());
		}
		return q;
	}

	int tour(int petrol[], int distance[]) {
		int start = 0;
		int end = 1;
		int curr_petrol = petrol[start] - distance[start];
		while (end != start || curr_petrol < 0) {
			while (curr_petrol < 0 && start != end) {
				curr_petrol -= petrol[start] - distance[start];
				start = (start + 1) % petrol.length;
				if (start == 0)
					return -1;
			}
			curr_petrol += petrol[end] - distance[end];

			end = (end + 1) % petrol.length;
		}
		return start;
	}
}

class StackQueue {
	Stack<Integer> s1 = new Stack<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();

	void Push(int x) {
		s1.push(x);
	}

	int Pop() {
		if (s1.size() == 0)
			return -1;
		while (s1.size() > 1) {
			s2.push(s1.pop());
		}
		int x = s1.pop();
		while (!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		return x;
	}
}
