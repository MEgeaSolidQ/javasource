package hadoop.MEGCreateSequenceFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class MEGCreateSequenceFileDriver extends  Configured  implements  Tool  {
	public  int  run ( String [ ]  arg0 )  throws  Exception  {
		 if   ( arg0 . length  !=  2)  {
			 			System.err.printf( "Usar : %s [ ops ] <entrada> <salida >\n" ,
			 							getClass ( ) . getSimpleName ( ) ) ;
			 		ToolRunner.printGenericCommandUsage (System.err ) ;
			 		return -1;
		 }
		 
				Configuration  conf  =  getConf() ;
				conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
				
				Job  job  =  Job.getInstance ( conf ) ;
				job.setInputFormatClass(KeyValueTextInputFormat.class);
				job.setOutputFormatClass(SequenceFileOutputFormat.class);
				job.setJobName ( "Sequence File" ) ;
				
				job.setJarByClass ( getClass ( ) ) ;
				
				FileInputFormat.addInputPath ( job ,  new  Path ( arg0 [ 0 ] ) ) ;
				FileOutputFormat.setOutputPath ( job ,  new  Path ( arg0 [ 1 ] ) );
				job.setOutputKeyClass ( Text.class ) ;
				job.setOutputValueClass ( Text.class ) ;
				job.setNumReduceTasks( 0 ) ;
				
				job.setMapperClass (MEGCreateSequenceFileMapper.class ) ;
				
				
		return  ( job.waitForCompletion ( true )  ?  0  :-1);
		}
		public   static  void  main( String [ ]  args )  throws  Exception  {
			int exitCode  =  ToolRunner.run (new  MEGCreateSequenceFileDriver ( ) ,  args ) ;
			System.exit ( exitCode ) ;
		}

}
