package hadoop.MEGCreateSequenceFile;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MEGCreateSequenceFileMapper  extends Mapper<Text , Text, Text, Text> {
	@Override
	public void map(Text key,Text Value, Context ctx)
		throws IOException, InterruptedException {
		
		String[] Campos= Value.toString().replace("\"","").split(",");
		
		if (!key.toString().equals("\"PATENT\"")) { 
			String Clave=Campos[3].toString();
			String Valor=key.toString()+","+Campos[0].toString();
			ctx.write(new Text(Clave), new Text(Valor));
		 }
		}

}
