package algorithms;

import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.println("use next to get");
		if(scan.hasNext()) {
			String str1 = scan.next();
			System.out.println("what input is " + str1);
		}
        scan.close();
	}

}
