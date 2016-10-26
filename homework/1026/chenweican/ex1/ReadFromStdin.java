package ex1;

import java.util.Scanner;

public class ReadFromStdin {
	public static void main(String[] args){
		Scanner sc;
		// Scanner 负责从System.in中读入
		sc = new Scanner(System.in);
		int iLineCount = 0;
		// 不断读入，直到没有下一行为止
		while (sc.hasNext()){
			String line = sc.nextLine(); // 读入下一行
			System.out.println(line);
			iLineCount++;
		}
		System.err.printf("I have recieved %d lines\n", iLineCount);
	}

}
