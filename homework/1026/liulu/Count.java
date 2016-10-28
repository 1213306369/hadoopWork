package com.qst.streaming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Count {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String key = null;
		String value = null;
		Set<String> newSet = new HashSet<String>();
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] str = line.split("\t");
			if (key == null) {
				key = str[0];
				value = str[1];
				newSet.add(str[1]);
			} else {
				if (key.equals(str[0])) {
					if(!value.equals(str[1])){ 
						newSet.add(str[1]);
					}
				} else {
					System.out.println(key + "\t" + newSet.size());
					newSet.clear();
					key = str[0];
					newSet.add(str[1]);
				}
			}
		}
		System.out.println(key + "\t" + newSet.size());
	}
}
