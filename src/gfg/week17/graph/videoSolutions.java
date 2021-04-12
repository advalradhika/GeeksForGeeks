package gfg.week17.graph;

import java.util.*;

public class videoSolutions {

	public static void main(String[] args) {
		ArrayList<ArrayList<AdjListNode>> ad = new ArrayList<ArrayList<AdjListNode>>();
		for (int i = 0; i < 6; i++) {
			ad.add(new ArrayList<AdjListNode>());
		}
		ad.get(0).add(new AdjListNode(1, 5));
		ad.get(0).add(new AdjListNode(2, 10));
		ad.get(1).add(new AdjListNode(0, 5));
		ad.get(1).add(new AdjListNode(2, 3));
		ad.get(1).add(new AdjListNode(3, 20));
		// ad.get(1).add(new AdjListNode(2, 3));
		ad.get(2).add(new AdjListNode(0, 10));
		ad.get(2).add(new AdjListNode(1, 3));
		ad.get(2).add(new AdjListNode(3, 2));
		ad.get(3).add(new AdjListNode(1, 20));
		ad.get(3).add(new AdjListNode(2, 2));
		// ad.get(4).add(new AdjListNode(3, 9));
		// ad.get(4).add(new AdjListNode(1, 5));
		// ad.get(4).add(new AdjListNode(2, 7));
		// dijkstraMinDistance(ad, 0);
		ArrayList<ArrayList<Integer>> adList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 6; i++) {
			adList.add(new ArrayList<Integer>());
		}
		adList.get(0).add(1);
		adList.get(1).add(2);
		adList.get(2).add(3);
		adList.get(3).add(4);
		adList.get(3).add(0);
		adList.get(4).add(5);
		adList.get(5).add(4);
		kosarajuFindStronglyConnectedComponents(adList);
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

	static void shortestDistanceInDirectedAcyclic(ArrayList<ArrayList<AdjListNode>> adj, int s) {
		int v = adj.size();
		int[] dis = new int[v];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[s] = 0;
		int[] indegree = new int[v];
		for (int i = 0; i < v; i++) {
			for (AdjListNode u : adj.get(i)) {
				indegree[u.getV()]++;
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
			for (AdjListNode ad : adj.get(curr)) {
				if (dis[curr] != Integer.MAX_VALUE && dis[ad.getV()] > dis[curr] + ad.getWeight()) {
					dis[ad.getV()] = dis[curr] + ad.getWeight();
				}
				indegree[ad.getV()]--;
				if (indegree[ad.getV()] == 0) {
					q.add(ad.getV());
				}
			}
		}
		for (int i = 0; i < dis.length; i++) {
			System.out.println(dis[i]);
		}
	}

	static int primsMinSpanningTree(ArrayList<ArrayList<AdjListNode>> adj) {
		int res = 0;
		int v = adj.size();
		int[] key = new int[v];
		Arrays.fill(key, Integer.MAX_VALUE);
		key[0] = 0;
		boolean[] incVertices = new boolean[v];
		for (int i = 0; i < v - 1; i++) {
			int u = minKey(key, incVertices);
			incVertices[u] = true;
			for (AdjListNode adNode : adj.get(u)) {
				if (!incVertices[adNode.getV()] && adNode.getWeight() < key[adNode.getV()]) {
					key[adNode.getV()] = adNode.getWeight();
				}
			}
		}
		for (int i = 0; i < v; i++) {
			System.out.println(" i : " + i + " key[i] : " + key[i]);
			res += key[i];
		}
		return res;
	}

	static int minKey(int[] key, boolean[] incVertices) {
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i < key.length; i++) {
			if (!incVertices[i] && key[i] < min) {
				min = key[i];
				minIdx = i;
			}
		}
		return minIdx;
	}

	static void dijkstraMinDistance(ArrayList<ArrayList<AdjListNode>> adj, int s) {
		int v = adj.size();
		int[] dis = new int[v];
		boolean[] fin = new boolean[v];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[s] = 0;
		for (int i = 0; i < v - 1; i++) {
			int idx = getMinDisIdx(dis, fin);
			fin[idx] = true;
			for (AdjListNode ad : adj.get(idx)) {
				if (dis[ad.getV()] > dis[idx] + ad.getWeight()) {
					dis[ad.getV()] = dis[idx] + ad.getWeight();
				}
			}
		}
		for (int i = 0; i < dis.length; i++) {
			System.out.println("Vertex No : " + i + " Distance : " + dis[i]);
		}
	}

	static int getMinDisIdx(int[] dis, boolean[] fin) {
		int idx = -1;
		for (int i = 0; i < dis.length; i++) {
			if (!fin[i] && (idx == -1 || dis[i] < dis[idx]))
				idx = i;
		}
		return idx;
	}

	static void kosarajuFindStronglyConnectedComponents(ArrayList<ArrayList<Integer>> adj) {
		boolean[] visited = new boolean[adj.size()];
		Stack<Integer> st = new Stack<Integer>();
		getOrder(adj, st, visited);
		adj = reverseEdges(adj);
		printComponents(adj, st, visited);
	}

	static void getOrder(ArrayList<ArrayList<Integer>> adj, Stack<Integer> st, boolean[] visited) {
		int v = adj.size();
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				getOrderUtil(adj, i, st, visited);
			}
		}
	}

	static void getOrderUtil(ArrayList<ArrayList<Integer>> adj, int v, Stack<Integer> st, boolean[] visited) {
		visited[v] = true;
		for (Integer ad : adj.get(v)) {
			if (!visited[ad])
				getOrderUtil(adj, ad, st, visited);
		}
		st.add(v);
	}

	static ArrayList<ArrayList<Integer>> reverseEdges(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < adj.size(); i++) {
			res.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < adj.size(); i++) {
			for (Integer ad : adj.get(i)) {
				res.get(ad).add(i);
			}
		}
		return res;
	}

	static void printComponents(ArrayList<ArrayList<Integer>> adj, Stack<Integer> st, boolean[] visited) {
		System.out.println("stack:" + st);
		System.out.println(adj);
		Arrays.fill(visited, false);
		while (!st.isEmpty()) {
			int curr = st.pop();
			if (!visited[curr]) {
				DFSUtil(adj, curr, visited);
				System.out.println();
			}
		}
	}

}

class AdjListNode {
	private int v;
	private int weight;

	AdjListNode(int v, int w) {
		this.v = v;
		this.weight = w;
	}

	int getV() {
		return v;
	}

	int getWeight() {
		return weight;
	}
}
