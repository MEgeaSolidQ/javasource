package hadoop.MEGSimpleReduceSideJoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;


public class MEGSimpleReduceSideJoinText implements Writable {
    public MEGSimpleReduceSideJoinText() {
        set(new Text(), new Text());
     }
     public MEGSimpleReduceSideJoinText(Text tipo, Text valor) {
        set(tipo, valor);
     }
     public void set(Text tipo, Text valor) {
        this.tipo = tipo;
        this.valor = valor;
     }
     public Text gettipo() {
        return tipo;
     }
     public Text getvalor() {
        return valor;
     }
     @Override
     public void readFields(DataInput in) throws IOException {
        tipo.readFields(in);
        valor.readFields(in);
     }
     @Override
     public void write(DataOutput out) throws IOException {
        tipo.write(out);
        valor.write(out);
     }
  
     @Override
     public int hashCode() {
        return tipo.hashCode() * 163 + valor.hashCode();
     }              
     @Override
     public boolean equals(Object o) {
        if (o instanceof MEGSimpleReduceSideJoinText) {
           MEGSimpleReduceSideJoinText pa = (MEGSimpleReduceSideJoinText) o;
           return tipo.equals(pa.tipo) && valor.equals(pa.valor);
        }
        return false;
     }
     @Override
     public String toString() {
        return "["+tipo+", "+valor+"]";
     }
     private Text tipo;
     private Text valor;
 }