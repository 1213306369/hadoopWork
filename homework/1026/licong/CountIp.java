package hehe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
public class CountIp {
	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<String>();
		Scanner sca = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		while (sca.hasNextLine()) {
			String str = sca.nextLine();
			String[] str1 = str.split("\t");
			if (list.isEmpty()) {//如果是空那么就船一个ip的值
				list.add(str1[0]);
			} else {//不为空比较
				if (list.contains(str1[0])) {hs.add(str1[1]);} else {
					System.out.println(list.get(0)+"\t"+hs.size());
					hs.clear();list.clear();hs.add(str1[1]);
				}
			}
		}
	}	
}
