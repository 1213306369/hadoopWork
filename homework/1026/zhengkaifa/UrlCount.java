import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UrlCount {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		String ip = null;
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] tokens=line.split("\t");
			String newIp = tokens[0];
			String url = tokens[1];
			
			if (newIp.equals(ip)) {
				if(!list.contains(url))
					list.add(url);
			}else{
				if (ip!=null) System.out.println(ip+"\t"+list.size());
				
				ip = newIp;
				list.clear();
				list.add(url);
			}
		}
		if (ip!=null) System.out.println(ip+"\t"+list.size());
		else System.out.println("文件是空的");
		
		sc.close();
		
	}

}
