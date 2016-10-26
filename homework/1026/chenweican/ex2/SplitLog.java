package ex2;

import java.util.Scanner;

public class SplitLog {
	public static void main(String[] args){
		Scanner sc;
		// Scanner 负责从System.in中读入
		sc = new Scanner(System.in);
		// 不断读入，直到没有下一行为止
		while (sc.hasNext()){
			String line = sc.nextLine(); // 读入下一行
			String[] tokens;
			tokens = line.split(" ");
			if (tokens.length > 6){
				System.out.printf("%s\t%s\n", tokens[0], tokens[6]);
			}
		}
	}
}
