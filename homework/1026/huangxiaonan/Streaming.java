package hadoop;

import java.util.Scanner;

public class Streaming {
	public static void main(String[] args) {
		Scanner sc;
		sc = new Scanner(System.in);
		while(sc.hasNext()){
			String line = sc.nextLine();
			System.out.println(line);
		}
	}
}
