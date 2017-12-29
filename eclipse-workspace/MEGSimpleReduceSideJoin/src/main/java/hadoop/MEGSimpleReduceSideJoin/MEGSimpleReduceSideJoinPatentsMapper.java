package hadoop.MEGSimpleReduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;




public class MEGSimpleReduceSideJoinPatentsMapper extends Mapper<Text,Text,LongWritable,MEGSimpleReduceSideJoinText> {
	
	 @Override
	 public void map(Text key,Text value,Context ctxt) throws IOException, InterruptedException {
		 String[] valores=value.toString().split(",");
		 
		 MEGSimpleReduceSideJoinText valor= new MEGSimpleReduceSideJoinText(new Text("\"ncitas\""),new Text (String.valueOf(valores.length)));
		 Long clave=Long.valueOf(key.toString());
		 
		
		 ctxt.write(new LongWritable(clave), valor);
	 }

}