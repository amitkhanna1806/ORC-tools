import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hive.ql.io.orc.OrcFile;
//import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
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
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.orc.*;
import org.apache.orc.mapred.OrcList;
import org.apache.orc.mapred.OrcStruct;

public class Map extends org.apache.hadoop.mapreduce.Mapper
{


  private final NullWritable nada = NullWritable.get();

  public void map(Text key, OrcStruct value,
                  OutputCollector<Text,OrcStruct> output)
      throws IOException, InterruptedException {
    output.collect((Text) value.getFieldValue(0),
        value);
  }
}