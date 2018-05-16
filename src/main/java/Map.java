import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.orc.*;
import org.apache.orc.mapred.OrcStruct;

public class Map extends Mapper<NullWritable, OrcStruct, NullWritable, OrcStruct>
{
  private TypeDescription schema =
      TypeDescription.fromString("struct<values:string>");
  private OrcStruct pair = (OrcStruct) OrcStruct.createValue(schema);

  public void map(NullWritable key, OrcStruct value,
                  Mapper<NullWritable, OrcStruct, NullWritable, OrcStruct>.Context context )
      throws IOException, InterruptedException {
    pair.setFieldValue(0, new Text(value.toString()));
    context.write(NullWritable.get(),pair);
  }
}