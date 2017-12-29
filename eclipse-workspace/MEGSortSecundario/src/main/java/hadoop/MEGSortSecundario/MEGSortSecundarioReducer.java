package hadoop.MEGSortSecundario;


import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MEGSortSecundarioReducer extends Reducer<PaisAnho,IntWritable,PaisAnho,IntWritable> {
	@Override
	public void reduce (PaisAnho key, Iterable<IntWritable> values, Context ctxt ) throws IOException, InterruptedException  {
		int acum=0;
		for (IntWritable i :values ) {
			acum+=i.get();
		}
		ctxt.write(key, new IntWritable(acum));
	}

}
