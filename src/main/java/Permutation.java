import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> rq = new RandomizedQueue<String>(); 	
		int c = 0;
      	int k = StdIn.readInt();
	    while (!StdIn.isEmpty()) {
	        String s = StdIn.readString(); 
	        rq.enqueue(s);
	    }  
	    for (String i : rq) {
	    	    if (c < k) {
	    	      	StdOut.println(i); 
	    	      	c++;
	    	    }
	    }
	}
}
