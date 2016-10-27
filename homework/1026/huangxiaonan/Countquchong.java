package hadoop;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Count {
	public static void main(String[] args) {
		//List<Count> list = new ArrayList<Count>();
		Scanner sc = new Scanner(System.in);


		Set<String> st= new HashSet<String>(); 
			String line = sc.nextLine();
			String[] tokens = line.split("\t");
			while(sc.hasNext()){
				String lines = sc.nextLine();
				String[] tokenss = lines.split("\t");
				if(tokenss[0].equals(tokens[0])){
					st.add(tokens[1]);
					tokens = tokenss;
				}else{
					System.out.println(tokens[0]+"\t"+(st.size()+1));
					st.clear();
					tokens = tokenss;
				}
			}
			System.out.println(tokens[0]+"\t"+(st.size()+1));
			
			/*int firt = 0;
			if(firt==0){
				t = tokens[0];
				firt=1;
			}
			if(tokens[0].equals(t)){
				count++;
			}else{
				System.out.println(tokens[0]+"\t"+count);
			}
			if(sc.hasNext()==false){
				t = tokens[0];
				count=1;
				System.out.println(tokens[0]+"\t"+count);
			}
			t = tokens[0];
			count=1;*/
		
	}

}
