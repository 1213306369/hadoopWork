package hadoop;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhengze {
	public static void main(String[] args) {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		
		//只匹配出IP、时间和访问的URL
		//String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* (\\[[^ ]* [^ ]*\\]) \"[^ ]+ ([^ ]+).*";
		//String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* [^ ]* [^ ]* [^ ]+ ([^ ]+).*";
		//String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* (\\[[^ ]* [^ ]*\\]) \"([^ ]+) ([^ ]+) ([^ ]+)\" (\\d+) (\\d+) \"([^ ]+)\" \"([^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+ [^ ]+)\"";
		String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* (\\[[^ ]* [^ ]*\\]) \"([^ ]+) ([^ ]+) ([^ ]+)\" (\\d+) (\\d+) \"([^ ]+)\" \"(.*)\"";
		Pattern r = Pattern.compile(pattern);
		while(sc.hasNext()){
			String line = sc.nextLine();
			Matcher m = r.matcher(line);
			if(m.find()){
				//System.out.printf("%s\t%s\n",m.group(1),m.group(2));
				System.out.println(m.group(1)+"\t"+m.group(2)+"\t"+m.group(3)+"\t"+m.group(4)+"\t"+m.group(5)+"\t"+m.group(6)+"\t"+m.group(7)+"\t"+m.group(8)+"\t"+m.group(9)+"\t");
			}
		}	
	}
}
