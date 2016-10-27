package streamtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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



public class Count {
	public static class Maps extends MapReduceBase implements Mapper<LongWritable , Text,
	Text, Text>{
		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<Text, Text> output, Reporter reporter)
						throws IOException {
			String line = value.toString();
			String []splits =line.split("\t");
				output.collect(new Text(splits[0]),new Text(splits[1]));
		}
	}
	
	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, 
	Text, Text> {	
		Map<Text, Text> m = new HashMap<Text, Text>();
		@Override
		public void reduce(Text key, Iterator<Text> values,
				OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			m.clear();
			while (values.hasNext()){
				Text storm = new Text(values.next());
				m.put(storm, storm);
			}
			Set<Text> s = m.keySet();
			Iterator<Text> i = s.iterator();
			int count = 0;
			while(i.hasNext()){
				i.next();
				count++;
			}
			output.collect(key, new Text(""+count));
		}
	}
	
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(Count.class);
		conf.setJobName("Count");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);		
		conf.setMapperClass(Maps.class);
		conf.setReducerClass(Reduce.class);
		conf.setNumReduceTasks(1);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);
	}
}
