package gfg.week18.greedy;

import java.util.Arrays;

public class videoSolutions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}//// Works only if coin value are standard Indian/US currency

	static int pickMinimumCoins(int[] arr, int amount) {
		int res = 0;
		Arrays.sort(arr);
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] <= amount) {
				int c = amount / arr[i];
				res += c;
				amount -= c * arr[i];
			}
			if (amount == 0)
				break;
		}
		return res;
	}

	///// O(nlogn)
	static int activitySelection(Activity[] arr) {
		int res = 1;
		Arrays.sort(arr);
		int prevFinish = arr[0].finish, currstart;
		for (int i = 1; i < arr.length; i++) {
			currstart = arr[i].start;
			if (currstart >= prevFinish) {
				res++;
				prevFinish = arr[i].finish;
			}
		}
		return res;
	}

	///// O(nlogn)
	static double fractionalKnapsack(Item[] items, int cap) {
		Arrays.sort(items);
		double res = 0.0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].weight <= cap) {
				res += items[i].value;
				cap -= items[i].weight;
			} else {
				res += items[i].value * (double) cap / (double) items[i].weight;
				break;
			}
		}
		return res;
	}

}

class Activity implements Comparable<Activity> {
	int start;
	int finish;

	Activity(int start, int finish) {
		this.start = start;
		this.finish = finish;
	}

	@Override
	public int compareTo(Activity act) {
		return this.finish - act.finish;
	}

	public void printActivity(Activity[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].start + " " + arr[i].finish + "  ***  ");
		}
	}
}

class Item implements Comparable<Item> {
	int weight;
	int value;

	Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	@Override
	public int compareTo(Item i) {
		return this.weight * i.value - i.weight * this.value;
	}
}