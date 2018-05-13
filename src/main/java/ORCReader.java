/**
 * Created by amit.khanna on 5/11/18.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.Reader;
import org.apache.hadoop.hive.ql.io.orc.RecordReader;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

import java.io.IOException;
import java.util.List;

public class ORCReader {


  public ORCReader(String[] args) throws IOException {
    Configuration conf =new Configuration();
    OrcFile.ReaderOptions readerOptions = new OrcFile.ReaderOptions(conf);
    System.out.println("filesystem is " + readerOptions.getFilesystem());


//    conf.set("mapreduce.job.inputformat.class", "org.apache.hadoop.hive.ql.io.orc.OrcNewInputFormat");
    conf.set("mapreduce.input.fileinputformat.inputdir", args[0]);
//    conf.set("mapreduce.job.queuename", "merlin-supply");


    System.out.println(FileSystem.get(conf));

    Reader orcReader = OrcFile.createReader(FileSystem.get(conf),new Path(conf.get("mapreduce.input.fileinputformat.inputdir")));
    System.out.println("number of rows is" + orcReader.getNumberOfRows());

    StructObjectInspector inspector = (StructObjectInspector)orcReader.getObjectInspector();
    RecordReader recordReader = orcReader.rows();
    System.out.println("Meta is " +orcReader.getMetadataKeys());

    System.out.println("content length is " + orcReader.getContentLength());
    List fields = inspector.getAllStructFieldRefs();

    System.out.println("tail is " + orcReader.getFileTail());
    for(int i = 0; i < fields.size(); ++i) {
      System.out.print(((StructField)fields.get(i)).getFieldObjectInspector().getTypeName() + '\t');
    }
    Object row = null;
    while(recordReader.hasNext())
    {
      row = recordReader.next(row);
      List value_lst = inspector.getStructFieldsDataAsList(row);
      StringBuilder builder = new StringBuilder();
      //iterate over the fields
      //Also fields can be null if a null was passed as the input field when processing wrote this file
      for(Object field : value_lst) {
        if(field != null)
          builder.append(field.toString());
        builder.append('\t');
      }
      //this writes out the row as it would be if this were a Text tab seperated file
      //System.out.println(builder.toString());
    }

  }
public static void main(String[] args) throws Exception {
  new ORCReader(args);
}
}
