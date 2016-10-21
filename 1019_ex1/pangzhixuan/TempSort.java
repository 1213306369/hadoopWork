package com.qst.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class TempSort {
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
		@Override
		public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			String line = value.toString();
			String [] str=line.split("-",2);
			output.collect(new Text(str[0]), new Text(line));
			
	}

	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter report)
				throws IOException {
			
			    List<String> list=new ArrayList<String>();
			    while(values.hasNext()){
			    	list.add((values.next()).toString());
			    }
			   
			    Collections.sort(list, new Comparator<Object>() {  
			    	   
			    	   public int compare(Object o1, Object o2) {  
			    	       String[] s1=((String) o1).split("\t",2);
			    	       String[] s2=((String) o2).split("\t",2);
			    	       int s11=Integer.parseInt(s1[1]);
			    	       int s22=Integer.parseInt(s2[1]);
			    	       if(s11>s22){
			    	    	   return 1;
			    	       }
			    	       return 0;
			    	    }  
			       });    
				   
			       for(int i=0;i<list.size();i++){
			    	    String str=list.get(i);
			    	    String [] strs=str.split("\t");
			    	    output.collect(new Text(strs[0]), new Text(strs[1]));
			       }
		}

	}
	
	public static class MyPartitioner extends MapReduceBase implements Partitioner<Text, Text> {

		@Override
		public int getPartition(Text key, Text value, int reduceNum) {
			String key1=key.toString();
			int key2=Integer.parseInt(key1);
			if(key2%2==0){
				return 0;
			}
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		JobConf conf = new JobConf(TempSort.class);
		conf.setJobName("TempSort");
		
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		
		conf.setNumReduceTasks(2);
		conf.setPartitionerClass(MyPartitioner.class);
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);
	}
}}
