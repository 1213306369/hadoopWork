package stream;

import java.util.Scanner;

public class Output {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String line = sc.nextLine();
			String[] tokens;
			tokens = line.split(" ");
			if (tokens.length > 6){
				System.out.printf("%s\t%s\n",tokens[0],tokens[6]);
			}
		}
	}

}
