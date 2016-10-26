package MapReduceJoin;

import java.util.Scanner;

public class TestStream1 {
	public static void main(String[] args) {
		Scanner s ;
		s = new Scanner(System.in);
		
		while (s.hasNext() ){
			String line = s.nextLine();
			String [] a ;
			a = line.split(" ");
			if (a.length > 6) {
				System.out.printf("%s\t%s\n",a[0],a[6]);
			}
		}
	}
}
