package upload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class count {
 public static void main(String[] args) {
	 Scanner scan=new Scanner(System.in);
		List<String> list=new ArrayList<String>();
		//List<String> listout=new ArrayList<String>();
		Map<String,Integer> map=new TreeMap<String,Integer>();
		
		int i=4;
		while(scan.hasNext()){
			String line=scan.nextLine();
			//System.out.println("------------");
			String []split=line.split("\t");
			list.add(split[0]);
			
		}
		//System.out.println(list);
		for (String s : list) {
			int sum=0;
			
			for (String s2 : list) {
				if(s.equals(s2)){
					sum++;
				}
					
			}
			map.put(s,sum);
			//System.out.println("-------");
			//System.out.println(map);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue()+"\n");
      }
        }  

	
}
