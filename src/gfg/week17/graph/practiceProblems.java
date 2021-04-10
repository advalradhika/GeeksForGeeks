package gfg.week17.graph;

import java.util.*;

public class practiceProblems {

	public static void main(String[] args) {
		char[][] grid = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 0 } };
		int res = numIslands(grid);
		// System.out.println(res);

		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 4; i++) {
			arr.add(new ArrayList<Integer>());
		}
		arr.get(0).add(1);
		arr.get(1).add(0);
		arr.get(1).add(2);
		arr.get(1).add(3);
		arr.get(2).add(1);
		arr.get(3).add(1);
		System.out.println(isCycle(4, arr));

	}

	public ArrayList<ArrayList<Integer>> printGraph(int V, ArrayList<ArrayList<Integer>> adj) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(i);
			for (int j = 0; j < adj.get(i).size(); j++) {
				temp.add(adj.get(i).get(j));
			}
			ans.add(temp);
		}
		return ans;
	}

	public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		boolean visited[] = new boolean[V];
		bfsOfGraphUtil(adj, 0, visited, res);
		return res;
	}

	void bfsOfGraphUtil(ArrayList<ArrayList<Integer>> adj, int i, boolean[] visited, ArrayList<Integer> res) {
		visited[i] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(i);
		while (!q.isEmpty()) {
			int x = q.poll();
			res.add(x);
			for (int y : adj.get(x)) {
				if (!visited[y]) {
					q.add(y);
					visited[y] = true;
				}
			}
		}
	}

	public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		boolean visited[] = new boolean[V];
		dfsOfGraphUtil(adj, 0, visited, res);
		return res;
	}

	void dfsOfGraphUtil(ArrayList<ArrayList<Integer>> adj, int i, boolean[] visited, ArrayList<Integer> res) {
		visited[i] = true;
		res.add(i);
		for (int x : adj.get(i)) {
			if (!visited[x]) {
				visited[x] = true;
				dfsOfGraphUtil(adj, x, visited, res);
			}
		}
	}

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static int numIslands(char[][] grid) {
		int x = grid.length;
		int y = grid[0].length;
		int res = 0;
		boolean[][] visited = new boolean[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					res++;
					numIslandsUtil(grid, i, j, visited);
				}
			}
		}
		return res;
	}

	static void numIslandsUtil(char[][] grid, int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		for (int i = 0; i < 8; i++) {
			if (isValid(x + dx[i], y + dy[i], visited, grid)) {
				numIslandsUtil(grid, x + dx[i], y + dy[i], visited);
			}
		}
	}

	static boolean isValid(int x, int y, boolean[][] visited, char[][] grid) {
		int r = visited.length;
		int c = visited[0].length;
		if (x < 0 || y < 0 || x > r - 1 || y > c - 1)
			return false;
		if (visited[x][y] || grid[x][y] == '0')
			return false;
		return true;
	}

	public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i] && isCycleUtil(adj, visited, i, -1)) {
				return true;
			}
		}
		return false;
	}

	static boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int s, int parent) {
		visited[s] = true;
		for (int u : adj.get(s)) {
			if (u == parent) {
				continue;
			}
			if (visited[u] || isCycleUtil(adj, visited, u, s)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				if (isCyclicUtil(adj, i, visited, recStack))
					return true;
			}
		}
		return false;
	}

	boolean isCyclicUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, boolean[] recStack) {
		visited[s] = true;
		recStack[s] = true;
		for (int u : adj.get(s)) {
			if (!visited[u] && isCyclicUtil(adj, u, visited, recStack))
				return true;
			else if (recStack[u])
				return true;
		}
		recStack[s] = false;
		return false;
	}

}
