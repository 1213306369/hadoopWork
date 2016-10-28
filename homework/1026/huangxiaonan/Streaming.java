package hadoop;

import java.util.Scanner;

public class Streaming {
	public static void main(String[] args) {
		Scanner sc;
		sc = new Scanner(System.in);
		while(sc.hasNext()){
			String line = sc.nextLine();
			String[] tokens;
			tokens = line.split(" ");
			if(tokens.length>6){
				//System.out.printf("%s\t%s\n",tokens[0],tokens[6]);
				System.out.println(tokens[0]+"\t"+tokens[6]);
			}	
		}
	}
}
