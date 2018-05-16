/**
 * Created by amit.khanna on 5/11/18.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javolution.text.TextBuilder;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.orc.TypeDescription;
import org.apache.orc.mapred.OrcList;
import org.apache.orc.mapred.OrcStruct;

public class Reduce extends org.apache.hadoop.mapreduce.Reducer implements Reducer<Text,OrcStruct,Text,OrcStruct>
{

  private TypeDescription schema =
      TypeDescription.fromString("struct<key:string,values:string>");
  private OrcStruct pair = (OrcStruct) OrcStruct.createValue(schema);
  private List<OrcStruct> value =new ArrayList<>();
  private String values = null;


  public void reduce(Text key, Iterator<OrcStruct> iterator, OutputCollector<Text, OrcStruct> output,
                     Reporter reporter) throws IOException
  {
    while (iterator.hasNext()) {
      value.add(iterator.next());
    }
    pair.setFieldValue(0, key);
    pair.setFieldValue(1, (Text) value);
    output.collect(key, pair);
  }

  @Override
  public void close() throws IOException {

  }

  @Override
  public void configure(JobConf jobConf) {

  }
}