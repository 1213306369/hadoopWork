package com.qst.demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1ByReg {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String pattern = "(\\d+. \\d+. \\d+. \\d+) [^ ]* [^ ]* \\[[^ ]* [^ ]*\\] \"[^ ]+([^ ]+) .*";
		Pattern r = Pattern.compile(pattern);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			Matcher m = r.matcher(line);
			if(m.find()){
				System.err.println("find you");
				System.out.printf("%s\t%s\n",m.group(1),m.group(2));
			}
		}
	}
}
