package common;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class FindFriends {
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		@Override
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			 StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
	            Text owner = new Text();//存放自己

	            Set<String> set = new TreeSet<String>();//存放朋友

	            owner.set(stringTokenizer.nextToken());
	            while(stringTokenizer.hasMoreTokens()){
	                set.add(stringTokenizer.nextToken());
	            }

	            String[] friends = new String[set.size()];//朋友
	            friends = set.toArray(friends);

	            for(int i=0; i<friends.length;i++){
	                for(int j=i+1; j<friends.length; j++){
	                    String outputkey = friends[i]+","+friends[j];//朋友之间两两组合
	                    output.collect(new Text(outputkey), owner);//<朋友组合，自己>
	                }
	            }
			
		}

	}



	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
				throws IOException {
			
			List<String> list=new ArrayList<String>();
			while(values.hasNext()){
					list.add(values.next().toString());
			}
			
			 String commonFriends = "";
	            for(String str : list)
	            {
	                if(commonFriends == ""){
	                    commonFriends = str.toString();
	                }else{
	                    commonFriends = commonFriends+","+str.toString();
	                }
	            }
	            output.collect(key, new Text("["+commonFriends+"]"));
	        }
		
		}

	

	public static void main(String[] args) throws IOException {
		JobConf conf = new JobConf(FindFriends.class);
		conf.setJobName("Medel");
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);

	}
}

