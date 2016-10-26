package mapRed;

import java.util.Scanner;

public class Streaming2 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	while(sc.hasNext()){
		String line = sc.nextLine();
		String[] a = line.split(" ");
		System.out.println(a[0]+"\t"+a[6]);
	}
}
}
