import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
//import org.apache.hadoop.hive.ql.io.orc.Reader;
//import org.apache.hadoop.hive.ql.io.orc.RecordReader;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.MapReduceBase;
//import org.apache.hadoop.mapred.Mapper;
//import org.apache.hadoop.mapred.OutputCollector;
//import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.orc.*;
import org.apache.orc.mapred.OrcList;
//import org.apache.orc.mapred.OrcStruct;

public class Map extends org.apache.hadoop.mapreduce.Mapper{

  private final static IntWritable one = new IntWritable(1);
  private Text word = new Text();
  TypeInfo typeInfo;
  // createValue creates the correct value type for the schema
  // get a handle to the list of ints

  private final NullWritable nada = NullWritable.get();

  public void map(NullWritable key, OrcStruct value, Context output, Reporter reporter)
      throws IOException, InterruptedException {


    ObjectInspector inspector = value.createObjectInspector(typeInfo);
    StructObjectInspector structObjectInspector = (StructObjectInspector) inspector;
//    structObjectInspector.getStructFieldRef()

    List columnValues = structObjectInspector.getStructFieldsDataAsList(value);

    StringBuilder builder = new StringBuilder();
    //iterate over the fields
    //Also fields can be null if a null was passed as the input field when processing wrote this file
    if(columnValues.contains("8bed9d46396a4f63ab0f4634e8418a15"))
    System.out.println(columnValues.get(0));
    output.write(nada,columnValues.toString());

//    DateWritable eventDate =(DateWritable) columnValues.get(1);

    // <Your custom logic with the key and value pairs>
//    v.set(filename + "  "+ eventDate.toString())
//    context.write(NullWritable.get(), v);
//    String line = value.toString();
//    StringTokenizer tokenizer = new StringTokenizer(line);
//    while (tokenizer.hasMoreTokens())
//    {
//      word.set(tokenizer.nextToken());
//      output.collect(word, one);
//    }
  }
}