package upload;

import java.util.Scanner;

public class pipei {
 public static void main(String[] args) {
	 Scanner scan=new Scanner(System.in);
		
		while(scan.hasNext()){
			String line=scan.nextLine();
			//System.out.println("------------");
			String []split=line.split(" ");
			if(split.length>6){
				System.out.println(split[0]+"\t"+split[6]);
			}
		}
}
	
}
