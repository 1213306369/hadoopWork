import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;


public class SecondarySort {
	public static void main(String[] args) throws IOException{
		// 配置map和reduce
		JobConf conf = new JobConf(SecondarySort.class);
		conf.setJobName("Secondary Ray");
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setMapperClass(SSMapper.class);
		conf.setReducerClass(SSReduce.class);
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);
		System.out.println("nice");
	}
	
	public static class SSMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
			// 输入处理
			String line = value.toString(); // "2016-10-01 10:00:00 \t 10"
			// 获取年份
			String[] tokens = line.split("-"); // "2016-10-01 10:00:00\t10"
			// tokens: ['2016', '10', '01 10:00:00\t10']
			String year = tokens[0];
			// 获取温度
			//String[] t_tokens = line.split("\t");
			// t_tokens: ['2016-10-01 10:00:00', '10']
			//int t = Integer.parseInt(t_tokens[1]);
			//String t = t_tokens[1];
			// 输出到reduce
			output.collect(new Text(year),value);
	    }
	}

	
	public static class SSReduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
		@SuppressWarnings("unchecked")
		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
				throws IOException {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (  ;  values.hasNext()  ;  ){
				Text value = values.next();
				String[] tokens = value.toString().split("\t");
				arr.add(Integer.parseInt(tokens[1]));
				
			}

			CustomComparator c = new CustomComparator();
			Collections.sort(arr, c);
			int count = 0;
			for (int i=0; i<arr.size(); i++)
			{
				output.collect(key,
						new Text(String.valueOf(arr.get(i)))
				);
				count++;
			}
			System.err.printf("has output %d records", count);
			
		}
		public class CustomComparator implements Comparator{
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				int i1 = (Integer)o1;
				int i2 = (Integer)o2;
				if (i1 < i2){
					return 1;
				}
				else{
					if (i1 == i2){
						return 0;
					}
					return -1;
				}
				//return 0;
			}
		};

	}

	

}
