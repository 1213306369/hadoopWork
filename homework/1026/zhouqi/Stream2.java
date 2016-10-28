package com.qst;

import java.util.Scanner;

public class Stream2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String line=sc.nextLine();
			String items[]=line.split(" ");
			System.out.println(items[0]+"\t"+items[6]);
		}
		sc.close();
	}	
}
