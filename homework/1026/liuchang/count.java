import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UrlCount {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<String>();	//建立列表
		String ip = null;	//上一个IP
		while (sc.hasNext()) {	
			String line = sc.nextLine();	
			String[] tokens=line.split("\t");	//读入的内容用\t分割，保存为数组
			String newIp = tokens[0];	//数组第一个元素是ip
			String url = tokens[1];	//数组第二个元素是地址
			/*
			 * if进行判断，如果newIp跟ip相等再进行判断
			 * if判断是否这两个里面的url也相等，不相等则list.add（list.size +1）
			 * */
			if (newIp.equals(ip)) {
				if(!list.contains(url))
					list.add(url);
				/*
				 * 第一次输入的时候ip=null
				 * 不是第一次输入的时候ip!=null，所以输出Ip	列表size
				 */
			}else{
				if (ip!=null) System.out.println(ip+"\t"+list.size());
				
				ip = newIp;    
				list.clear();	//清空list准备读入下一条内容
				list.add(url);	//第一次读入的时候上面的if都不满足，所以到了这一步
			}
		}
		if (ip!=null) System.out.println(ip+"\t"+list.size());	//针对最后一条数据和上一条IP相等的情况
		else System.out.println("文件是空的");	//如果文件是空的 则报告给用户
		
		sc.close();
		
	}

}
