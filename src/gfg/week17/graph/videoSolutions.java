package gfg.week17.graph;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		// TODO Auto-generated metArrayList<E>
	}

	static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}

	static void printGraph(ArrayList<ArrayList<Integer>> adj) {
		for (int i = 0; i < adj.size(); i++) {
			for (int j = 0; j < adj.get(i).size(); j++) {
				System.out.print(adj.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	static void BFS(ArrayList<ArrayList<Integer>> adj, int v, int s) {
		boolean[] visited = new boolean[v];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int u = q.poll();
			System.out.print(u + " ");
			for (int x : adj.get(u)) {
				if (!visited[x]) {
					visited[x] = true;
					q.add(x);
				}
			}
		}
	}

	static void BFSDisconnected(ArrayList<ArrayList<Integer>> adj, int v, int s, boolean[] visited) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int u = q.poll();
			System.out.print(u + " ");
			for (int x : adj.get(u)) {
				if (!visited[x]) {
					visited[x] = true;
					q.add(x);
				}
			}
		}
	}

	static int BFSUtil(ArrayList<ArrayList<Integer>> adj) {
		int v = adj.size();
		int res = 0;
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			BFSDisconnected(adj, v, i, visited);
			res++;
		}
		return res;
	}

	static void DFS(ArrayList<ArrayList<Integer>> adj, int v, int s) {
		boolean[] visited = new boolean[v];
		DFSUtil(adj, s, visited);
	}

	static void DFSUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited) {
		visited[s] = true;
		System.out.print(s + " ");
		for (int u : adj.get(s))
			if (!visited[u])
				DFSUtil(adj, u, visited);
	}

	static int DFS(ArrayList<ArrayList<Integer>> adj, int v) {
		int count = 0;
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				count++;
				DFSUtil(adj, i, visited);
			}
		}
		return count;
	}

}
