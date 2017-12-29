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
		String all ="";
		for (MEGSimpleReduceSideJoinText valor :values ) {
			if (valor.gettipo().toString().contains("pais")) 
			{
				pais+=valor.getvalor().toString();
			}
			if (valor.gettipo().toString().contains("cita")){
				citas+=valor.getvalor().toString();
				
			}
			
			//all+=valor.gettipo().toString()	 +"- " + valor.getvalor().toString();		
		}
		if (pais=="") pais="No Disponible";
		if (citas=="") citas="0";
		String resultado="Pais : [" +pais +"], Citas :[ "+ citas+"]" /*+ all*/;
		ctxt.write(key, new Text(resultado));
	}

}
