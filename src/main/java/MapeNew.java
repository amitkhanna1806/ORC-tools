import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.Reporter;
import org.apache.orc.*;
import org.apache.orc.mapred.OrcList;
import org.apache.orc.mapred.OrcStruct;

public class MapeNew extends Mapper<NullWritable, OrcStruct, NullWritable, OrcStruct>
{
  private TypeDescription schema =
      TypeDescription.fromString("struct<values:string>");
  private OrcStruct pair = (OrcStruct) OrcStruct.createValue(schema);
  private List<OrcStruct> value =new ArrayList<>();

  private final NullWritable nada = NullWritable.get();

  public void map(NullWritable key, OrcStruct value,
                  Mapper<NullWritable, OrcStruct, NullWritable, OrcStruct>.Context context )
      throws IOException, InterruptedException {
    pair.setFieldValue(0, new Text(value.toString()));
    context.write(NullWritable.get(),pair);
  }
}