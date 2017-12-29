package hadoop.MEGSortSecundario;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public  class ClaveCompuestaComparator extends WritableComparator {
	   protected ClaveCompuestaComparator() {
	      super(PaisAnho.class, true);
	   }
	   @SuppressWarnings("rawtypes")
	   @Override
	   public int compare(WritableComparable w1, WritableComparable w2) {
	      PaisAnho pa1 = (PaisAnho) w1;
	      PaisAnho pa2 = (PaisAnho) w2;
	      int cmp = pa1.getPais().compareTo(pa2.getPais());
	      if (cmp == 0) {
	         return pa1.getAnho().compareTo(pa2.getAnho());
	   }
	   return cmp;
	}
	}