package com.qst.demo;

import java.util.HashSet;
import java.util.Scanner;

public class Demo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ip = null;

		HashSet<String> setPath = new HashSet<String>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] st = line.split("\t");
			if (ip == null) {//第一行数据
				ip = st[0];
				setPath.add(st[1]);
			} else {
				if (ip.equals(st[0])) {
					setPath.add(st[1]);//当ip重复，直接往hashmap中追加数据
				} else {
					System.out.println(ip + "\t" + setPath.size());//当ip不同，先输出，hashmap的大小即为路径个数
					setPath.clear();
					ip = st[0];//在清空，再加入新的数据
					setPath.add(st[1]);
				}
			}
		}
		System.out.println(ip + "\t" + setPath.size());//输出最后一个ip
		sc.close();
	}
}
