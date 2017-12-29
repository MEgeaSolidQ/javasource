package hadoop.MEGcitnumberbypatentchained;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.chain.*;

import hadoop.MEGcitnumberbypatentchained.MEGcitnumberbypatentchainedDriver;
import hadoop.MEGcitnumberbypatentchained.MEGcitnumberbypatentchainedMapper;
import hadoop.MEGcitnumberbypatentchained.MEGcitnumberbypatentchainedReducer;

import hadoop.MEGcitnumberbypatentchained.MEGcitnumberbypatentchainedFinalMapper;

public class MEGcitnumberbypatentchainedDriver  extends  Configured  implements  Tool  {
	public  int  run ( String [ ]  arg0 )  throws  Exception  {
		 if   ( arg0 . length  !=  2)  {
			 			System.err.printf( "Usar : %s [ ops ] <entrada> <salida >\n" ,
			 							getClass ( ) . getSimpleName ( ) ) ;
			 		ToolRunner.printGenericCommandUsage (System.err ) ;
			 		return -1;
		 }
		 
				Configuration  conf  =  getConf() ;
				conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
				conf.set(TextOutputFormat.SEPERATOR, ",");
				
				Job  job  =  Job.getInstance ( conf ) ;
				job.setInputFormatClass(KeyValueTextInputFormat.class);
				job.setOutputFormatClass(TextOutputFormat.class);
				
				job.setJobName ( "Citing Patents" ) ;
				job.setJarByClass ( getClass ( ) ) ;
				
				FileInputFormat.addInputPath ( job ,  new  Path ( arg0 [ 0 ] ) ) ;
				FileOutputFormat.setOutputPath ( job ,  new  Path ( arg0 [ 1 ] ) );
				//FileOutputFormat.setCompressOutput(job,true);
				//FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
				
				
				ChainMapper.addMapper(job, MEGcitnumberbypatentchainedMapper.class, Text.class, Text.class, Text.class, Text.class, conf);
				Configuration  reducerConf = new Configuration(false);
				reducerConf.set(TextOutputFormat.SEPERATOR, ",");
				ChainReducer.setReducer(job, MEGcitnumberbypatentchainedReducer.class , Text.class, Text.class, Text.class, Text.class, reducerConf);
				ChainReducer.addMapper(job, MEGcitnumberbypatentchainedFinalMapper.class,  Text.class, Text.class, Text.class, IntWritable.class,reducerConf);
				
				
				
		return  ( job.waitForCompletion ( true )  ?  0  :-1);
		}
		public   static  void  main( String [ ]  args )  throws  Exception  {
			int exitCode  =  ToolRunner.run (new  MEGcitnumberbypatentchainedDriver ( ) ,  args ) ;
			System.exit ( exitCode ) ;
		}

}
