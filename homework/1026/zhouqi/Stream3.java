package com.qst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Stream3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<String> set = new HashSet<String>();
		String previous="";
		while(sc.hasNext()){
			String line=sc.nextLine();
			String items[]=line.split("\t");
			String ip = items[0];
			String url = items[1];
			if(previous.equals(ip)){
					set.add(url);
			}else{
				if(!previous.equals(""))
					System.out.println(previous+"\t"+set.size());
				previous=ip;
				set.clear();
				set.add(url);
			}
		}
		if(!previous.equals(""))
			System.out.println(previous+"\t"+set.size());
		sc.close();
	}	
}
