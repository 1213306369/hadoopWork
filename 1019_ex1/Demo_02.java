package sort;  
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
  
public class Demo_02 {  
    //�Լ������key��Ӧ��ʵ��WritableComparable�ӿ�  
    public static class IntPair implements WritableComparable<IntPair> {  
        int first;  
        int second;  
        /** 
         * Set the left and right values. 
         */  
        public void set(int left, int right) {  
            first = left;  
            second = right;  
        }  
        public int getFirst() {  
            return first;  
        }  
        public int getSecond() {  
            return second;  
        }  
        @Override  
        //�����л��������еĶ�����ת����IntPair  
        public void readFields(DataInput in) throws IOException {  
            // TODO Auto-generated method stub  
            first = in.readInt();  
            second = in.readInt();  
        }  
        @Override  
        //���л�����IntPairת����ʹ�������͵Ķ�����  
        public void write(DataOutput out) throws IOException {  
            // TODO Auto-generated method stub  
            out.writeInt(first);  
            out.writeInt(second);  
        }  
        @Override  
        //key�ıȽ�  
        public int compareTo(IntPair o) {  
            // TODO Auto-generated method stub  
            if (first != o.first) {  
                return first < o.first ? -1 : 1;  
            } else if (second != o.second) {  
                return second < o.second ? -1 : 1;  
            } else {  
                return 0;  
            }  
        }  
          
        //�¶�����Ӧ����д����������  
        @Override  
        //The hashCode() method is used by the HashPartitioner (the default partitioner in MapReduce)  
        public int hashCode() {  
            return first * 157 + second;  
        }  
        @Override  
        public boolean equals(Object right) {  
            if (right == null)  
                return false;  
            if (this == right)  
                return true;  
            if (right instanceof IntPair) {  
                IntPair r = (IntPair) right;  
                return r.first == first && r.second == second;  
            } else {  
                return false;  
            }  
        }  
    }  
     /** 
       * ���������ࡣ����firstȷ��Partition�� 
       */  
      public static class FirstPartitioner extends Partitioner<IntPair,Text>{  
        @Override  
        public int getPartition(IntPair key, Text value,   
                                int numPartitions) {  
          return Math.abs(key.getFirst() * 127) % numPartitions;  
        }  
      }  
        
      /** 
       * ���麯���ࡣֻҪfirst��ͬ������ͬһ���顣 
       */  
    /*//��һ�ַ�����ʵ�ֽӿ�RawComparator 
    public static class GroupingComparator implements RawComparator<IntPair> { 
        @Override 
        public int compare(IntPair o1, IntPair o2) { 
            int l = o1.getFirst(); 
            int r = o2.getFirst(); 
            return l == r ? 0 : (l < r ? -1 : 1); 
        } 
        @Override 
        //һ���ֽ�һ���ֽڵıȣ�ֱ���ҵ�һ������ͬ���ֽڣ�Ȼ�������ֽڵĴ�С��Ϊ�����ֽ����Ĵ�С�ȽϽ���� 
        public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2){ 
            // TODO Auto-generated method stub 
             return WritableComparator.compareBytes(b1, s1, Integer.SIZE/8,  
                     b2, s2, Integer.SIZE/8); 
        } 
    }*/  
    //�ڶ��ַ������̳�WritableComparator  
    public static class GroupingComparator extends WritableComparator {  
          protected GroupingComparator() {  
            super(IntPair.class, true);  
          }  
          @Override  
          //Compare two WritableComparables.  
          public int compare(WritableComparable w1, WritableComparable w2) {  
            IntPair ip1 = (IntPair) w1;  
            IntPair ip2 = (IntPair) w2;  
            int l = ip1.getFirst();  
            int r = ip2.getFirst();  
            return l == r ? 0 : (l < r ? -1 : 1);  
          }  
        }  
      
          
    // �Զ���map  
    public static class Map extends  
            Mapper<LongWritable, Text, IntPair, Text> {  
        IntPair intkey = new IntPair();  
        public void map(LongWritable key, Text value, Context context)  
                throws IOException, InterruptedException {  
            String line = value.toString();  
            int left = 0;  
            int right = 0;  
			String[] colValue = line.split("\\t");
			left = Integer.parseInt(colValue[0].substring(0, 4));
			right = Integer.parseInt(colValue[1]);
			intkey.set(left, right);
			context.write(intkey, value);
        }  
    }  
    // �Զ���reduce  
    //  
    public static class Reduce extends  
            Reducer<IntPair, Text, Text, Text> {  
    	Text date = new Text();
    	Text temp = new Text();
        public void reduce(IntPair key, Iterable0<Text> values,  
                Context context) throws IOException, InterruptedException {  
        	for (Text val : values) {
        		String[] raw = val.toString().split("\\t");
            	date.set(raw[0]);
            	temp.set(raw[1]);
                context.write(date, temp);  
            }  
        }  
    }  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {  
        // TODO Auto-generated method stub  
        // ��ȡhadoop����  
        Configuration conf = new Configuration();  
        // ʵ����һ����ҵ  
        Job job = new Job(conf, "secondarysort");  
        job.setJarByClass(Demo_02.class);  
        // Mapper����  
        job.setMapperClass(Map.class);  
        // ������ҪCombiner���ͣ���ΪCombiner���������<Text, IntWritable>��Reduce����������<IntPair, IntWritable>������  
        //job.setCombinerClass(Reduce.class);  
        // Reducer����  
        job.setReducerClass(Reduce.class);  
        // ��������  
        job.setPartitionerClass(FirstPartitioner.class);  
        // ���麯��  
        job.setGroupingComparatorClass(GroupingComparator.class);  
          
        // map ���Key������  
        job.setMapOutputKeyClass(IntPair.class);  
        // map���Value������  
        job.setMapOutputValueClass(Text.class);  
        // rduce���Key�����ͣ���Text����Ϊʹ�õ�OutputFormatClass��TextOutputFormat  
        job.setOutputKeyClass(Text.class);  
        // rduce���Value������  
        job.setOutputValueClass(Text.class);  
          
        // ����������ݼ��ָ��С���ݿ�splites��ͬʱ�ṩһ��RecordReder��ʵ�֡�  
        job.setInputFormatClass(TextInputFormat.class);  
        // �ṩһ��RecordWriter��ʵ�֣��������������  
        job.setOutputFormatClass(TextOutputFormat.class);  
          
        // ����hdfs·��  
        FileInputFormat.setInputPaths(job, new Path(args[0]));  
        // ���hdfs·��  
        FileOutputFormat.setOutputPath(job, new Path(args[1]));  
        // �ύjob  
        System.exit(job.waitForCompletion(true) ? 0 : 1);  
    }  
}  
