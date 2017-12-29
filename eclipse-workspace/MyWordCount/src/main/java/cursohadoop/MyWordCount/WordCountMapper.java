package cursohadoop.MyWordCount;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

public class WordCountMapper extends Mapper<LongWritable , Text, Text, IntWritable> {
	@Override
	public void map(LongWritable key,Text Value, Context ctx)
		throws IOException, InterruptedException {
		Matcher matcher = pat.matcher(Value.toString());
		while (matcher.find() ) {
			word.set(matcher.group().toLowerCase());
			ctx.write(word, one);
		}
		}
	
  private Text word = new Text();
  private final static IntWritable one = new IntWritable(1);
  private Pattern pat = Pattern.compile("\\b[a-zA-Z\\u00C0-\\uFFFF]+\\b"); 

}
