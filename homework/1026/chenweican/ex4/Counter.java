package ex4;

import java.util.Scanner;

public class Counter {
	public static void main(String[] args){
		Scanner sc;
		// Scanner 负责从System.in中读入
		sc = new Scanner(System.in);
		int iLineCount = 0;
		// 不断读入，直到没有下一行为止
		String key = null;
		String value;
		while (sc.hasNext()){
			String line = sc.nextLine(); // 读入下一行
			String[] tokens = line.split("\t");
			if (2 != tokens.length){
				continue;
			}
			if (null == key){
				// 处理第一行
				key = tokens[0];
				value = tokens[1];
			}
			else{
				// 处理非第一行
			}
			
			iLineCount++;
		}
	}
}
