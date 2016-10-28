import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] str = null;
			str = line.split(" ");
			if (str.length > 6) {
				System.out.println(str[0] + "\t" + str[6]);
			}
		}
	}
}
