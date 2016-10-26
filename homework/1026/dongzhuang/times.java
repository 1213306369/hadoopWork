import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class times {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	HashMap<String, Integer> hm=new HashMap<>();
	while (sc.hasNext()){
		int count=1;
		String line=sc.nextLine();
		String str[]=line.split("\t");
		if(hm.containsKey(str[0])){//判断刚刚输入的单词是否已经存在
			count = hm.get(str[0])+1;//如果已经存在，新的个数就在已有的个数上加1
		}
		hm.put(str[0], count);
	}
	sc.close();
	Iterator<Entry<String, Integer>> it = hm.entrySet().iterator();
	while(it.hasNext()){
		Entry<String, Integer> entry=(Entry<String, Integer>)it.next();
		System.out.println(entry.getKey()+"\t"+entry.getValue());
	}
}
}
