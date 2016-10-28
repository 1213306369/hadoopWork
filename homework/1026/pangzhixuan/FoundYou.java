
import java.util.Scanner;

public class FoundYou{

     public static void main(String [] args){
	 
	    Scanner sc=new Scanner(System.in);
		while(sc.hasNext()){
		    String line=sc.nextLine();
		    String [] strs=line.split(" ");
		    if(strs.length>6){
			System.out.println(strs[0]+"\t"+strs[6]);
		    }
		}
	}
}