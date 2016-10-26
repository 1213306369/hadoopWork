package com.qst;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stream3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		String previous="";
		while(sc.hasNext()){
			String line=sc.nextLine();
			String items[]=line.split("\t");
			String ip = items[0];
			String url = items[1];
			if(previous.equals(ip)){
				if(!list.contains(url)){
					list.add(url);
				}
			}else{
				if(!previous.equals(""))
					System.out.println(previous+"\t"+list.size());
				previous=ip;
				list.clear();
				list.add(url);
			}
		}
		sc.close();
	}	
}
