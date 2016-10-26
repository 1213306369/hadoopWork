package ex2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitLogByReg {
	public static void main(String[] args){
		
		Scanner sc;
		// Scanner 负责从System.in中读入
		sc = new Scanner(System.in);
		String pattern = "\\d+.\\d+.\\d+.\\d ([^ ]*) (.*)";
		Pattern r = Pattern.compile(pattern);

		// 不断读入，直到没有下一行为止
		while (sc.hasNext()){
			String line = sc.nextLine(); // 读入下一行
			/*
			String[] tokens;
			tokens = line.split(" ");
			if (tokens.length > 6){
				System.out.printf("%s\t%s\n", tokens[0], tokens[6]);
			}
			*/
			Matcher m = r.matcher(line);
			if (m.find()){
				System.out.println("find you");
				System.out.println(m.group(1));
			}
		}
	}
}
