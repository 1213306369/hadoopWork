package org.zagjab.count;

import java.util.HashSet;
import java.util.Scanner;

public class Count {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String key = "";
		HashSet<String> set = new HashSet<String>();
		while(sc.hasNext()){
			String line = sc.nextLine();
			String[] tokens =line.split("\t");
			if(key==""){
				key = tokens[0];
				set.add(tokens[1]);
			}else{
				if(key.equals(tokens[0])){
					set.add(tokens[1]);
				}else{
					System.out.println(key+'\t'+set.size());
					set.clear();
					key = tokens[0];
					set.add(tokens[1]);
				}
			}
		}
		System.out.println(key+'\t'+set.size());
	}
}
