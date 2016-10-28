package test;

import java.util.Scanner;

public class Zz {

	public static void main(String[] args) {
		Scanner sc;
		sc = new Scanner(System.in);
		while(sc.hasNext()){
			String line =sc.nextLine();
			String [] str = line.split(" ");
			if(line.length()>6){
			System.out.println(str[0]+"\t"+str[6]);
			}
		}

	}

}
