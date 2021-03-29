package gfg.week8.hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class Hash {
	int BUCKET;
	ArrayList<LinkedList<Integer>> table;

	public Hash(int b) {
		BUCKET = b;
		table = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < b; i++) {
			table.add(new LinkedList<Integer>());
		}
	}

	void insert(Integer key) {
		int i = key % BUCKET;
		table.get(i).add(key);
	}

	boolean search(Integer key) {
		int i = key % BUCKET;
		return table.get(i).contains(key);
	}

	void remove(Integer key) {
		int i = key % BUCKET;
		table.get(i).remove((Integer) key); ////// Type casting makes sure remove function takes object, not index.
	}

}
