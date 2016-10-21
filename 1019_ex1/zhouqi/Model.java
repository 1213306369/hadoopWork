package sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
import org.apache.hadoop.mapreduce.Job;

public class Model {
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, Text> {
		@Override
		public void map(LongWritable key, Text value, OutputCollector<IntWritable, Text> output, Reporter reporter)
				throws IOException {
			System.err.println("maptest");
			String line = value.toString();
			Integer year = Integer.parseInt(line.split("-")[0].trim());
			output.collect(new IntWritable(year), new Text(line));
		}

	}

	public static class Reduce extends MapReduceBase implements Reducer<IntWritable, Text, Text, Text> {
		@Override
		public void reduce(IntWritable key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
				throws IOException {
			System.err.println("reducetest");
			List<String> list = new ArrayList<String>();
			while (values.hasNext()) {
				list.add(values.next().toString());
			}
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					int temp1 = Integer.parseInt(s1.split("\t")[1]);
					int temp2 = Integer.parseInt(s2.split("\t")[1]);
					if (temp1==temp2) return 0;
					return temp1 > temp2 ? -1 : 1;
				}
			});
			for (String str : list) {
				output.collect(new Text(str.split("\t")[0]), new Text(str.split("\t")[1]));
			}
		}

	}

	public static class IntWritableDecreasingComparator extends IntWritable.Comparator {
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return -super.compare(b1, s1, l1, b2, s2, l2);
		}
	}

	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(Model.class);
		conf.setJobName("Medel");
		conf.setInputFormat(TextInputFormat.class);
		conf.setMapOutputKeyClass(IntWritable.class);
		conf.setOutputFormat(TextOutputFormat.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		Job job =new Job(conf);
		job.setSortComparatorClass(IntWritableDecreasingComparator.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}
