package friend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;


public class CommonFriends {
	public static class Map extends MapReduceBase implements Mapper<LongWritable , Text,
	Text, Text>{
//		private Text person = new Text();
//		private ArrayList<String> storm = new ArrayList();
		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			String line = value.toString();
			String []friends =line.split(" ");
			String person = friends[0];
//			StringTokenizer tokenizer = new StringTokenizer(line);
//			person.set(tokenizer.nextToken());
//			while (tokenizer.hasMoreTokens()) {
//				storm.add(tokenizer.nextToken());
//			}
//			String [] friends = new String[storm.size()];
//			friends = storm.toArray(friends);
			for(int i = 1;i < friends.length;i++){
				for(int j=i+1; j<friends.length; j++){
                    String outputkey = "[" + friends[i]+","+friends[j] + "]";
                    output.collect(new Text(outputkey), new Text(person));
                }
			}
		}
	}
	
	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, 
	Text, Text> {
		public void reduce(Text key, Iterator<Text> values,
				OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			String commonfriends = "";
			while(values.hasNext()){
				if(commonfriends == ""){
					commonfriends = values.next().toString();
				}else{
					commonfriends = commonfriends + "&" + values.next().toString();
				}
			}
			output.collect(key,new Text(commonfriends));
//			for(Text v:values){
//				if(commonfriends == ""){
//					commonfriends = v.toString();
//				}else{
//					commonfriends = commonfriends + "," + v.toString();
//				}
//			}
//			output.collect(key,new Text(commonfriends));
		}
	}
	
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(CommonFriends.class);
		conf.setJobName("Common");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
		conf.setNumReduceTasks(1);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		JobClient.runJob(conf);
	}
}
