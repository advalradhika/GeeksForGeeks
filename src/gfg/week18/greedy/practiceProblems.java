package gfg.week18.greedy;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {
		int[] start = { 7, 2, 2, 3 };
		int[] end = { 8, 4, 7, 10 };
		// System.out.println(activitySelection(start, end, 4));
		// System.out.println(largestNumber(50, 363));
		Job[] arr = { new Job(1, 4, 20), new Job(2, 1, 10), new Job(3, 1, 40), new Job(4, 1, 30) };
		int[] res = JobScheduling(arr, 4);
	}

	public static int activitySelection(int start[], int end[], int n) {
		Activity[] act = new Activity[start.length];
		for (int i = 0; i < start.length; i++) {
			act[i] = new Activity(start[i], end[i]);
		}
		Arrays.sort(act);
		int res = 1;
		int prevFinish = act[0].finish;
		for (int i = 1; i < act.length; i++) {
			if (act[i].start > prevFinish) {
				res++;
				prevFinish = act[i].finish;
			}
		}
		return res;
	}

	static String decodeHuffmanData(MinHeapNode root, String binaryString) {
		return "";
	}

	double fractionalKnapsack(int W, Item arr[], int n) {
		double res = 0;
		Arrays.sort(arr, new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				return i1.weight * i2.value - i2.weight * i1.value;
			}
		});
		for (int i = 0; i < arr.length; i++) {
			Item curr = arr[i];
			if (curr.weight <= W) {
				res += curr.value;
				W -= curr.weight;
			} else {
				res += curr.value * (double) W / (double) curr.weight;
				break;
			}
		}
		return res;
	}

	public static int maxMeetings(int start[], int end[], int n) {
		Activity[] act = new Activity[start.length];
		for (int i = 0; i < start.length; i++) {
			act[i] = new Activity(start[i], end[i]);
		}
		Arrays.sort(act);
		int res = 1;
		int prevFinish = act[0].finish;
		for (int i = 1; i < act.length; i++) {
			if (act[i].start > prevFinish) {
				res++;
				prevFinish = act[i].finish;
			}
		}
		return res;
	}

	static String largestNumber(int n, int sum) {
		String pass = "";
		if (sum > 9 * n)
			return "-1";
		for (int i = 0; i < n; i++) {
			if (sum > 9) {
				pass += "9";
				sum -= 9;
			} else {
				pass += Integer.toString(sum);
				sum = 0;
			}
		}
		return pass;
	}

	static int[] JobScheduling(Job arr[], int n) {
		Arrays.sort(arr, new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				return j2.profit - j1.profit;
			}
		});
		int res = 0, count = 0;
		int[] sol = new int[100];
		Arrays.fill(sol, 0);
		for (int i = 0; i < n; i++) {
			Job curr = arr[i];
			for (int j = curr.deadline - 1; j >= 0; j--) {
				if (sol[j] == 0) {
					sol[j] = curr.profit;
					count++;
					break;
				}
			}
		}
		for (int i = 0; i < 100; i++) {
			res += sol[i];
		}
		int[] re = new int[2];
		re[0] = count;
		re[1] = res;
		return re;
	}
}

class MinHeapNode implements Comparator<MinHeapNode> {
	char data;
	int freq;
	MinHeapNode left, right;

	MinHeapNode(char data, int freq) {
		this.data = data;
		this.freq = freq;
	}

	@Override
	public int compare(MinHeapNode x, MinHeapNode y) {
		return x.freq - y.freq;
	}
}

class Job {
	int id, profit, deadline;

	Job(int x, int y, int z) {
		this.id = x;
		this.deadline = y;
		this.profit = z;
	}
}