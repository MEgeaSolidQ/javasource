package hadoop.MEGSimpleReduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MEGSimpleReduceSideJoinReducer  extends Reducer<LongWritable,MEGSimpleReduceSideJoinText,LongWritable,Text> {
	@Override
	public void reduce (LongWritable key, Iterable<MEGSimpleReduceSideJoinText> values, Context ctxt ) throws IOException, InterruptedException  {
		String pais="";
		String citas="";
		for (MEGSimpleReduceSideJoinText valor :values ) {
			if (valor.gettipo().toString() =="\"pais\"") 
			{
				pais+=valor.getvalor().toString();
			}
			if (valor.gettipo().toString() =="\"ncitas\"") {
				citas+=valor.getvalor().toString();
				
			}
			
			
		}
		if (pais=="") pais="No Disponible";
		String resultado="Pais : [" +pais +"], Citas :[ "+ citas+"]";
		ctxt.write(key, new Text(resultado));
	}

}
