package hadoop.MEGSortSecundario_v2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;



public class MEGSortSecundarioReducer extends Reducer<PaisAnho,IntWritable,Text,Text> {
	enum PaisesConPatentes {
		 menos_de_5, mas_de_5
	 }
	
	Map<String, String> codigoDescPaises;
	
	@Override
	protected void setup(org.apache.hadoop.mapreduce.Reducer<PaisAnho,IntWritable,Text,Text>.Context ctxt) throws IOException, InterruptedException  {
	      
	      BufferedReader br= new BufferedReader(new FileReader("/usr/files/country_codes.txt"));
	      codigoDescPaises = new HashMap<String, String>();
	      String line="";
	      while ((line=br.readLine())!=null)
	      {
	    	  String[] p = line.split(",");
	    	  codigoDescPaises.put(p[0], p[1]);
	      }

	}
	
	@Override
	public void reduce (PaisAnho key, Iterable<IntWritable> values, Context ctxt ) throws IOException, InterruptedException  {
		String lista="";
		int counter=0;
		for (IntWritable i :values ) {
			lista+=key.getAnho().toString() + "->"+String.valueOf(i)+";";
			counter++;
			
		}
		if(counter<5) { ctxt.getCounter(PaisesConPatentes.menos_de_5).increment(1);}
		else {ctxt.getCounter(PaisesConPatentes.mas_de_5).increment(1); }
			
		ctxt.write( new Text( key.getPais() + "-" +codigoDescPaises.get(key.getPais().toString())) , new Text(lista));
	}
}