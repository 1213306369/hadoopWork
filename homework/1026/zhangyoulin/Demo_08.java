package demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Jacoulin
 * 
 * cat access.log | java -cp demo.jar demo.Demo_05 | sort -t$'\t' -k1 | java -cp demo.jar demo.Demo_08 2>& 1 > result2
 *
 * ͳ��ÿ��IP���ʹ���·����������ex3�������Ϊ����
 *
 */

public class Demo_08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Map<String, String>> m = null;
		int count = 1;
		while(sc.hasNext()){
			String[] line = sc.nextLine().split("\\t");
			line[0] = line[0].substring(3);
			if(m != null){
				if(m.containsKey(line[0])){
					if(m.get(line[0]).containsKey((line[1]))){
						
					}else{
						count++;
					}
				}else{
					Set<String> s = m.keySet(); 
					Iterator<String> i = s.iterator();
					if(i.hasNext()){
						System.out.println("ip:"+i.next()+"\t"+"pathnum:"+count);
					}
					count = 1;
					//�����ͷ��ڴ���������⣬ֻ��Ϊ����Ӧǰ��� m != null ���ж�����
					m.clear();
					m = null;
				}
			}else{
				m = new HashMap<String, Map<String,String>>();
				Map<String, String> m1 = new HashMap<String, String>();
				m1.put(line[1], line[1]);
				m.put(line[0], m1);
			}
		}
	}
}
