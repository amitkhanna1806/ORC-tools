//import org.apache.thrift.TBase;
//import org.apache.thrift.TException;
//import org.apache.thrift.TFieldIdEnum;
//import org.apache.thrift.protocol.TProtocol;
//
///**
// * Created by amit.khanna on 6/4/18.
// */
//public class thriftclass implements TBase {
//
//  private final String x;    // x-coordinate
//  private final String y;    // y-coordinate
//
//  // point initialized from parameters
//  public thriftclass(String x, String y) {
//    this.x = x;
//    this.y = y;
//  }
//
//  // accessor methods
//  public String x() { return x; }
//  public String y() { return y; }
//
//  // return a string representation of this point
//  public String toString() {
//    return "(" + x + ", " + y + ")";
//  }
//
//  @Override
//  public TFieldIdEnum fieldForId(int i) {
//    return null;
//  }
//
//  @Override
//  public boolean isSet(TFieldIdEnum tFieldIdEnum) {
//    return false;
//  }
//
//  @Override
//  public Object getFieldValue(TFieldIdEnum tFieldIdEnum) {
//    return null;
//  }
//
//  @Override
//  public void setFieldValue(TFieldIdEnum tFieldIdEnum, Object o) {
//
//  }
//
//  @Override
//  public TBase deepCopy() {
//    return null;
//  }
//
//  @Override
//  public void clear() {
//
//  }
//
//  @Override
//  public int compareTo(Object o) {
//    return 0;
//  }
//
//  @Override
//  public void read(TProtocol tProtocol) throws TException {
//
//  }
//
//  @Override
//  public void write(TProtocol tProtocol) throws TException {
//
//  }
//}
