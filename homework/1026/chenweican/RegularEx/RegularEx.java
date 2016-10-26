package RegularEx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularEx {
	public static void main(String[] args){
		String line = "10.0.0.1 2016-10-20 10:00:00";
		String pattern = "(\\d+).\\d+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find()){
			System.out.println(m.group(0)); // m.group(0) 表示匹配上的子串
			System.out.println(m.group(1)); // m.group(1) 表示第一个括号内匹配上的内容
		}
		else{
			System.out.println("miss");
		}
		
	}
}
