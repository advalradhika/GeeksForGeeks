package gfg.week18.greedy;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {
		int[] start = { 7, 2, 2, 3 };
		int[] end = { 8, 4, 7, 10 };
		System.out.println(activitySelection(start, end, 4));
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
