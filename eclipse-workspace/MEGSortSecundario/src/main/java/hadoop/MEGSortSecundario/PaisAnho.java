package hadoop.MEGSortSecundario;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public  class PaisAnho implements WritableComparable<PaisAnho> {
    public PaisAnho() {
       set(new Text(), new Text());
    }
    public PaisAnho(Text pais, Text anho) {
       set(pais, anho);
    }
    public void set(Text pais, Text anho) {
       this.pais = pais;
       this.anho = anho;
    }
    public Text getPais() {
       return pais;
    }
    public Text getAnho() {
       return anho;
    }
    @Override
    public void readFields(DataInput in) throws IOException {
       pais.readFields(in);
       anho.readFields(in);
    }
    @Override
    public void write(DataOutput out) throws IOException {
       pais.write(out);
       anho.write(out);
    }
    @Override
    public int compareTo(PaisAnho o) {
       int cmp = pais.compareTo(o.pais);
       if (cmp != 0) {
          return cmp;
       }
       return anho.compareTo(o.anho);
    }
    @Override
    public int hashCode() {
       return pais.hashCode() * 163 + anho.hashCode();
    }              
    @Override
    public boolean equals(Object o) {
       if (o instanceof PaisAnho) {
          PaisAnho pa = (PaisAnho) o;
          return pais.equals(pa.pais) && anho.equals(pa.anho);
       }
       return false;
    }
    @Override
    public String toString() {
       return "["+pais+", "+anho+"]";
    }
    private Text pais;
    private Text anho;
}