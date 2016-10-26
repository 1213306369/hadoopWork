package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P {
	public static void main(String[] args) {
	Scanner sc= new Scanner(System.in); //创建一个输入
	int first = 0; //判断第一行
	int number = 0;//计数值
	String s = null; //上一个IP的
	while(sc.hasNext()){
	 String line=sc.nextLine();  //读取一行数据
	 String[] splits =line.split("\t");  //【0】代表前面的IP地址，
	 
	 if(first == 0){//判断是不是第一行
		 s = splits[0];  //初始值给S
		 first = 1;  //改变第一行
	 }
	 if(splits[0].equals(s)){
		 number++; //相等+1
		 }
	 else{
		System.out.printf("%s\t%d\n",s,number);
		s=splits[0];
		number=1;
	 }
	 if(sc.hasNext()==false){
			System.out.printf("%s\t%d\n",splits[0],number);
		}
	 }
}
}
