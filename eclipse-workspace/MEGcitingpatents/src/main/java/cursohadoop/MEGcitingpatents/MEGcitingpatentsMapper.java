package cursohadoop.MEGcitingpatents;



import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MEGcitingpatentsMapper  extends Mapper<Text , Text, Text, Text> {
	@Override
	public void map(Text key,Text Value, Context ctx)
		throws IOException, InterruptedException {
		if (!key.toString().equals("\"CITING\"")) { 
		//ctx.write(key, new Text(Value));
		ctx.write( new Text(Value),key);
		 }
		}
	

}
