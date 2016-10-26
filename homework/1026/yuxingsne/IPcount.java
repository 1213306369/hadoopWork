import java.util.HashSet;
import java.util.Scanner;


public class IPcount {
public static void main(String[] args) {
	Scanner sc;
	sc=new Scanner(System.in);
	 String key=null;
	 String value=null;
	 HashSet<String> values = new HashSet<String>();
	while(sc.hasNext()){
		//遍历数组，进行统计路径
		String s = sc.nextLine(); 
		String[] word = s.split("\t",2);
		if (word.length >1){
			if(key==null){
				key=word[0];
				value=word[1];
				values.add(value);	
				continue;
				}
			if(key!=null){
				if(key.equals(word[0])){
					values.add(word[1]);
				}else{
					int count=values.size();
					System.out.println(key+"\t"+count);
					values.clear();
					key=word[0];
					value=word[1];
					values.add(value);	
				}
						
			}
		}
						
	}
	
		System.out.println(key+"\t"+values.size());
}
}
