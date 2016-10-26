package ex4;

import java.util.HashSet;
import java.util.Scanner;

public class Counter {
	public static void main(String[] args){
		Scanner sc;
		// Scanner 负责从System.in中读入
		sc = new Scanner(System.in);
		int iLineCount = 0;
		// 不断读入，直到没有下一行为止
		String key = null;
		String value;
		HashSet<String> setVisitPaths = new HashSet<String>();
		while (sc.hasNext()){
			String line = sc.nextLine(); // 读入下一行
			String[] tokens = line.split("\t");
			if (2 != tokens.length){
				continue;
			}
			if (null == key){
				// 处理第一行
				key = tokens[0];
				value = tokens[1];
				setVisitPaths.add(tokens[1]); // 放进集合中记录，别漏了
			}
			else{
				// 处理非第一行
				if (key.equals(tokens[0])){
					// 同一个key
					// 把路径放入集合
					setVisitPaths.add(tokens[1]);
				}
				else{
					// key 不一样，进入下一组了
					// 把上一组的数据先输出
					System.out.printf("%s\t%d\n", key, setVisitPaths.size());
					// 清空集合
					setVisitPaths.clear();
					key = tokens[0];
					setVisitPaths.add(tokens[1]);
				}
			}
			
			System.out.printf("%s\t%d\n", key, setVisitPaths.size()); // 最后一组不要忘了输出
		}
	}
}
