package algorithms;

import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SGwithScanner {
	
	private RedBlackBST<String, Integer> st;           // String  ->  index
	private String[] keys;                             // index  ->  String
	private Graph G;                                   // the Graph
	
	public SGwithScanner(String sp) {
		st = new RedBlackBST<String, Integer>();
		Scanner scan = new Scanner(System.in);                       // first pass
		System.out.println("enter things to the graph");
		while (scan.hasNextLine()) {                     // builds the index
			String[] a = scan.nextLine().split(sp);
			for (int i = 0; i < a.length; i++) 
				if (!st.contains(a[i]))
					st.put(a[i], st.size());
			}
		scan.close();
		
		keys = new String[st.size()];                  // inverted index
		for (String name: st.keys())                   // to get string keys in an array
			keys[st.get(name)] = name;
		
		G = new Graph(st.size());                      // second pass		
		Scanner scan2 = new Scanner(System.in);                         // builds the graph
		System.out.println("what's you want");
		while (scan2.hasNextLine()) {
			String[] a = scan2.nextLine().split(sp);
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++)
				G.addEdge(v, st.get(a[i]));
		}
		scan2.close();
		
	}
	
	public boolean contains(String s) { return st.contains(s);}
	public int index(String s)        { return st.get(s);}
	public String name(int v)         { return keys[v];}
	public Graph G()                  { return G;}

	public static void main(String[] args) {
//		String filename = args[0];
//		String delim = args[1];
		SGwithScanner sg = new SGwithScanner(" ");
		Graph G = sg.G;
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String source = scan.nextLine();
			for (int w: G.adj(sg.index(source)))
				StdOut.println("   " + sg.name(w));
		}

	}

}
