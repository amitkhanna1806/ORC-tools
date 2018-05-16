import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.orc.mapred.OrcStruct;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.orc.mapreduce.OrcInputFormat;
import org.apache.orc.mapreduce.OrcOutputFormat;


public class ORCDriver extends Configured implements Tool {
  public static void main(String[] args) throws Exception {

    int res =  ToolRunner.run(new Configuration(), new ORCDriver(),args);
    System.exit(res);
  }


  @Override
  public int run(String[] arg0) throws Exception {
    // TODO Auto-generated method stub

    Configuration conf = getConf();
    conf.set("mapreduce.job.inputformat.class", "org.apache.orc.mapreduce.OrcInputFormat");
    conf.set("mapreduce.job.outputformat.class", "org.apache.orc.mapreduce.OrcOutputFormat");
    conf.set("mapreduce.input.fileinputformat.inputdir", arg0[0]);
    conf.set("mapreduce.output.fileinputformat.outputdir", arg0[1]);
    conf.set("mapreduce.map.tasks", "25");
    conf.set("orc.mapred.output.schema","struct<values:string>");

    Job job = Job.getInstance(conf,"Read ORC Files");
    job.setJarByClass(ORCDriver.class);
    job.setMapperClass(MapNew.class);

    job.setMapOutputKeyClass(NullWritable.class);
    job.setMapOutputValueClass(OrcStruct.class);
    job.setOutputKeyClass(NullWritable.class);
    job.setOutputValueClass(OrcStruct.class);

    job.setOutputFormatClass(OrcOutputFormat.class);
    Path path =new Path(arg0[0]);
    MultipleInputs.addInputPath(job, path, OrcInputFormat.class);

    FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
    job.setNumReduceTasks(0);

    System.exit(job.waitForCompletion(true) ?0:1);

    return 0;
  }


}