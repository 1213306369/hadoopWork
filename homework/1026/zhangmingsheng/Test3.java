import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Test3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Object> list = new ArrayList<Object>();
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			list.add(line);
		}
		Set<Object> set = new HashSet<Object>();
		List<Object> newList = new ArrayList<Object>();
		for (Iterator<Object> iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		int sum = 0;
		String ez = null;
		for(int i = 0;i < list.size();i++){
			String line = (String) list.get(i);
			String[] str = null;
			str = line.split("\t");
			if (!str[0].equals(ez)) {
				if (ez != null) {
					System.out.println(ez + "\t" + sum);
				}
				sum = 1;
				ez = str[0];
			} else {
				sum++;
			}
		}
		System.out.println(ez + "\t" + sum);
	}
}
