package com.qst.friend;

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



public class Friend {
 public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
       String line = value.toString();
       String str[] = line.split(" ");
       for(int i=1;i<str.length;i++){
    	   String a="";
    	   if(str[0].compareTo(str[i])<0){
    	       a=str[0]+","+str[i];
    	   }else if(str[0].compareTo(str[i])>0){
    		   a=str[i]+","+str[0];
    	   }
    	   String b="";
    	   for(int j=1;j<str.length;j++){
    		   if(j!=i){
    		   b=b+" "+str[j];		   
    		   }
    	   }
    	   output.collect(new Text(a),new Text(b));
       }      
	}
	public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text>{

		@Override
		public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter)
				throws IOException {
			List<String>list =new ArrayList<String>();
			while(values.hasNext()){
			
			String a = values.next().toString();
			list.add(a);
			}
			if(list.size()==2){
				String s1=list.get(0);
				String s2=list.get(1);
				StringBuffer ss = new StringBuffer();
				String [] str=s1.split(" ");
				for(int i=0;i<str.length;i++){
					if(s2.indexOf(str[i])>=0){
						ss.append(str[i]+" ");
					}
				}
				output.collect(key, new Text("["+ss+"]"));
			}
			}
		}
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(Friend.class);
		conf.setJobName("Friend");
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

