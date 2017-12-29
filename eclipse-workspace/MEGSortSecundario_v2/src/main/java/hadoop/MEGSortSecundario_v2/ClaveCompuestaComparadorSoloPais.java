package hadoop.MEGSortSecundario_v2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public  class ClaveCompuestaComparadorSoloPais extends WritableComparator {
	   protected ClaveCompuestaComparadorSoloPais() {
	      super(PaisAnho.class, true);
	   }
	   @SuppressWarnings("rawtypes")
	   @Override
	   public int compare(WritableComparable w1, WritableComparable w2) {
	      PaisAnho pa1 = (PaisAnho) w1;
	      PaisAnho pa2 = (PaisAnho) w2;
	      int cmp = pa1.getPais().compareTo(pa2.getPais());
	      /* En esta segunda version solo se agrupa por pais */
	      /*if (cmp == 0) {
	         return pa1.getAnho().compareTo(pa2.getAnho());*/
	   
	   return cmp;
	}
	}
