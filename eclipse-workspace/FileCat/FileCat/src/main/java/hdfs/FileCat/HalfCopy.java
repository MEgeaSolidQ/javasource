package hdfs.FileCat;



import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.util.Progressable;
import org.mortbay.util.IO;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;

public class HalfCopy {
	public static void main(String[] args) throws Exception {
	    String localSrc = args[0];
	    String dst = args[1];
	    
	    
	   
	   
	    Configuration conf = new Configuration();
	    FileSystem fs = FileSystem.get(URI.create(dst), conf);
	    FSDataInputStream in=fs.open(new Path(localSrc));
	    FileStatus fst=fs.getFileStatus(new Path(localSrc));
	    long lng =fst.getLen()/2;
	    in.seek(lng);
	    FSDataOutputStream out = fs.create(new Path(dst), new Progressable() {
	      public void progress() {
	        System.out.print(".");
	      }
	    });
	    
	    IO.copy(in, out);
	    out.hflush();
	    out.hsync();
	    out.close();
	    System.out.print("\r\n Half Copied \r\n");
	}

}
