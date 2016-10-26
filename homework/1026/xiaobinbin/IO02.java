Pckage com.test;
import java.util.Scanner;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			String string = in.nextLine();
			String [] data = string.split(" ");
			System.out.println("IP:" + data[0] + "\t\tPATH:" + data[6]);
		}
	}

}

