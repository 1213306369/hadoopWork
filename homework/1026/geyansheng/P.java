package test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P {
	public static void main(String[] args) {
	Scanner sc= new Scanner(System.in);
	Set<String> se = new HashSet();
	int first = 0;
	String s = null; 
	while(sc.hasNext()){
	 String line=sc.nextLine(); 
	 String[] splits =line.split("\t"); 
	 if(first == 0){
		 s = splits[0];  
		 first = 1;  
	 }
	 if(splits[0].equals(s)){
		 se.add(splits[1]);
		 }
	 else{
		System.out.printf("%s\t%d\n",s,se.size());
		se.clear();
		s=splits[0];
		 se.add(splits[1]);
	 }
	 if(sc.hasNext()==false){
			System.out.printf("%s\t%d\n",splits[0],se.size());
		}
	 }
}
}
