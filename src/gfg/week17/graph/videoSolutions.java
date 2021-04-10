package gfg.week17.graph;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {}

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

	static void shortestPathUnweightedUndirected(ArrayList<ArrayList<Integer>> adj, int s) {
		int v = adj.size();
		int[] dis = new int[v];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[s] = 0;
		boolean[] visited = new boolean[v];
		shortestPathUnweightedUndirectedUtil(adj, s, visited, dis);
	}

	static void shortestPathUnweightedUndirectedUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited,
			int[] dis) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visited[s] = true;
		while (!q.isEmpty()) {
			int x = q.poll();
			for (int v : adj.get(x)) {
				if (!visited[v]) {
					visited[v] = true;
					dis[v] = dis[x] + 1;
					q.add(v);
				}
			}
		}
	}

	static boolean checkCycleUndirected(ArrayList<ArrayList<Integer>> adj) {
		int v = adj.size();
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i])
				if (checkCycleUndirectedUtil(adj, i, visited, -1))
					return true;
		}
		return false;
	}

	static boolean checkCycleUndirectedUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, int parent) {
		visited[s] = true;
		for (int x : adj.get(s)) {
			if (!visited[x]) {
				if (checkCycleUndirectedUtil(adj, x, visited, s))
					return true;
			} else if (parent != x)
				return true;
		}
		return false;
	}

	static boolean checkCycleDirected(ArrayList<ArrayList<Integer>> adj) {
		int v = adj.size();
		boolean[] visited = new boolean[v];
		boolean[] recStack = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i])
				if (checkCycleDirectedUtil(adj, i, visited, recStack))
					return true;
		}
		return false;
	}

	static boolean checkCycleDirectedUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited,
			boolean[] recStack) {
		visited[s] = true;
		recStack[s] = true;
		for (int x : adj.get(s)) {
			if (!visited[x] && checkCycleDirectedUtil(adj, x, visited, recStack))
				return true;
			else if (recStack[x])
				return true;
		}
		recStack[s] = false;
		return false;
	}

	static void kahnsTopologicalSortBFS(ArrayList<ArrayList<Integer>> adj) {
		int v = adj.size();
		int[] indegree = new int[v];
		for (int i = 0; i < v; i++) {
			for (int u : adj.get(i)) {
				indegree[u]++;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < v; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr + " ");
			for (int ad : adj.get(curr)) {
				indegree[ad]--;
				if (indegree[ad] == 0) {
					q.add(ad);
				}
			}
		}
	}

	static boolean detectCycleKahns(ArrayList<ArrayList<Integer>> adj) {
		int count = 0;
		int v = adj.size();
		int[] indegree = new int[v];
		for (int i = 0; i < v; i++) {
			for (int u : adj.get(i)) {
				indegree[u]++;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < v; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int curr = q.poll();
			count++;
			for (int ad : adj.get(curr)) {
				indegree[ad]--;
				if (indegree[ad] == 0) {
					q.add(ad);
				}
			}
		}
		return (count == v);
	}

	static void topologicalDFS(ArrayList<ArrayList<Integer>> adj) {
		Stack<Integer> s = new Stack<Integer>();
		int v = adj.size();
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				topologicalDFSUtil(adj, i, visited, s);
			}
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop() + " ");
		}
	}

	static void topologicalDFSUtil(ArrayList<ArrayList<Integer>> adj, int i, boolean[] visited, Stack<Integer> st) {
		visited[i] = true;
		for (int v : adj.get(i)) {
			if (!visited[v]) {
				topologicalDFSUtil(adj, v, visited, st);
			}
		}
		st.push(i);
	}
	
	

}
