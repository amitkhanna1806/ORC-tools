///**
// * Created by amit.khanna on 5/11/18.
// */
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileStatus;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hive.ql.io.orc.OrcFile;
//import org.apache.hadoop.hive.ql.io.orc.Reader;
//import org.apache.hadoop.hive.ql.io.orc.RecordReader;
//import org.apache.hadoop.hive.serde2.objectinspector.StructField;
//import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
//import org.apache.orc.OrcProto;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ORCReader {
//
//
//  public ORCReader(String[] args) throws IOException {
//    Configuration conf =new Configuration();
//    conf.set("inputdir", args[0]);
//    conf.set("columnName", args[1]);
//    conf.set("columnValue", args[2]);
//    FileSystem fs = FileSystem.get(conf);
//    FileStatus[] fileStatus = fs.listStatus(new Path(conf.get("inputdir")));
//
//    for (FileStatus status : fileStatus) {
//      if (status.getPath().toString().contains(".orc")) {
//        System.out.println("reading " + status.getPath().toString());
//      System.out.println(FileSystem.get(conf));
//      Path path = new Path(status.getPath().toString());
//
//      Reader orcReader = OrcFile.createReader(FileSystem.get(conf),path);
//      System.out.println("number of rows is" + orcReader.getNumberOfRows());
//      StructObjectInspector inspector = (StructObjectInspector)orcReader.getObjectInspector();
//      int field_index = -1;
//      int z=1;
//        for (StructField structField : inspector.getAllStructFieldRefs()) {
//          if((structField.getFieldName().equals(conf.get("columnName")))) {
//            System.out.println("type name is " + structField.getFieldObjectInspector().getTypeName());
//            field_index = z;
//            break;
//          }
//          z++;
//        }
//        int columnValue = Integer.valueOf(conf.get("columnValue"));
//            System.out.println("column value is "+columnValue);
//        if(field_index==-1){
//          System.out.println("wrong field name");
//          return;
//        }
//        System.out.println("field index is " + field_index);
//        System.out.println("File min is " + orcReader.getOrcProtoFileStatistics().get(field_index).getIntStatistics().getMinimum());
//        System.out.println("File max is " + orcReader.getOrcProtoFileStatistics().get(field_index).getIntStatistics().getMaximum());
//
//        if ((columnValue <= orcReader.getOrcProtoFileStatistics().get(field_index).getIntStatistics().getMaximum()) && (columnValue >= orcReader.getOrcProtoFileStatistics().get(field_index).getIntStatistics().getMinimum())){
//
//          System.out.println("can be in file "+ status.getPath().toString());
//          int h =0;
//          long start;
//          long end = 0;
//          for (OrcProto.StripeStatistics stripeStatistics : orcReader.getOrcProtoStripeStatistics()) {
//              System.out.println("min under stripe is " + stripeStatistics.getColStats(field_index).getIntStatistics().getMinimum());
//              System.out.println("max under stripe is " + stripeStatistics.getColStats(field_index).getIntStatistics().getMinimum());
//            start=end;
//            end=end+orcReader.getFileTail().getFooter().getStripes(h).getNumberOfRows();
//            if (columnValue <= stripeStatistics.getColStats(field_index).getIntStatistics().getMaximum() && columnValue >= stripeStatistics.getColStats(field_index).getIntStatistics().getMinimum()) {
//              System.out.println(columnValue + " can be found in " + h + "th stripe reading it ");
//              read_stripe(orcReader, start, end);
//            }
//            h++;
//          }
//        }
//        else{
//          System.out.println("not present in file " + status.getPath().toString());
//          continue;
//        }
//      }
//    }
//  }
//  public static void read_stripe(Reader orcReader, long start, long end) throws IOException {
//
//    StructObjectInspector inspector = (StructObjectInspector)orcReader.getObjectInspector();
//    RecordReader recordReader = orcReader.rows();
//    Object row = null;
//    recordReader.seekToRow(start);
//    System.out.println(" Reading stripe from rows " + start + " to " + end);
//    while(recordReader.hasNext())
//    {
//      row = recordReader.next(row);
//      List value_lst = inspector.getStructFieldsDataAsList(row);
//      StringBuilder builder = new StringBuilder();
//      for(Object field : value_lst) {
//        if(field != null)
//          builder.append(field.toString());
//        builder.append('\t');
//      }
//      System.out.println(builder.toString());
//      if(recordReader.getRowNumber()>=end)
//        break;
//    }
//  }
//  public static void main(String[] args) throws Exception {
//    //arg-> input file, column for where clause, column value.
//    new ORCReader(args);
//  }
//}
//
