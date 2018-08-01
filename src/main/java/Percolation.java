
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;



public class Percolation {
	 private int n;
	 private WeightedQuickUnionUF uf;
	 private WeightedQuickUnionUF uf_backwash;
	 private boolean[] isopen;
	 
	 public Percolation(int n)  {                // create n-by-n grid, with all sites blocked
		 if (n <= 0) throw new IllegalArgumentException("n is<=0"); 
		 this.n = n;
		 uf = new WeightedQuickUnionUF((n+1)*(n)+n+1);  
         uf_backwash = new WeightedQuickUnionUF(n*n+n+1); 
         isopen=new boolean[(n+1)*(n)+n+1];  
         for(int i=1;i<=n;i++){  
             uf.union(0*n+1, 0*n+i);                  //the line0 is all connected in uf
             uf_backwash.union(0*n+1, 0*n+i);         // line0 is all connected in uf_backwash
             isopen[0*n+i]=true;  
             uf.union((n+1)*n+1, (n+1)*n+i);          // line(n+1) is all connected in uf 
             isopen[(n+1)*n+i] = true;    
         }  
	 } 
	 
	 public void open(int row, int col)  {  // open site (row, col) if it is not open already
		 if (row<1||row>n) throw new IndexOutOfBoundsException("row index i out of bounds");  
         if (col<1||col>n)  throw new IndexOutOfBoundsException("column index j out of bounds");  

         if (isopen[row*n+col]) return;  
         isopen[row*n+col]=true;  //count_n++;  
         
         if (isopen[(row-1)*n+col]){       // if former line, same col is open, then, connect them
        	     uf.union(row*n+col, (row-1)*n+col);    
             uf_backwash.union(row*n+col, (row-1)*n+col);    
         }    
         if (isopen[(row+1)*n+col]){      // if later line, same col is open, then, connect them
             uf.union(row*n+col, (row+1)*n+col);    
             if (row!=n){                 // backwash only initialized with n lines
                 uf_backwash.union(row*n+col, (row+1)*n+col);    
             }    
         }    
         if (col!=1 && isopen[row*n+col-1]){    // ignore the 0 col and n+1 col
             uf.union(row*n+col, row*n+col-1);    
             uf_backwash.union(row*n+col, row*n+col-1);    
         }    
         if (col!=n && isopen[row*n+col+1]){    
             uf.union(row*n+col, row*n+col+1);    
             uf_backwash.union(row*n+col, row*n+col+1);    
         }
	 }
	   
	 public boolean isOpen(int row, int col) { // is site (row, col) open?
		 if (row<1||row>n) throw new IndexOutOfBoundsException("row index i out of bounds");  
         if(col<1||col>n)  throw new IndexOutOfBoundsException("column index j out of bounds");                 
         return isopen[row*n+col]; 
         // isopen has the same function with isOpen, but one is array, the other is matrix
	 }
	 
	 public boolean isFull(int row, int col) { // is site (row, col) full?
		 if (row<1||row>n) throw new IndexOutOfBoundsException("row index i out of bounds");  
         if(col<1||col>n)  throw new IndexOutOfBoundsException("column index j out of bounds");  
         return uf_backwash.connected(row*n+col, 0*n+1) && isopen[row*n+col]; 
         // isFull is the site that connect with any site from the top line1
	 }   
	 
	 public int numberOfOpenSites()  {     // number of open sites
		 int a = 0;
		 for (int i = n+1; i <= (n+1)*n; i++) {
			 if (isopen[i]) a++;
		 }
		 return a;
	 }
	 
	 public boolean percolates()  {            // does the system percolate?
		 return uf.connected(0*n+1, (n+1)*n+1);
	 }

	 public static void main(String[] args) {  // test client (optional)
		 int N = StdIn.readInt();  
         Percolation pe=new Percolation(N);  
         pe.open(1, 1);  
         pe.open(2, 1);  
         System.out.println(pe.percolates()); 
	 }

}
