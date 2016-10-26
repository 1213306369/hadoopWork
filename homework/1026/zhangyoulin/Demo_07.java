package demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo_07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String format = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[[^ ]* [^ ]*\\] \"[^ ]+ ([^ ]+) .*";
		Pattern pattern = Pattern.compile(format);
		while(sc.hasNext()){
			String line = sc.nextLine();
			Matcher m = pattern.matcher(line);
			if(m.find()){
				System.out.println("ip:"+m.group(1)+"\t"+"path:"+m.group(2));
			}
		}
	}
}
