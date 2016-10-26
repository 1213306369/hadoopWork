import java.util.Scanner;


public class nice {
public static void main(String[] args) {
	Scanner sc;
	sc=new Scanner(System.in);
	while(sc.hasNext()){
		String line=sc.nextLine();
		String[] word = line.split(" ");
		if (word.length > 6){
			System.out.println( word[0]+"\t"+word[6]);
		}
	}

}
}
