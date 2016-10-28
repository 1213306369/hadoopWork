package task1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class times1 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int count=0;
	String ip=null;
	String url=null;
	HashSet<String> set = new HashSet<>();
	while(sc.hasNext()){
		String line=sc.nextLine();
		String str[]=line.split("\t");
		if(str.length!=2)
			continue;
		if(ip==null){
			ip=str[0];
			url=str[1];
			set.add(url);
		}else{
			if(ip.equals(str[0])){
				set.add(str[1]);
			}else{
				System.out.println(ip+"\t"+set.size());
				set.clear();
				ip=str[0];
				set.add(str[1]);
			}
		}
	}
	System.out.println(ip+"\t"+set.size());
}
}
