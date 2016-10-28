package task1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhengze {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String p="(\\d+.\\d+.\\d+.\\d+).*(\\[.*\\]) \"([A-Z]+) (/[a-zA-Z]+/\\d+).*(http://[^ ]+).*(Mozilla/.*\\);) (.*)";
		Pattern pattern=Pattern.compile(p);
		while (sc.hasNext()){
			String line=sc.nextLine();
			Matcher matcher=pattern.matcher(line);
			if(matcher.find()){
				System.out.println("IP:"+matcher.group(1));
				System.out.println("Time:"+matcher.group(2));
				System.out.println("Method:"+matcher.group(3));
				System.out.println("URL:"+matcher.group(4));
				System.out.println("Refer:"+matcher.group(5));
				System.out.println("Machine:"+matcher.group(6));
				System.out.println("Browser:"+matcher.group(7));
				
			}
		}
		sc.close();
		/*
		 
		182.118.25.221 - - [31/Dec/2015:23:58:05 +0800] "GET /show/4949 HTTP/1.1" 200 33560 "http://services.youyanchu.com/show/4949" "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0); 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)"
		182.118.25.222 - - [31/Dec/2015:23:58:06 +0800] "GET /show/4423 HTTP/1.1" 200 20094 "http://services.youyanchu.com/show/4423" "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0); 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)"
		111.206.36.134 - - [31/Dec/2015:23:58:11 +0800] "GET /show/6133 HTTP/1.1" 301 182 "-" "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2"
		182.118.22.211 - - [31/Dec/2015:23:58:21 +0800] "GET /show/5552 HTTP/1.1" 200 32378 "http://services.youyanchu.com/show/5552" "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0); 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)"
		101.226.167.236 - - [31/Dec/2015:23:59:00 +0800] "GET /?begin_date=2013-06-28&end_date=2013-12-28&page=4 HTTP/1.1" 200 40226 "http://www.youyanchu.com/?begin_date=2013-06-28&end_date=2013-12-28&page=4" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1; 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)"
		180.149.134.10 - - [31/Dec/2015:23:59:03 +0800] "POST / HTTP/1.1" 301 182 "-" "Jakarta Commons-HttpClient/3.1"
		123.125.104.247 - - [31/Dec/2015:23:59:29 +0800] "POST / HTTP/1.1" 301 182 "-" "Jakarta Commons-HttpClient/3.1"
		180.149.134.10 - - [31/Dec/2015:23:59:29 +0800] "POST / HTTP/1.1" 301 182 "-" "Jakarta Commons-HttpClient/3.1"
		
		*/
	}
}
