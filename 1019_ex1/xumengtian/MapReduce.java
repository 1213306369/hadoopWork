package com.qst.a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class MapReduce {
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

		@Override
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			String line = value.toString();
			String str[] = line.split("-", 2);
			output.collect(new Text(str[0]), value);
		}

		public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

			@Override
			public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
					throws IOException {
				List<String> list = new ArrayList<String>();
				while (values.hasNext()) {
					String aa = values.next().toString();
					list.add(aa);
				}
				compare(list);
				for (String dd : list) {
					output.collect(new Text(dd), new Text(""));
				}
			}

			public void compare(List<String> list) {
				for (int i = 0; i < list.size() - 1; i++) {
					for (int v = 0; v < list.size() - i - 1; v++) {
						String str1[] = list.get(v).split("\t", 2);
						String str2[] = list.get(v + 1).split("\t", 2);
						int o = Integer.parseInt(str1[1]);
						int p = Integer.parseInt(str2[1]);
						if (o > p) {
							String bb;
							bb = list.get(v);
							list.set(v, list.get(v + 1));
							list.set(v + 1, bb);
						}
					}
				}
			}

			public static void main(String[] args) throws Exception {
				JobConf conf = new JobConf(MapReduce.class);
				conf.setJobName("MapReduce");
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

	}
}
