package com.qst.test;
import java.util.Scanner;
public class SingleJob {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum=1;
			String[] token = sc.nextLine().split("\t");
			while(true){
				if(sc.hasNextLine()){
					String[] token1 = sc.nextLine().split("\t");
					if(token[0].equals(token1[0])){
						sum++;
					}else{
						System.out.println(token[0]+"\t"+sum);
						sum=1;
						token =token1;
					}
				}else{
					System.out.println(token[0]+"\t"+sum);
					break ;
				}
				
		}
	}
}
