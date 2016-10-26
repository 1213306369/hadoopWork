import java.util.Scanner;

public class Find {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	while(sc.hasNext()){
		String line=sc.nextLine();
		String str[]=line.split(" ");
		System.out.println(str[0]+"\t"+str[6]);
	}
}
}
