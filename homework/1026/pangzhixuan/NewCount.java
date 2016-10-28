
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NewCount{

     public static void main(String [] args){
	 
	    Scanner sc=new Scanner(System.in);
	    
	    Set<String> set=new HashSet<>();
	    
	    String key=null;
	    
		while(sc.hasNext()){
			
		    String line=sc.nextLine();
		    
		    String [] str=line.split("\t");
		     
		    if( key==null ||  key.equals(str[0]) ){
		    	 key=str[0];
		    	 set.add(str[1]);
		    }else{
		    	System.out.println(key+"\t"+set.size());
		    	set.clear();
		    	key=str[0];
		    	set.add(str[1]);
		    }
		   }
		}
}

