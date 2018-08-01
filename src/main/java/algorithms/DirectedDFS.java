package algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {
	
	private boolean[] marked;
	
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	public DirectedDFS(Digraph G, Iterable<Integer> source) {
		marked = new boolean[G.V()];
		for (int s : source) {
			if (!marked[s])
				dfs(G, s);
		}
	}
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w])
				dfs(G, w);
		}
	}
	
	public boolean marked(int v) { return marked[v];}

	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));
		Bag<Integer> source = new Bag<Integer>();
		for (int i = 1; i < args.length; i++)
			source.add(Integer.parseInt(args[i]));
		DirectedDFS reachable = new DirectedDFS(G, source);
		for (int v = 0; v < G.V(); v++)
			if (reachable.marked(v))
				StdOut.print(v + " ");
		StdOut.println();
	}

}
