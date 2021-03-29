package gfg.week11.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class practiceProblems {

	public static void main(String[] args) {
		// String str = "{([])}";
		// System.out.println(ispar(str));
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);

		/*
		 * System.out.println(s); //deleteMid(s, 5); System.out.println(s);
		 */
		String str = "(a*b)-(c*d)+(e/f)";
		// str = infixToPostfix(str);
		// System.out.println(str);
		// System.out.println(matchBrac('[', ']'));
	}

	public static void insert(Stack<Integer> st, int x) {
		st.add(x);
	}

	// Function to pop element from stack
	public static void remove(Stack<Integer> st) {
		int x = st.remove(st.size() - 1);
	}

	// Function to return top of stack
	public static void headOf_Stack(Stack<Integer> st) {
		int x = st.get(st.size() - 1);
		System.out.println(x + " ");
	}

	// Function to find the element in stack
	public static boolean find(Stack<Integer> st, int val) {
		boolean exists = false;
		exists = st.contains(val);
		if (exists == true) {
			return true;
		} else {
			return false;
		}
	}

	public static String removeConsecutiveDuplicates(String str) {
		ArrayDeque<Character> s = new ArrayDeque<Character>();
		for (int i = 0; i < str.length(); i++) {
			if (s.isEmpty() || s.peek() != str.charAt(i))
				s.push(str.charAt(i));
		}
		String res = new String("");
		while (!s.isEmpty()) {
			res = s.peek() + res;
			s.pop();
		}
		return res;
	}

	public static String removePair(String str) {
		ArrayDeque<Character> s = new ArrayDeque<Character>();
		for (int i = 0; i < str.length(); i++) {
			if (s.isEmpty() || s.peek() != str.charAt(i)) {
				s.push(str.charAt(i));
			} else {
				s.pop();
			}
		}
		String res = new String("");
		while (!s.isEmpty()) {
			res = s.peek() + res;
			s.pop();
		}
		return res;
	}

	static boolean ispar(String str) {
		ArrayDeque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < str.length(); i++) {
			System.out.println(" i : " + i);
			if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
				stack.push(str.charAt(i));
				System.out.println("stack : " + stack);
			} else {
				if (stack.isEmpty()) {
					System.out.println("1st");
					System.out.println("stack : " + stack);
					return false;
				} else if (!matchBrac(stack.peek(), str.charAt(i))) {
					System.out.println("1st");
					System.out.println("stack : " + stack);
					return false;
				} else {
					stack.pop();
					System.out.println("stack : " + stack);
				}
			}
		}
		return stack.isEmpty();
	}

	public static boolean matchBrac(char a, char b) {
		return ((a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}'));
	}

	public static Stack<Integer> _push(int arr[], int n) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (s.peek() > arr[i])
				s.push(arr[i]);
			else
				s.push(s.peek());
		}
		return s;
	}

	static void _getMinAtPop(Stack<Integer> s) {
		while (!s.isEmpty())
			System.out.print(s.pop() + " ");
	}

	public static void deleteMid(Stack<Integer> s, int sizeOfStack) {
		int mid = (int) Math.ceil(sizeOfStack / 2.0) - 1;
		Stack<Integer> v = new Stack<Integer>();
		while (mid-- > 0) {
			v.push(s.pop());
		}
		s.pop();
		while (!v.isEmpty()) {
			s.push(v.pop());
		}
		while (!s.isEmpty()) {
			v.push(s.pop());
		}
		while (!v.isEmpty()) {
			s.push(v.pop());
		}
	}

	public static String infixToPostfix(String exp) {
		HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
		chars.put('-', 0);
		chars.put('+', 0);
		chars.put('*', 1);
		chars.put('/', 1);
		chars.put('^', 2);
		String output = "";
		char x;
		Stack<Character> s = new Stack<Character>();
		for (int i = 0; i < exp.length(); i++) {
			x = exp.charAt(i);
			if (x != '/' && x != '*' && x != '^' && x != '+' && x != '-' && x != '(' && x != ')') {
				output += x;
			} else if (x == '(') {
				s.push(x);
			} else if (x == ')') {
				while (!s.isEmpty() && s.peek() != '(') {
					output += s.pop();
				}
				if (!s.isEmpty() && s.peek() == '(')
					s.pop();
			} else {
				while (!s.isEmpty() && s.peek() != '(' && s.peek() != ')' && chars.get(x) <= chars.get(s.peek())) {
					output += s.pop();
				}
				s.push(x);
			}
		}
		while (!s.isEmpty()) {
			output += s.pop();
		}
		return output;
	}

	public static int evaluatePostFix(String exp) {
		Stack<Integer> s = new Stack<Integer>();
		int op1, op2;
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
				s.add(Integer.parseInt(exp.substring(i, i + 1)));
			} else {
				op1 = s.pop();
				op2 = s.pop();
				if (exp.charAt(i) == '-') {
					s.push(op2 - op1);
				} else if (exp.charAt(i) == '+') {
					s.push(op2 + op1);
				} else if (exp.charAt(i) == '*') {
					s.push(op2 * op1);
				} else {
					s.push(op2 / op1);
				}
			}
		}
		return s.pop();
	}

	public static int[] calculateSpan(int price[], int n) {
		int[] res = new int[n];
		ArrayDeque<Integer> s = new ArrayDeque<>();
		s.add(0);
		// System.out.print(1 + " ");
		res[0] = 1;
		for (int i = 1; i < n; i++) {
			while (s.isEmpty() == false && price[s.peek()] <= price[i]) {
				s.pop();
			}
			int span = s.isEmpty() ? i + 1 : i - s.peek();
			// System.out.print(span + " ");
			res[i] = span;
			s.push(i);
		}
		return res;
	}

	public static long[] nextLargerElement(long[] arr, int n) {
		long[] res = new long[n];
		ArrayList<Long> v = new ArrayList<>();
		ArrayDeque<Long> s = new ArrayDeque<>();
		s.push(arr[n - 1]);
		v.add(-1L);
		for (int i = n - 2; i >= 0; i--) {
			while (s.isEmpty() == false && s.peek() <= arr[i])
				s.pop();
			long ng = s.isEmpty() ? -1L : s.peek();
			v.add(ng);
			s.push(arr[i]);
		}
		Collections.reverse(v);
		for (int i = 0; i < v.size(); i++) {
			res[i] = v.get(i);
		}
		return res;

	}

	public static long getMaxArea(long arr[], long n) {
		long res = 0;
		for (long i = 0; i < n; i++) {
			long curr = arr[(int) i];
			for (long j = i - 1; j >= 0; j--) {
				if (arr[(int) j] >= arr[(int) i]) {
					curr += arr[(int) i];
				} else {
					break;
				}
			}
			for (long j = i + 1; j < n; j++) {
				if (arr[(int) j] >= arr[(int) i]) {
					curr += arr[(int) i];
				} else {
					break;
				}
			}
			res = Math.max(res, curr);
		}
		return res;
	}

	int celebrity(int M[][], int n) {
		Stack<Integer> stack = new Stack<Integer>();
		int first, second;
		for (int i = 0; i < n; i++) {
			stack.push(i);
		}
		while (stack.size() > 1) {
			first = stack.pop();
			second = stack.pop();
			if (M[first][second] == 1) {
				stack.push(second);
			} else {
				stack.push(first);
			}
		}
		int last = stack.pop();
		for (int i = 0; i < n; i++) {
			if (M[last][i] == 1)
				return -1;
		}
		for (int i = 0; i < n; i++) {
			if (M[i][last] == 0 && i != last)
				return -1;
		}
		return last;
	}

	static int[] printMaxOfMin(int[] arr, int N) {
		return null;
	}

}
