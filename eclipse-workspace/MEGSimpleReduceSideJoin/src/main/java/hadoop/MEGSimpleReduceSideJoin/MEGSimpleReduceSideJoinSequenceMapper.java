package hadoop.MEGSimpleReduceSideJoin;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;




public class MEGSimpleReduceSideJoinSequenceMapper  extends Mapper<Text,Text,LongWritable,MEGSimpleReduceSideJoinText>  {
	
	 @Override
	 public void map(Text key,Text value,Context ctxt) throws IOException, InterruptedException {
		 String[] valores=value.toString().split(",");
		 
		 MEGSimpleReduceSideJoinText valor= new MEGSimpleReduceSideJoinText(new Text("\"pais\""),new Text (key.toString()));
		 
		 Long clave=Long.valueOf(valores[0].toString());
		
		 ctxt.write(new LongWritable(clave),valor);
	 }

}