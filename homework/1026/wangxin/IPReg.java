package com.qst.demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPReg {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//String pattern="(\\d+.\\d+.\\d+.\\d+) ";//只匹配ip
//		String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+/[A-Z][a-z]+/\\d+:\\d+:\\d+:\\d+) [^ ]*\\]";//匹配IP和时间
//		String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+/[A-Z][a-z]+/\\d+:\\d+:\\d+:\\d+) [^ ]*\\] \"[^ ]+ ([^ ]+) .*";//匹配IP.时间、URL
		String pattern = "(.*)";
		Pattern r = Pattern.compile(pattern);
		while(sc.hasNext()){
			String line = sc.nextLine();
			Matcher m =r.matcher(line);
			if(m.find()){
//				System.out.printf("%s\n",m.group(1));//输出只匹配IP
//				System.out.printf("%s\t%s\n",m.group(1),m.group(2));//输出匹配IP和时间
//				System.out.printf("%s\t%s\t%s\n",m.group(1),m.group(2),m.group(3));//输出匹配IP.时间和URL
				System.out.println(m.group(1));
			}
		}
	}
}
