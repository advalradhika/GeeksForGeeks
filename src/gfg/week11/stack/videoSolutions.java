package gfg.week11.stack;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		int[] arr = { 1, 3, 2, 4 };
		String s = "{[()]}";
		// System.out.println(getMaxAreaX(arr, 7));
		System.out.println(isBalanced(s));
	}

	public static boolean isBalanced(String str) {
		ArrayDeque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
				stack.push(str.charAt(i));
			} else {
				if (stack.isEmpty())
					return false;
				else if (!matchBrac(stack.peek(), str.charAt(i)))
					return false;
				else
					stack.pop();
			}
		}
		return stack.isEmpty();
	}

	public static boolean matchBrac(char a, char b) {
		System.out.println("a : " + a + "  b : " + b);
		return ((a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}'));
	}

	public static void printSpanNaive(int arr[], int n) {
		for (int i = 0; i < n; i++) {
			int span = 1;
			for (int j = i - 1; j >= 0 && arr[j] <= arr[i]; j--)
				span++;
			System.out.print(span + " ");
		}
	}

	public static void printSpan(int arr[], int n) {
		ArrayDeque<Integer> s = new ArrayDeque<>();
		s.add(0);
		System.out.print(1 + " ");
		for (int i = 1; i < n; i++) {
			while (s.isEmpty() == false && arr[s.peek()] <= arr[i]) {
				s.pop();
			}
			int span = s.isEmpty() ? i + 1 : i - s.peek();
			System.out.print(span + " ");
			s.push(i);
		}
	}

	public static ArrayList<Integer> nextGreater(int arr[], int n) {
		ArrayList<Integer> v = new ArrayList<>();
		ArrayDeque<Integer> s = new ArrayDeque<>();
		s.push(arr[n - 1]);
		v.add(-1);
		for (int i = n - 2; i >= 0; i--) {
			while (!s.isEmpty() && s.peek() <= arr[i]) {
				s.pop();
			}
			int ng = s.isEmpty() ? -1 : s.peek();
			v.add(ng);
			s.push(arr[i]);
		}
		Collections.reverse(v);
		return v;
	}

	public static int getMaxArea(int[] arr, int n) {
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int curr = arr[i];
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] >= arr[i]) {
					curr += arr[i];
				} else {
					break;
				}
			}
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] >= arr[i]) {
					curr += arr[i];
				} else {
					break;
				}
			}
			res = Math.max(res, curr);
		}
		return res;
	}

	public static int getMaxAreaEfficient(int[] arr, int n) {
		int res = 0;
		int[] prevSmallest = getPrevStack(arr, n);
		int[] nextSmallest = getNextStack(arr, n);
		for (int i = 0; i < arr.length; i++) {
			int curr = arr[i] * (nextSmallest[i] - prevSmallest[i] - 1);
			res = Math.max(res, curr);
		}
		return res;
	}

	public static int[] getPrevStack(int[] arr, int n) {
		int[] prevSmallest = new int[n];
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		stack.add(0);
		for (int i = 0; i < n; i++) {
			while (stack.isEmpty() == false && arr[stack.peek()] >= arr[i])
				stack.pop();
			int pse = stack.isEmpty() ? -1 : stack.peek();
			prevSmallest[i] = pse;
			stack.push(i);
		}
		return prevSmallest;
	}

	public static int[] getNextStack(int[] arr, int n) {
		int[] nextSmallest = new int[n];
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		stack.add(n - 1);
		for (int i = n - 1; i > 0; i--) {
			while (stack.isEmpty() == false && arr[stack.peek()] >= arr[i])
				stack.pop();
			int pse = stack.isEmpty() ? n : stack.peek();
			nextSmallest[i] = pse;
			stack.push(i);
		}
		return nextSmallest;
	}

	public static int getMaxAreaEfficientTwo(int arr[], int n) {
		Stack<Integer> s = new Stack<>();
		int res = 0;
		int tp;
		int curr;
		for (int i = 0; i < n; i++) {
			while (s.isEmpty() == false && arr[s.peek()] >= arr[i]) {
				tp = s.peek();
				s.pop();
				curr = arr[tp] * (s.isEmpty() ? i : i - s.peek() - 1);
				res = Math.max(res, curr);
			}
			s.add(i);
		}
		while (s.isEmpty() == false) {
			tp = s.peek();
			s.pop();
			curr = arr[tp] * (s.isEmpty() ? n : n - s.peek() - 1);
			res = Math.max(res, curr);
		}

		return res;
	}

	public static int largestHist(int C, int arr[]) {
		Stack<Integer> result = new Stack<Integer>();
		int top_val;
		int max_area = 0;
		int area = 0;
		int i = 0;
		while (i < C) {
			if (result.empty() || arr[result.peek()] <= arr[i])
				result.push(i++);
			else {
				top_val = arr[result.peek()];
				result.pop();
				area = top_val * i;
				if (!result.empty())
					area = top_val * (i - result.peek() - 1);
				max_area = Math.max(area, max_area);
			}
		}
		while (!result.empty()) {
			top_val = arr[result.peek()];
			result.pop();
			area = top_val * i;
			if (!result.empty())
				area = top_val * (i - result.peek() - 1);
			max_area = Math.max(area, max_area);
		}
		return max_area;
	}

	static int maxRectangle(int R, int C, int mat[][]) {
		int res = largestHist(C, mat[0]);
		for (int i = 1; i < R; i++) {
			for (int j = 0; j < C; j++)
				if (mat[i][j] == 1)
					mat[i][j] += mat[i - 1][j];
			res = Math.max(res, largestHist(C, mat[i]));
		}
		return res;
	}

	public static void toString(int[] arr) {
		System.out.println();
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(" " + arr[i]);
		}
		System.out.print("]");
	}

}

class TwoStack {
	int cap, top1, top2;
	int[] arr;

	TwoStack(int c) {
		cap = c;
		top1 = -1;
		top2 = c;
		arr = new int[c];
	}

	void push(int x, int stack) {
		if (top1 < top2 - 1) {
			if (stack == 1) {
				top1++;
				arr[top1] = x;
			} else {
				top2++;
				arr[top2] = x;
			}
		}
	}

	int pop(int stack) {
		if (stack == 1) {
			if (top1 >= 0) {
				int v = arr[top1];
				top1--;
				return v;
			}
		} else {
			if (top2 < cap) {
				int v = arr[top2];
				top2--;
				return v;
			}
		}
		return Integer.MAX_VALUE;
	}
}

class kStacks {
	int arr[];
	int top[];
	int next[];
	int cap, k;
	int freeTop;

	kStacks(int k1, int n) {
		k = k1;
		cap = n;
		arr = new int[cap];
		top = new int[k];
		next = new int[cap];

		for (int i = 0; i < k; i++)
			top[i] = -1;

		freeTop = 0;
		for (int i = 0; i < cap - 1; i++)
			next[i] = i + 1;
		next[cap - 1] = -1;
	}

	boolean isFull() {
		return (freeTop == -1);
	}

	boolean isEmpty(int sn) {
		return (top[sn] == -1);
	}

	void push(int x, int sn) {
		if (isFull()) {
			System.out.print("\nStack Overflow\n");
			return;
		}

		int i = freeTop;
		freeTop = next[i];
		next[i] = top[sn];
		top[sn] = i;
		arr[i] = x;
	}

	int pop(int sn) {
		if (isEmpty(sn)) {
			System.out.print("\nStack Underflow\n");
			return Integer.MAX_VALUE;
		}

		int i = top[sn];
		top[sn] = next[i];
		next[i] = freeTop;
		freeTop = i;
		return arr[i];
	}

}

class GetMinStack {
	Stack<Integer> ms;
	Stack<Integer> as;

	GetMinStack() {
		ms = new Stack<>();
		as = new Stack<>();
	}

	void push(int x) {

		if (ms.isEmpty()) {
			ms.add(x);
			as.add(x);
			return;
		}
		ms.add(x);
		if (as.peek() >= ms.peek())
			as.add(x);
	}

	void pop() {
		if (as.peek() == ms.peek())
			as.pop();
		ms.pop();
	}

	int top() {
		return ms.peek();
	}

	int getMin() {
		return as.peek();
	}
}

class getMinStackEff {

	Stack<Integer> s;
	int min;

	getMinStackEff() {
		s = new Stack<>();
	}

	void push(int x) {

		if (s.isEmpty()) {
			min = x;
			s.add(x);
		} else if (x <= min) {
			s.add(x - min);
			min = x;
		} else {
			s.add(x);
		}
	}

	int pop() {

		int t = s.peek();
		s.pop();
		if (t <= 0) {
			int res = min;
			min = min - t;
			return res;
		} else {
			return t;
		}
	}

	int peek() {
		int t = s.peek();
		return ((t <= 0) ? min : t);
	}

	int getMin() {
		return min;
	}
}

class getMinEff2 {

	Stack<Integer> s;
	int min;

	getMinEff2() {
		s = new Stack<>();
	}

	void push(int x) {

		if (s.isEmpty()) {
			min = x;
			s.add(x);
		} else if (x <= min) {
			s.add(2 * x - min);
			min = x;
		} else {
			s.add(x);
		}
	}

	int pop() {

		int t = s.peek();
		s.pop();
		if (t <= min) {
			int res = min;
			min = 2 * min - t;
			return res;
		} else {
			return t;
		}
	}

	int peek() {
		int t = s.peek();
		return ((t <= min) ? min : t);
	}

	int getMin() {
		return min;
	}
}
