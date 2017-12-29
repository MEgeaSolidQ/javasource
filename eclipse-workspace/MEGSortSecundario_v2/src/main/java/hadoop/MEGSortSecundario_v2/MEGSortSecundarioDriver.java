package hadoop.MEGSortSecundario_v2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class MEGSortSecundarioDriver extends  Configured  implements  Tool  {
	public  int  run ( String [ ]  arg0 )  throws  Exception  {
		 if   ( arg0 . length  !=  2)  {
			 			System.err.printf( "Usar : %s [ ops ] <entrada> <salida >\n" ,
			 							getClass ( ) . getSimpleName ( ) ) ;
			 		ToolRunner.printGenericCommandUsage (System.err ) ;
			 		return -1;
		 }
		 
				Configuration  conf  =  getConf() ;
				conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "\t");
				conf.set(TextOutputFormat.SEPERATOR, "\t");
				
				Job  job  =  Job.getInstance ( conf ) ;

				job.setInputFormatClass(SequenceFileInputFormat.class);
				job.setOutputFormatClass(TextOutputFormat.class);
				
				job.setJobName ( "OrdenacionSecundaria v2" ) ;
				job.setJarByClass ( getClass ( ) ) ;
				
				FileInputFormat.addInputPath ( job ,  new  Path ( arg0 [ 0 ] ) ) ;
				FileOutputFormat.setOutputPath ( job ,  new  Path ( arg0 [ 1 ] ) );
				//FileOutputFormat.setCompressOutput(job,true);
				//FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
				
				job.setMapOutputKeyClass(PaisAnho.class);
				job.setMapOutputValueClass(IntWritable.class);
				
				job.setOutputKeyClass ( Text.class ) ;
				job.setOutputValueClass ( Text.class ) ;
				job.setNumReduceTasks( 1 ) ;
				
				job.setSortComparatorClass(ClaveCompuestaComparator.class);
				job.setPartitionerClass(PaisPartitioner.class);
				job.setGroupingComparatorClass(ClaveCompuestaComparadorSoloPais.class);
				
				job.setReducerClass(MEGSortSecundarioReducer.class ) ;
				job.setMapperClass (MEGSortSecundarioMapper.class ) ;
				job.setCombinerClass (MEGSortSecunadrioAditionalReducer.class ) ;
				
		return  ( job.waitForCompletion ( true )  ?  0  :-1);
		}
		
		public   static  void  main( String [ ]  args )  throws  Exception  {
			int exitCode  =  ToolRunner.run (new  MEGSortSecundarioDriver ( ) ,  args ) ;
			System.exit ( exitCode ) ;
		}

}
