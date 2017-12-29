package hadoop.MEGcitnumberbypatentchained;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MEGcitnumberbypatentchainedFinalMapper extends Mapper<Text , Text, Text, IntWritable > {
	@Override
	public void map(Text key,Text Value, Context ctx)
		throws IOException, InterruptedException {
		int counter = 0;
		String valor="";
		if (Value.toString().length()>0 )
			valor=Value.toString()+",";
			// es una forma de contar las comas que al final son las patentes citadas
		counter+=valor.length()-valor.replace(",","").length();

		ctx.write (key,  new  IntWritable(counter) ) ;
		
		}

}
