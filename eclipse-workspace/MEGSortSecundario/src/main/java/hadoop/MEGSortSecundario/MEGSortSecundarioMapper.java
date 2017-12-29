package hadoop.MEGSortSecundario;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MEGSortSecundarioMapper extends Mapper<Text,Text,PaisAnho,IntWritable> {
	
	 @Override
	 public void map(Text key,Text value,Context ctxt) throws IOException, InterruptedException {
		 String[] valores=value.toString().split(",");
		 PaisAnho pa = new PaisAnho();
		 pa.set(key, new Text(valores[1]));
		 ctxt.write(pa, new IntWritable(1));
	 }

}
