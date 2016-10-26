import java.util.HashSet;
import java.util.Scanner;

public class StoreHashSet {
	static String ip = null;
	static String oldIP = null;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		HashSet hashSetIp = new HashSet();
		HashSet hashSetPath = new HashSet();

		while (sc.hasNext()) {

			String line = sc.nextLine();
			String[] words = line.split("\t");
			ip = words[0];

			if (hashSetIp.contains(ip)) {

				hashSetPath.add(words[1]);

			} else {

				System.out.println(oldIP + " " + hashSetPath.size());

				oldIP = ip;
				hashSetIp.add(ip);
				hashSetPath.clear();
				hashSetPath.add(words[1]);
				hashSetIp.add(words[1]);

			}

		}
		System.out.println(oldIP + " " + hashSetPath.size());

	}

}
