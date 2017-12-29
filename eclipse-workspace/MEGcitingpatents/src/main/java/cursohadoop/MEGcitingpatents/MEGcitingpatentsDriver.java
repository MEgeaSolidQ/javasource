package cursohadoop.MEGcitingpatents;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;






import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MEGcitingpatentsDriver extends  Configured  implements  Tool  {
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
				
				job.setJobName ( "Citing Patents" ) ;
				
				job.setJarByClass ( getClass ( ) ) ;
				
				FileInputFormat.addInputPath ( job ,  new  Path ( arg0 [ 0 ] ) ) ;
				FileOutputFormat.setOutputPath ( job ,  new  Path ( arg0 [ 1 ] ) );
				FileOutputFormat.setCompressOutput(job,true);
				FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
				job.setOutputKeyClass ( Text.class ) ;
				job.setOutputValueClass ( Text.class ) ;
				job.setNumReduceTasks( 1 ) ;
				job.setReducerClass(MEGcitingpatentsReducer.class ) ;
				job.setMapperClass (MEGcitingpatentsMapper.class ) ;
				job.setCombinerClass (MEGcitingpatentsReducer.class ) ;
				
		return  ( job.waitForCompletion ( true )  ?  0  :-1);
		}
		public   static  void  main( String [ ]  args )  throws  Exception  {
			int exitCode  =  ToolRunner.run (new  MEGcitingpatentsDriver ( ) ,  args ) ;
			System.exit ( exitCode ) ;
		}

}
