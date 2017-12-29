package hadoop.MEGSimpleReduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MEGSimpleReduceSideJoinReducer  extends Reducer<LongWritable,Text,LongWritable,Text> {
	@Override
	public void reduce (LongWritable key, Iterable<Text> values, Context ctxt ) throws IOException, InterruptedException  {
		String pais="";
		String citas="";
		for (Text valor :values ) {
			if (valor.toString().contains("pais"))
			{
				pais+=valor.toString().split(",")[1];
			}
			if (valor.toString().contains("ncitas")) {
				citas+=valor.toString().split(",")[1];
				
			}
			
			
		}
		if (pais=="") pais="No Disponible";
		String resultado="Pais : [" +pais +"], Citas :[ "+ citas+"]";
		ctxt.write(key, new Text(resultado));
	}

}
