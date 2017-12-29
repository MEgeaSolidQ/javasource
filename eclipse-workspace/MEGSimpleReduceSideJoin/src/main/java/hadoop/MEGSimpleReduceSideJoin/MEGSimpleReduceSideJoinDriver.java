package hadoop.MEGSimpleReduceSideJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MEGSimpleReduceSideJoinDriver  extends Configured implements Tool {

	   @Override
	   public int run(String[] args) throws Exception {   
	      /*
	       * Comprueba los parámetros de entrada
	      */
	      if (args.length != 3) {
	         System.err.printf("Usar: %s [opciones genéricas] <entrada_citas> <entrada_info> <directorio_salida>%n",
	            getClass().getSimpleName());
	         System.err.printf("Recuerda que el directorio de salida no puede existir");
	         ToolRunner.printGenericCommandUsage(System.err);
	         return -1;
	      }

	      /* Obtiene la configuración por defecto y modifica algún parámetro */
	      Configuration conf = getConf();

	      /* Obtiene un job a partir de la configuración actual */
	      
		Job job = new Job(conf);
	      job.setJobName("Lista de citas");
	      /* Fija el jar del trabajo a partir de la clase del objeto actual */
	      job.setJarByClass(getClass());

	      /* Paths de entrada y salida */
	      Path entradaCitas = new Path(args[0]);
	      Path entradaInfo = new Path(args[1]);
	      Path salida = new Path(args[2]);

	      /* Como tenemos dos entradas diferentes (con dos mappers diferentes)
	       * usamos MultipleInputs, indicando en path de entrada, el formato
	       * de la entrada y el mapper a usar para cada una */
	      MultipleInputs.addInputPath(job, entradaCitas, KeyValueTextInputFormat.class, MEGSimpleReduceSideJoinPatentsMapper.class);
	      MultipleInputs.addInputPath(job, entradaInfo, SequenceFileInputFormat.class, MEGSimpleReduceSideJoinSequenceMapper.class);

	      /* Añadimos el path de salida */
	      FileOutputFormat.setOutputPath(job, salida);

	      /* Especifica el tipo de la clave y el valor de salida de los mappers.
	       * No es necesario si los tipos son iguales a los tipos de la salida final.
	       * Usamos LongWritable para el número de patente para que las ordene
	       * numéricamente */
	      job.setMapOutputKeyClass(LongWritable.class);
	      job.setMapOutputValueClass(Text.class);

	      /* Especifica el tipo de la clave y el valor de salida final */
	      job.setOutputKeyClass(LongWritable.class);
	      job.setOutputValueClass(Text.class);

	      /* Especifica el número de reducers */
	      job.setNumReduceTasks(1);

	      /* Especifica el reducer */
	      job.setReducerClass(MEGSimpleReduceSideJoinReducer.class);

	      return job.waitForCompletion(true) ? 0 : 1;
	   }

	   /**
	    * Usar hadoop jar SimpleReduceSideJoin.jar patentes_citas patentes_info dir_salida
	    *
	    * @param args
	    * patentes_citas patentes_info dir_salida
	    * @throws Exception
	   */
	   public static void main(String[] args) throws Exception {
	      int exitCode = ToolRunner.run(new MEGSimpleReduceSideJoinDriver(), args);
	      System.exit(exitCode);
	   }
	}