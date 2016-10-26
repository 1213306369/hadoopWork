package test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhengze {
	public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[[^ ]* [^ ]*\\] \"[^ ]+ ([^ ]+) .*";
	Pattern r = Pattern.compile(pattern);
	
	while(sc.hasNext()){
		String line = sc.nextLine();
		Matcher m = r.matcher(line);
		if(m.find()){
			//System.err.println("start find");
			System.out.printf("%s\t%s\n", m.group(1),m.group(2));
		}
	}
}
}
