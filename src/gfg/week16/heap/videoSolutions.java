package gfg.week16.heap;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		int[] arr = { 6, 4, 9, 2, 8, 1 };
		int[] a = { 9, 8, 7, 18, 19, 17 };
		kSortedArray(a, 2);
		MaxHeapUtil h = new MaxHeapUtil(arr.length);
		h.heapSort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}

	static void kSortedArray(int[] arr, int k) {
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		for (int i = 0; i <= k; i++) {
			p.add(arr[i]);
		}
	}

}

class MinHeapUtil {
	int[] arr;
	int size;
	int cap;

	MinHeapUtil(int c) {
		arr = new int[c];
		cap = c;
		size = 0;
	}

	int left(int i) {
		return 2 * i + 1;
	}

	int right(int i) {
		return 2 * i + 2;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	void minHeapify(int i) {
		int smallest = i;
		int left = left(i);
		int right = right(i);
		if (left < size && arr[left] < arr[i]) {
			smallest = left;
		}
		if (right < size && arr[right] < arr[i]) {
			smallest = right;
		}
		if (smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			minHeapify(smallest);
		}
	}

	void insert(int x) {
		if (size == cap) {
			return;
		}
		arr[size] = x;
		size++;
		int i = size - 1;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	int extractMin() {
		if (size == 0) {
			return Integer.MAX_VALUE;
		} else if (size == 1) {
			size--;
			return arr[0];
		} else {
			int min = arr[0];
			arr[0] = arr[size - 1];
			size--;
			minHeapify(0);
			return min;
		}
	}

	void decreaseKey(int i, int newKey) {
		arr[i] = newKey;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	void delete(int i) {
		decreaseKey(i, Integer.MIN_VALUE);
		extractMin();
	}

	void buildMinHeap(int[] arr) {
		for (int i = (size - 2) / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

}

class MaxHeapUtil {
	int[] arr;
	int size;
	int cap;

	MaxHeapUtil(int c) {
		arr = new int[c];
		cap = c;
		size = 0;
	}

	int left(int i) {
		return 2 * i + 1;
	}

	int right(int i) {
		return 2 * i + 2;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	void maxHeapify(int i, int[] arr, int n) {
		int largest = i;
		int left = left(i);
		int right = right(i);
		if (left < size && arr[left] > arr[i]) {
			largest = left;
		}
		if (right < size && arr[right] > arr[i]) {
			largest = right;
		}
		if (largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			maxHeapify(largest, arr, n);
		}
	}

	void insert(int x) {
		if (size == cap) {
			return;
		}
		arr[size] = x;
		size++;
		int i = size - 1;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	int extractMax() {
		if (size == 0) {
			return Integer.MAX_VALUE;
		} else if (size == 1) {
			size--;
			return arr[0];
		} else {
			int min = arr[0];
			arr[0] = arr[size - 1];
			size--;
			maxHeapify(0, arr, arr.length);
			return min;
		}
	}

	void decreaseKey(int i, int newKey) {
		arr[i] = newKey;
		while (i != 0 && arr[parent(i)] > arr[i]) {
			int temp = arr[i];
			arr[i] = arr[parent(i)];
			arr[parent(i)] = temp;
			i = parent(i);
		}
	}

	void delete(int i) {
		decreaseKey(i, Integer.MIN_VALUE);
		extractMax();
	}

	void buildMaxHeap(int[] arr) {
		for (int i = (arr.length - 2) / 2; i >= 0; i--) {
			maxHeapify(i, arr, arr.length);
		}
	}

	void heapSort(int[] arr) {
		buildMaxHeap(arr);
		for (int i = arr.length - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			maxHeapify(0, arr, i);
		}
	}

}