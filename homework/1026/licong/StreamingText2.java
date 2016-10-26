package hehe;

import java.util.Scanner;

public class StreamingText2 {
public static void main(String [] args ){
	
	Scanner sca=new Scanner(System.in);
	while(sca.hasNextLine()){
		String str =sca.nextLine();
	String [] str1=str.split(" ");
	if(str1.length>6){
		System.out.printf("%s\t%s\n",str1[0],str1[6]);
		
	}
		
	}
}
}
