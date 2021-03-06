package hadoop.MEGcitnumberbypatentchained;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MEGcitnumberbypatentchainedReducer extends  Reducer<Text ,   Text ,  Text ,   Text> {
	@Override
	public  void  reduce ( Text  key ,   Iterable<Text>  values ,
	Context  ctxt )  throws  IOException ,   InterruptedException  {
	String  Acum = "";
	for   ( Text  value  :  values )  {
		Acum = Acum+  value.toString();
		if (value.toString()!="" && values.iterator().hasNext())
		  Acum= Acum +" ,";
	}
	ctxt.write ( key ,  new  Text (Acum) ) ;
	}


}
