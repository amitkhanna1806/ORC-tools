import com.google.common.collect.Lists;
//import com.sun.java.util.jar.pack.Package;
//import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import org.apache.hadoop.security.UserGroupInformation;
//import org.apache.lens.api.APIResult;
//import org.apache.lens.api.LensConf;
//import org.apache.lens.api.LensSessionHandle;
//import org.apache.lens.api.query.QueryHandle;
//import org.apache.lens.api.query.QueryHandleWithResultSet;
//import org.apache.lens.api.query.QueryResult;
//import org.apache.lens.api.session.UserSessionInfo;
//import org.apache.lens.client.LensClient;
//import org.apache.lens.client.LensClientConfig;
//import org.apache.lens.client.LensClientSingletonWrapper;
//import org.apache.lens.client.LensConnection;
//import org.apache.lens.client.LensStatement;
//import org.apache.lens.client.resultset.ResultSet;
//import org.datanucleus.sco.backed.ArrayList;
//import org.json.JSONObject;
//import org.mortbay.util.ajax.JSON;
import au.com.bytecode.opencsv.CSVReader;
import org.apache.http.entity.InputStreamEntity;
import org.jboss.netty.handler.codec.base64.Base64Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValue;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//import javax.ws.rs.core.Response;
//import java.io.IOException;
//import java.util.List;


/**
 * Created by amit.khanna on 5/17/18.
 */
public class LensQuery {

  //      Properties properties =new Properties();
//    properties.load(RtaServices.class.getClassLoader().getResourceAsStream("lens.properties"));
    public static void main(String[] args) throws Exception {
     // UserGroupInformation loginUserUGI = UserGroupInformation.createRemoteUser(args[0]);

        final Logger log = LoggerFactory.getLogger(LensQuery.class);

        System.out.println("user is " + System.getProperty("user.name"));
//        LensClient lensClient = LensClientSingletonWrapper.instance().getClient();
//        LensStatement lensStatement = new LensStatement(lensClient.getConnection());
//        String query = "cube select total_burn from rrcube where time_range_in(event_time,'2018-05-17-00','2018-05-17-01')";
//        lensStatement.executeQuery(query, true, "amitquery");
//        if (!lensStatement.wasQuerySuccessful()) {
//            throw new RuntimeException("Query execution failed");
//        }
//      System.out.println(loginUserUGI.getUserName());
//
//      for (String s : loginUserUGI.getGroupNames()) {
//        System.out.println(s);
//      }
      File file = new File("/Users/amit.khanna/IdeaProjects/dsr-tools/src/main/java/abc.csv");

      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String s1 ="";

      while (bufferedReader.readLine()!=null) {
        s1 = bufferedReader.readLine();
      }
      //CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(s1.getBytes())));
      CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
      String[] result = csvReader.readNext();
      String[] result2 = csvReader.readNext();
      String s2="";
      for (String s : result2) {
        //System.out.println(s);
        s2+=s;
      }
      if(s1.equals(s2)){
        System.out.println("same");
      }
      else
        System.out.println("diff");
      System.out.println("s1 is " + s1);
      System.out.println("s2 is " + s2);

      Config whitelistedUserConfig;
      ConfigValue configValue=ConfigValueFactory.fromAnyRef("xyz");
      //System.out.println(configValue.unwrapped().toString());
//        LensClientConfig lensClientConfig = new LensClientConfig();
//        lensClientConfig.setBaseUrl("http://lens.data.inmobi.com:8080/lensapi/");
//        lensClientConfig.setLensDatabase("yoda");
//        LensClient lensClient =new LensClient(lensClientConfig, "amit.khanna","password");
//        LensStatement lensStatement = new LensStatement(lensClient.getConnection());
//        System.out.println("connection open =" + lensClient.isConnectionOpen());
        //String query = "cube select rq_pump_experiment_ids, total_burn from rrcube where time_range_in(event_time,'2018-05-22-00','2018-05-22-01')";
        //System.out.println(lensStatement.executeQuery(query, true, "amitquery"));
     // QueryHandle handle = lensStatement.executeQuery(query,true,"",new LensConf());
//      System.out.println("result is " + result.getResult().toString());
//      System.out.println("pretty result is " + result.getResult().toPrettyString());
      //  System.out.println("handle is "+handle);
      //System.out.println("result final is " + lensStatement.getHttpResultSet(lensClient.getQueryDetails(handle)));
     // ResultSet queryResult = lensClient.getHttpResultSet(handle);
//      while (queryResult.next()) {
//        String row[] = queryResult.getRow();
//          JSONObject jsonObject = (JSONObject) JSON.parse(row[0]);
//          String algo = (String) jsonObject.get("encryption-algorithm");
//          String data = (String) jsonObject.get("encrypted-data");
//        }
//        String app = row[0];
//        if(app!=null && !app.trim().isEmpty())
//          lensApps.add(app);
      }

//        LensConf lensConf =new LensConf();
//        lensConf.addProperty("username", "amit.khanna");
//        lensConf.addProperty("password","somepass");
//        lensConf.addProperty("lensbaseurl","http://lens.data.inmobi.com:8080/lensapi/");
//        lensConf.addProperty("db","yoda");
//        lensClient.setDatabase("yoda");
//        lensClient.setConnectionParam("lensbaseurl","http://lens.data.inmobi.com:8080/lensapi/");
//        LensConnection lensConnection = lensClient.getConnection();
//        UserSessionInfo userSessionInfo = new UserSessionInfo();
//        LensSessionHandle lensSessionHandle = lensConnection.getSessionHandle();
//        userSessionInfo.setUserName("lens-r");
//        lensClient.executeQueryAsynch("cube select total_burn from rrcube where time_range_in(event_time,'2018-05-17-00','2018-05-17-01')","burn");
//        System.out.println("executing cube select total_burn from rrcube where time_range_in(event_time,'2018-05-17-00','2018-05-17-01')");
//        System.out.println("conf = " + lensClient.getConf()+ "param " + lensClient.getConnectionParam());
//        lensClient.executeQueryAsynch("cube select total_burn from rrcube where time_range_in(event_time,'2018-05-17-00','2018-05-17-01')","burn");

    }
