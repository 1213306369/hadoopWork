package hs;

import java.util.Scanner;

public class Streaming {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String line = sc.nextLine();
			System.out.println(line);
		}
	}
}
