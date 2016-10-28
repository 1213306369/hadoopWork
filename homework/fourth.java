package fstest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChukChuk {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);
		while(sc.hasNext()){
			String in = sc.nextLine();
			set.add(in);
		}
		HashMap<String,Integer> hmap = new HashMap<String,Integer>();
		for(String string:set){
			String[] data = string.split(" ");
			Integer key = hmap.get(data[0]);
			hmap.put(data[0], key == null?1:key+1);
		}
		for(String key : hmap.keySet()){
			System.out.printf("%s,%s",key,hmap.get(key));
		}
	}
}
