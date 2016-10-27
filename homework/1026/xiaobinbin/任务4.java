import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HashSet<String> HSList = new HashSet<String>();		
		while(in.hasNext()){
			HSList.add(in.nextLine());
		}

		HashMap<String,Integer> HMList = new HashMap<String, Integer>();
		for (String onedata : HSList) {
			String [] data = onedata.split("\t\t");//取出IP  data[0]
			Integer cou = HMList.get(data[0]);
			HMList.put(data[0], cou == null?1:cou+1);
		}
		
		for(String key : HMList.keySet()) {  
		    System.out.println(key + "\t" +HMList.get(key));  
		}
	}
}

