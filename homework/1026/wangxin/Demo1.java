package com.qst.demo;

import java.util.Scanner;

public class Demo1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String st = scanner.nextLine();
			System.out.println(st);
		}
	}
}
