package org.zagjab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class nginxlogformat {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String pattern = "(\\d+.\\d+.\\d+.\\d+) ([^ ]* [^ ]*) \\[([^ ]* [^ ]*)\\] \"([^ ]+) ([^ ]+) ([^ ]+)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"";
		Pattern r = Pattern.compile(pattern);
		while(sc.hasNext()){
			String line = sc.nextLine();
			Matcher m = r.matcher(line);
			if(m.find()){
				System.out.println("IP:\t"+m.group(1)+'\n'+"User:\t"+m.group(2)+'\n'+"Time:\t"+m.group(3)+'\n'+"Method:\t"+m.group(4)+'\n'+"URL:\t"+m.group(5)+'\n'+"HTPV:\t"+m.group(6)+'\n'+"Status:\t"+m.group(7)+'\n'+"Bytes:\t"+m.group(8)+'\n'+"Refer:\t"+m.group(9)+'\n'+"Agent:\t"+m.group(10));
			}
		}
		
	}
	
	/*
	 113.105.12.154 - - [31/Dec/2015:00:00:23 +0800] "GET /show/19871 HTTP/1.1" 301 182 "http://m.sp.sm.cn/s?q=http%3A%2F%2Fyouyanchu.com%2Fshow%2F19871&uc_param_str=dnntnwvepffrgibijbprsv&dn=14904029003-5933e910&nt=2&nw=0&ve=10.3.0.547&pf=44&fr=iphone&gi=bTkwBTOkNreQarpBJRt20qTSX65lSDpkHPE%2BmsMrVum%2BooA%3D&bi=997&jb=2&pr=UCBrowser&sv=app&from=ucframe&yz=1&by=submit&snum=0" "Mozilla/5.0 (iPhone; CPU iPhone OS 8_4_1 like Mac OS X; zh-CN) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/12H321 UCBrowser/10.3.0.547 Mobile"
	 */
}
