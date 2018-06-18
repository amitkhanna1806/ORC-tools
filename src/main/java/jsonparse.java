import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.compress.utils.Charsets;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;
import sun.util.calendar.CalendarDate;

/**
 * Created by amit.khanna on 5/28/18.
 */

public class jsonparse {
  public static void main(String args[]) throws Exception {
      String s="[{\"uuid\":\"40bef925-fffc-4a37-883e-ba16373ced44\",\"tenantName\":\"inmobi\",\"filters\":{\"user_data_encryption_algorithm\":\"AES\",\"key_encryption_algorithm\":\"RSA\"},\"data\":\"30nfDdgW-ZcyRrsk03WatsJYlhD3Xccrp91sfxJKzBRIFT6BgrH8MClKbEP0NhIbfWpkSZUlTblLi9_5d3iWQtF64758G8VgmcXxFHtrGHCy4vWQz8RXJTNmex0ef0oesAOrX8wM1LbWIu642JZkeO0PztjPRQVmJgTwH8jIUORzfxOSIwZ70c9em_sGqsxiGc-96_iGu2pHkscHcGPG0iPBUPeVmRt8gWk87aG65EDxhFEOP1bzvTdVAmCty31u35skQh_u-IJUGWu7UAyoTMQEdHuIpue0ddwK2pA2ujdHwDlJbh4F5CYNFuLMlHE2x21NWHnxN6SBRmr8kOhlgfpcS4XxEf7TDr6NMU0blglG4vtfhHLfT7zMC2-Z1QL9jT_8-o0nwZhZd_oyxx1e-_M7DakK5VR4oYZ9IiM9Wn_DbXW62muBLCBXI-iNvAp8-Y8LoKv3Kzczp9Ncb9SHp8oUdCK8gTwxH6_908HMMLanqeuFm7E2Bgb2Y_auhQYEnWRSyw-RTqZPozswwzJUPvfRAEm1nVk5fWZ_U35hluOLHy9MR3FFc2phCDA8Wr5JHgJFzWzT-f8ymgnQuii8f9NLxkTX9Nehr-xLa5bhAp2245l9t_0mVErbsaUJ57F-2LRzQ11l3gJERStGYvU7_tGBEONtPSHj4yTDqwdzY3pvOMVnI8-xb6OI7Wlx_yncdXgIkbBVYBqI4ow_haGTBYYKKmUdD0DFSNT2UfR_9GBFmFsagspJSycl6DwekDuZbKrlMfSRBzwQ02MbudggAgHqDUgry0E9OzLLa_ooqSKkHMZM6_adm9Srbo15KTaxbQOfLyHWIop2RCUeH94npPfQirlWTE6_LdACOKByDykiNrdfBtk4vSrXzGIBhdafOfUpnvPzojV8XfQLPoNLGu_jJrOd-sTeoqUBfKLRDPARiz-tmHkCIEoLSlikGubaBSKrN63P_Kr1kURkPH-45GmV7sKCWHzalvuOOpYfbj2ZkBa_DxIwVP3HNYDVlvKmn6ZS_hvr9LroZsJK2I9C1UN40mNNBd_9iHOt60CZZjABmfCdwR16CWX1KYe2OKkxcBufmT1d_nEGgcOFU1g10Al6GwHm0cXT-pVKWrx2PCHWGoluBSRrx3CQ_13Tokp4QRT9uRW3qtH3eJ8B6bpsfAWj08RyoHlFyoTQyQfVGyi5Thu3m_ktOVtDe3cOOqaUJS9Zz132VKBPOulZuBJhWTIfczvd5R6j4oOtdjDj9oi52mg1__X367CB-fcb_M8zeujG-6T0lBbV8VHCVNZ87cLCnN6m7Snojo6Kq3jiOcPfEdVZaqfvXD0F2rvBn5UkPwa45Poz_oM36sudCcEPSGAucJ87n4GTTXcU-wchQvLGtaGcdFKNFXmDZKl_0zZQcerFcmHzePXMzDCfhsYe6iTJ0ooi4CBny4waCtEVHT9k-QLfoJ3t7JWzw3qqzkMJO5qG_nG_Ox8-Q0IHRWNu-6y0w9ENrrQm6d8CzHdxvGuznenogVgKxm-BYHqij9YvuURJ8Y6JednfpSuvZcPQ1U5bFihP6vB8NrRvAkqvnGq17r2BSIpIeJlmYoRrDwHXbitCRSngjOpR1Q8M77J_MXQCQiJm5JzKr3A9cFiuTMYlgN9sG26fDQJqECISdat4UB5zCkjl0e_WKi7j37oQJEaU1p5wTt3ql-p9YwNjKPjNebwVGXIRkVkj_s0NqHuK9pHEGQFKlZWARS0vBw1C-_pUQxkSV38qw5kNoduw4c_kmKmyejvS0PyaoKjPBCoYLM6XyJYQ-Zcisk1FADt6JE4GWUPJxDvdDaXB8Wpg4Ik_s4BKfGCMwCMaSr33Sgh9TNsMmOoIZ3z_6Q3Bk-izs6NbB2TZNxRUvhaWSQhGy68cI0HGY-WzgxM7pGJGkaJfAVQhjZeUXiUHx1bSZvAIqE9nidTaNLBmUBxOBecZYToSfH6BFXmiA3fFeOZWDj-BZq3eIrPmDxRO4LDlfNTFJyeN6vWozzaBafXqLwntOiVQp7i1nSnyHUOi1CTaawwR7YraOPQ6mY-iHob3BQaJif_UGJj6j1QwnPQbOWAI5lH6P1b4w4LdLccEIqGeN3siKLQysIp8lj1DAsbRl1hjV7xgW_J81ks5YyKI9FIJ5C7R-xTVAlfe1_P1VaM0ljMVJyQm1bLR92f38GoKDgjCHTWH0EaNA6z6TiV4EgV3s66dJCRxfTrGtaXqtP8aBjtBty6P_Cf6t51MR47sfGcLBKkd92F-a5s-VSwCGzSipuSlPUMotS026LYEIbn9EEczPlWyZU7mh-l4D9W6twcSzjdyuNkY7QlJi4zIzRN8JoSgyb4_SgBNgqGyLrbiSBNfcw-yBsyOfm3PWPhpZOF20YMzkORnZckMm32-pYkuIaUcW20Z01TSVaKXiFh63W9SCee1ak8z1sWL2_PxsfRwNavYCEBYyUPVohKl-PVyK0qVdUx93TKDTh7pjCqBsCIrUq78rJLtDsWQYhUqHNC6CshL8bV7XxzffshbTAn-Cl0boLJuDjnOC2ta2kV0U5V9wkyXfBU6SfcydltucNTytPnEeHQLLJHwZgSzaMEK0mireCyh2rOoz8YM9RNRYOMfMpN9PP4rC0f8I-auxnRpywXqy4U0AY5O4xAFAwliIxaPnZ2NZ80oBPybRK7kNRXqTM1PiiCzOJGiWZ6FKACTdzZ1XPrJUPPlmMRZudCJwdw4XIi_u-h3z9mHOWL-hdADOj1Jc1WGexuoSPqLIOXJ5OVbG6V2Qw0V4NVsRDEmFHO-axEEHrQuKPhGlTdVw47ko4L1aZMqvsTDVNjoz8E7wwAkBtk9NvJLIiR2gQTB_sMtR4JVSWmKhs-ZNv7Kxs68p0Q0ka8WXjByfpQ65lqpJWYo4fLFYIpvXwC5JDRLz8Hy283rTMpRSnesV-W5xpePOO3n6uYSs8baMMNWXgwnVKtG7MVmuVEJ9s3RraE9Q_ZCpx0deCIdQx5k_tgV_AVMM_fc0OSRtkREbYOal8Z_Fey3rrIyC0M3N8xJzHCJtJTt2c8qMUDNPjiOPkbGUAx-OgSkrLt267MmKvtoGvvMVmIFL1gw3VgiNZbzCO8c-oP7l__JWDopQNPSyeIlLzak5A9vlvGw7C00VWO4LKBjfnq0nn3gFugNXXd2bj2sIP-p0iK4KF9RxIjtSRuiKEEfRhT7RnvWuBgJ7eNsuf7DI0JqVRZFk0EkZeNJWdTktikUGnFrqrCIjGcDdHhq14nRFER_JEimK61Mefha9E_Drkl-auwyYkCOSrSDiELchkQhFFDp6_lCV7nqB-IoC-v3cqZ3w_Mpf9FeA_sUad1Or_SI4VFjWG-RAjsO22BWwmRQYuMf5oNeOdNbX9VHQ6CH7PHtDhnhjfDZtRGjfSDEeT0WsF8tYHg7MHiAynTIRiWjA5KKHGjwcNOMHPnlNqo_zmbClqxHayWCNVQuPt8QhCq7acsPDr1tvtSDlPQyFfYcRnvOvUBC272gzyTO7u4hv9WbC51Xhi4gCXVxS62JV8Z3CbJ4tcDUHNXTmATgE1EHMa44FNtDcSQOdJDfV8Nl46Sh2JARlUO7dRoRd8kT-uMN1XRy5qyIPdvvxhVgLxxb-JFUBYDbzdXM5Q0Xm3ZMLcENPK8y1fFSuUny8YZU6ZqwzKGxyn1SrQGP9DAZ_dEqGBjzoh-CPcdIpKtBptqEAAzhnW47HNv7z1QpAjw6BwDgB_e8E9BfykcOU_uP5q9BskxuB0xNyfkHaX1tgCxk6YkBkpoEI9xJms3Qc3jtQkfvHivWz-wvermtU7QCDpNxkD8sf6caLuqRwMZJmhey6YkBkpoEI9xJms3Qc3jtQqtHgXc8aWeQpU9pMccdRxQpy-CfGrgP8m9Vdd89WnUsv0QYTjUJ2mslnCbQ1VMhAvTrf-FHnkO0CquQoCCc3JI\",\"userDataEncryptionKey\":\"dj467ddD-fPhD3TRPDyMx-mnRUqMw98ZSzlIJKhHFONMR3tJ2MVWNdecIAhYhqLMRlf3TWi2UzC5dilpD6JtPQ\"}]";
    getDecryptedInfo(s);
  }
  private static String makeJsonCompatible(String message){
    String finalMessage ="";
    finalMessage+=message.charAt(0);
    for (int i =1; i< message.length()-1;i++) {
      if(message.charAt(i) == '"' && message.charAt(i+1) == '"'){
        finalMessage+='\\';
        continue;
      }
      else if(message.charAt(i) == '\\' && message.charAt(i+1) != '"')
        continue;
      finalMessage+=message.charAt(i);
    }
    finalMessage+=message.charAt(message.length()-1);
    return finalMessage;
  }
  private static String getDecryptedInfo(String encryptedData) throws Exception {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    System.out.println("encrypted row is {}"+ encryptedData);
    JsonElement je = jp.parse(encryptedData);
    System.out.println("parsed");
    JsonArray jsonArray = je.getAsJsonArray();
    String decryptedResult = "";
    for (JsonElement jsonElement : jsonArray) {
      JsonObject jsonobj = jsonElement.getAsJsonObject();
      JsonElement filterElement = jsonobj.get("filters");
      JsonObject filterObject = filterElement.getAsJsonObject();
      System.out.println("data is " + jsonobj.get("data").getAsString());
     byte[] decryptKey = decryptKey(jsonobj.get("userDataEncryptionKey").getAsString(),filterObject.get("key_encryption_algorithm").getAsString());
      System.out.println("string key is "+Base64.encodeBase64String(decryptKey));

      JsonElement jsonDataElement = jp.parse(jsonobj.get("data").getAsString());
      String prettyJsonString = gson.toJson(jsonDataElement);
      decryptedResult += prettyJsonString;
      decryptData(jsonobj.get("data").getAsString(), decryptKey, "AES/ECB/PKCS5Padding");
    }
    return decryptedResult;
  }
  private static byte[] decryptKey(String encryptedkey, String keyEncryptionAlgorithm) throws Exception {
    byte[] privateKey = Base64.decodeBase64("MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAuZMHzZ7p3OLnsEANeSeU6qocUHVJ\n" +
        "h3Eqvwo8/ONtZsPi+/90f6Tus0alNQJ6oblEaaLs2+oNfE5EIf1dDh0kMwIDAQABAkBS/Qm0TqjO\n" +
        "+zoZe8uKrsTN3J0k7g6f31/6Zx23Cg3WsmeDJPTxR5cwLWfmpP2JKfSTguW3xwTIb0jKhlH1TngB\n" +
        "AiEA7rPxkLef7N5VZQI2/6mgpGSoRg/w2vdv74LovwoD2QECIQDHBYaaqzmWL5rGgSfkpD8T+FXv\n" +
        "5rsAJDDhntf+T9fpMwIhALAJrMEZaqv5W+jeNUlfA8W5JhBleS9DBB4S4Sria+wBAiBfGlfhDxM8\n" +
        "nlDyrIM4sffmfhPqyLHxdlnZZsNGJjdBmwIhAJQ0xAz+UgGQYty/qArI6q/q0HaNHqcdnu+K2/GC\n" +
        "4Psb");
    byte[] inputData = Base64.decodeBase64(encryptedkey);
    System.out.println("key encrypted is "+ encryptedkey);
    System.out.println(inputData.length);

    return decrypt(privateKey, inputData, keyEncryptionAlgorithm);
  }
  public static byte[] decrypt(byte[] privateKey, byte[] inputData, String keyEncryptionAlgorithm)
      throws Exception {

    PrivateKey key = KeyFactory.getInstance(keyEncryptionAlgorithm)
        .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.PRIVATE_KEY, key);

    byte[] decryptedBytes = cipher.doFinal(inputData);

    return decryptedBytes;
  }

  public static String decryptData(String encryptedData, byte[] key, String encryptionAlgorithm) throws Exception {
    Cipher aesCipher;
    System.out.println("recieved data is "+encryptedData);
    aesCipher = Cipher.getInstance(encryptionAlgorithm);
    System.out.println("key length is " + key.length);
    System.out.println("key in decrypt data " + Base64.encodeBase64String(key));
    SecretKey secretKey = new SecretKeySpec(key, 0, 16, "AES");
    aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] encryptedBytes = Base64.decodeBase64(encryptedData);
    System.out.println("ecnrypted bytes lensgth "+ encryptedBytes.length);
    byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
    String decryptedData = new String(decryptedBytes);
    //System.out.println("decrypted data is " +decryptedData);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement jsonElement = jp.parse(decryptedData);
    System.out.println(gson.toJson(jsonElement));
    //System.out.println("decrypted data is " + decryptedData);

//    TIOStreamTransport tioStreamTransport = TIOStreamTransport.class.newInstance();
//    TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
//    System.out.println(tBase.toString());
//    deserializer.deserialize(tBase, decryptedBytes);
//    System.out.println(tBase.toString());
//    TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());
//    System.out.println("serialized" + serializer.toString(tBase));
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-00");
    System.out.println(dateFormat.format(date));
    Date date2 = date;
    date2.setMonth(date.getMonth()-13);
    System.out.println(dateFormat.format(date2));
    return decryptedData;
  }
}
