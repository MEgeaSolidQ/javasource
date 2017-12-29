package cursohadoop.MyWordCount;



import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public  class  WordCountDriver
extends  Configured  implements  Tool  {
public  int  run ( String [ ]  arg0 )  throws  Exception  {
 if   ( arg0 . length  !=  2)  {
	 			System.err.printf( "Usar : %s [ ops ] <entrada> <salida >\n" ,
	 							getClass ( ) . getSimpleName ( ) ) ;
	 		ToolRunner.printGenericCommandUsage (System.err ) ;
	 		return -1;
 }
 
		Configuration  conf  =  getConf() ;
		Job  job  =  Job.getInstance ( conf ) ;
		job.setJobName ( "Word Count" ) ;
		job.setJarByClass ( getClass ( ) ) ;
		FileInputFormat.addInputPath ( job ,  new  Path ( arg0 [ 0 ] ) ) ;
		FileOutputFormat.setOutputPath ( job ,  new  Path ( arg0 [ 1 ] ) );
		job.setOutputKeyClass ( Text.class ) ;
		job.setOutputValueClass ( IntWritable.class ) ;
		job.setNumReduceTasks( 1 ) ;
		job.setReducerClass(WordCountReducer.class ) ;
		job.setMapperClass (WordCountMapper.class ) ;
		job.setCombinerClass (WordCountReducer.class ) ;
		
return  ( job.waitForCompletion ( true )  ?  0  :-1);
}
public   static  void  main( String [ ]  args )  throws  Exception  {
	int exitCode  =  ToolRunner.run (new  WordCountDriver ( ) ,  args ) ;
	System.exit ( exitCode ) ;
}
}