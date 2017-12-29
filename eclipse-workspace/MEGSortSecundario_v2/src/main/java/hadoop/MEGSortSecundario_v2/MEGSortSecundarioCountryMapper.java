package hadoop.MEGSortSecundario_v2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MEGSortSecundarioCountryMapper  extends Mapper<Text,Text,PaisAnho,IntWritable> {
	
	 @Override
	 public void map(Text key,Text value,Context ctxt) throws IOException, InterruptedException {
		 // Este leerá el fichero Country_code.txt 
		 PaisAnho pa = new PaisAnho();
		 pa.set(key,value,new Text("-1") );
		 ctxt.write(pa, new IntWritable(1));
	 }

}
