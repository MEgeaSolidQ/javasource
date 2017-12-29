package hadoop.MEGcitnumberbypatentchained;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MEGcitnumberbypatentchainedMapper extends Mapper<Text , Text, Text, Text> {
	@Override
	public void map(Text key,Text Value, Context ctx)
		throws IOException, InterruptedException {
		if (!key.toString().equals("\"CITING\"")) { 
		ctx.write(key, new Text(Value));
		 }
		}
	


}
