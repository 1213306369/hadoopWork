import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REtolog {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String pattern = "GET(.*?)HTTP";

		while (sc.hasNext()) {

			String line = sc.nextLine();
			String[] words = line.split("-");
			String ip = words[0];

			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(words[2]);

			while (m.find()) {
				System.out.println(ip + "\t" + m.group(1));
			}

		}

	}

}
