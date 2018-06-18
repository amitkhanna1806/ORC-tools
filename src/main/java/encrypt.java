import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import javax.crypto.Cipher;

/**
 * @author visruthcv
 *
 */
public class encrypt {

  private static final String ALGORITHM = "RSA";

  public static byte[] encrypt(byte[] publicKey, byte[] inputData)
      throws Exception {

    PublicKey key = KeyFactory.getInstance(ALGORITHM)
        .generatePublic(new X509EncodedKeySpec(publicKey));

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.PUBLIC_KEY, key);

    byte[] encryptedBytes = cipher.doFinal(inputData);

    return encryptedBytes;
  }

  public static byte[] decrypt(byte[] privateKey, byte[] inputData)
      throws Exception {

    PrivateKey key = KeyFactory.getInstance(ALGORITHM)
        .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.PRIVATE_KEY, key);

    byte[] decryptedBytes = cipher.doFinal(inputData);

    return decryptedBytes;
  }

  public static KeyPair generateKeyPair()
      throws NoSuchAlgorithmException, NoSuchProviderException {

    KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);

    SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

    // 512 is keysize
    keyGen.initialize(512, random);

    KeyPair generateKeyPair = keyGen.generateKeyPair();
    return generateKeyPair;
  }

  public static void main(String[] args) throws Exception {

    KeyPair generateKeyPair = generateKeyPair();

    byte[] publicKey = generateKeyPair.getPublic().getEncoded();
    byte[] privateKey = generateKeyPair.getPrivate().getEncoded();

    BASE64Encoder base64Encoder =new BASE64Encoder();
    System.out.println("base64Encoder "+base64Encoder.encode(publicKey));
    System.out.println("base64Encoder private "+ base64Encoder.encode(privateKey));
    String basepublic = base64Encoder.encode(publicKey);
    String baseprivate = base64Encoder.encode(privateKey);

    BASE64Decoder base64Decoder = new BASE64Decoder();
    byte[] publicKey2 = base64Decoder.decodeBuffer("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALmTB82e6dzi57BADXknlOqqHFB1SYdxKr8KPPzjbWbD\n" +
        "4vv/dH+k7rNGpTUCeqG5RGmi7NvqDXxORCH9XQ4dJDMCAwEAAQ==");
    byte[] privateKey2 = base64Decoder.decodeBuffer("MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAuZMHzZ7p3OLnsEANeSeU6qocUHVJ\n" +
        "h3Eqvwo8/ONtZsPi+/90f6Tus0alNQJ6oblEaaLs2+oNfE5EIf1dDh0kMwIDAQABAkBS/Qm0TqjO\n" +
        "+zoZe8uKrsTN3J0k7g6f31/6Zx23Cg3WsmeDJPTxR5cwLWfmpP2JKfSTguW3xwTIb0jKhlH1TngB\n" +
        "AiEA7rPxkLef7N5VZQI2/6mgpGSoRg/w2vdv74LovwoD2QECIQDHBYaaqzmWL5rGgSfkpD8T+FXv\n" +
        "5rsAJDDhntf+T9fpMwIhALAJrMEZaqv5W+jeNUlfA8W5JhBleS9DBB4S4Sria+wBAiBfGlfhDxM8\n" +
        "nlDyrIM4sffmfhPqyLHxdlnZZsNGJjdBmwIhAJQ0xAz+UgGQYty/qArI6q/q0HaNHqcdnu+K2/GC\n" +
        "4Psb");

    byte[] encryptedData = encrypt(publicKey2,
        "hi this is Visruth here".getBytes());


//    String data = "��\\f]��\\u0004�����~\\u0005�1\\u0011ɏ�,�ϫ��Ap�HN�!Z\\u0012�n�j�m�\\u0018\\u001E/f����A梽s�ZX��ˆ�";
//
//    BASE64Decoder base64Decoder2 = new BASE64Decoder();
//    byte[] encryptedData3 = base64Decoder2.decodeBuffer("Data");
    byte[] decryptedData = decrypt(privateKey2, ("smFzRbcJk3cRP8/n73t0dfLGQgPrIaKuYBcBTk5yPHHAFpZLLRLNkB02kCQxmZYQRlkbMOB8ivNWn/6NjoLApVQu003du003d").getBytes());

    System.out.println(new String(decryptedData));
    //jsonparse();
    System.out.println(getHashedBucketId("500c449c-8793-402a-abcf-1622f86b70a2"));

  }

  public static String getHashedBucketId(String userId) {
    long userIdValue = 0;

    for (int i = 0; i < userId.length(); i++) {
      userIdValue += (long) userId.charAt(i);
    }

    return String.valueOf(userIdValue % 100);
  }
  public static void jsonparse() {
    System.out.println("making pretty string");
    String str1="[{\"uuid\":\"0001c342-aac9-41e9-9620-7d7961f221c2\",\"tenantName\":\"inmobi\",\"filters\":{\"user_data_encryption_algorithm\":\"symmetric\",\"key_encryption_algorithm\":\"rsa\"},\"data\":\"Request(id:GUID(id_high:-7898441399872057344, id_low:-72056219648392973), IP:Geo(carrier:1139, country:52), handset:HandsetMeta(id:69622, manufacturer:200, os:3), n_ads_requested:1, n_ads_served:0, inventory:APP, user:User(id:0001c342-aac9-41e9-9620-7d7961f221c2, uids:{selectedUid=0001c342-aac9-41e9-9620-7d7961f221c2, u-id-adt=0, u-id-s=GPID, GPID=0001c342-aac9-41e9-9620-7d7961f221c2}, discardedUids:{}), slot_requested_deprecated:0, detected_site:Site(site_inc_id:1409028199472772, site_guid:a793618f6c434a96afd0a2b6c2506bbe, inventory_type:APP, publisherExpectation:1.0), u_params:{u-appbid=com.textra, u-s-id=9b5f80b7-4a52-4447-a20f-8bdc61a0a12d, u-appis=com.android.vending}, x_headers:{x-forwarded-for=78.229.90.4:54740}, spec_version:pr-SAND-HTBTA-20180409, request_source:RequestSource(req_origin:CLIENTSIDE, integration_method:SDK, version:7.1.0, integration_family:Android Sdk, is_direct_integration:true, third_party_name:dir), mk_carrier:78.229.90.4, rq_ad_interaction_type:native, rq_response_format:sdkJson, rq_site_id:a793618f6c434a96afd0a2b6c2506bbe, refparams:{}, h_user_agent:Mozilla/5.0 (Linux; Android 5.1.1; D5503 Build/14.6.A.1.236; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.158 Mobile Safari/537.36, d_params:{d-textsize=28.0, d-localization=fr_FR, d-nettype-raw=1, d-device-screen-size=360X592, d-media-volume=100, d-model-name=D5503, d-density-dependent-screen-size=720x1184, d-brand-name=Sony, d-device-screen-density=2.0, d-manufacturer-name=Sony, d-orientation=1}, suppply_source_type:NETWORK_SUPPLY, apBssid:22043762314193, detectedDeviceModelId:49265, visible_bssid_present:false, requestApiType:API_1_0, resolvedAdType:NATIVE, language:Language(id:48, code:fr), preloadRequest:0, resolvedSlotSize:0x0, connectionType:WIFI, deeplinkSupport:0, gdprDetails:GdprDetails(gdprEnabled:true, userConsent:true), coppaRequest:false)1527169358181[]\"},{\"uuid\":\"0001c342-aac9-41e9-9620-7d7961f221c2\",\"tenantName\":\"inmobi\",\"filters\":{\"user_data_encryption_algorithm\":\"symmetric\",\"key_encryption_algorithm\":\"rsa\"},\"data\":\"Request(id:GUID(id_high:-7999769092953010176, id_low:-3963165215364742928), IP:Geo(carrier:1139, country:52), handset:HandsetMeta(id:69622, manufacturer:200, os:3), n_ads_requested:1, n_ads_served:0, inventory:APP, user:User(id:0001c342-aac9-41e9-9620-7d7961f221c2, uids:{selectedUid=0001c342-aac9-41e9-9620-7d7961f221c2, u-id-adt=0, u-id-s=GPID, GPID=0001c342-aac9-41e9-9620-7d7961f221c2}, discardedUids:{}), slot_requested_deprecated:0, detected_site:Site(site_inc_id:1409028199472772, site_guid:a793618f6c434a96afd0a2b6c2506bbe, inventory_type:APP, publisherExpectation:1.0), u_params:{u-appbid=com.textra, u-s-id=baac2325-7962-43ea-82a8-7815e6d4e3e0, u-appis=com.android.vending}, x_headers:{x-forwarded-for=78.229.90.4:58485}, spec_version:pr-SAND-HTBTA-20180409, request_source:RequestSource(req_origin:CLIENTSIDE, integration_method:SDK, version:7.1.0, integration_family:Android Sdk, is_direct_integration:true, third_party_name:dir), mk_carrier:78.229.90.4, rq_ad_interaction_type:native, rq_response_format:sdkJson, rq_site_id:a793618f6c434a96afd0a2b6c2506bbe, refparams:{}, h_user_agent:Mozilla/5.0 (Linux; Android 5.1.1; D5503 Build/14.6.A.1.236; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/66.0.3359.158 Mobile Safari/537.36, d_params:{d-textsize=28.0, d-localization=fr_FR, d-nettype-raw=1, d-device-screen-size=360X592, d-media-volume=100, d-model-name=D5503, d-density-dependent-screen-size=720x1184, d-brand-name=Sony, d-device-screen-density=2.0, d-manufacturer-name=Sony, d-orientation=1}, suppply_source_type:NETWORK_SUPPLY, apBssid:22043762314193, detectedDeviceModelId:49265, visible_bssid_present:false, requestApiType:API_1_0, resolvedAdType:NATIVE, language:Language(id:48, code:fr), preloadRequest:0, resolvedSlotSize:0x0, connectionType:WIFI, deeplinkSupport:0, gdprDetails:GdprDetails(gdprEnabled:true, userConsent:true), coppaRequest:false)1527145765989[]\"}]";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(str1);
    JsonArray jsonArray = je.getAsJsonArray();
    for (JsonElement jsonElement : jsonArray) {
      JsonObject jsonobj = jsonElement.getAsJsonObject();
      System.out.println("uuid is " + jsonobj.get("uuid"));
      JsonElement filterElement = jsonobj.get("filters");
      System.out.println(filterElement);
      JsonObject filterObject = filterElement.getAsJsonObject();
      System.out.println("key encryption algo is "+ filterObject.get("key_encryption_algorithm").getAsString());
      System.out.println("data encryption algo is "+ filterObject.get("user_data_encryption_algorithm").getAsString());
    }
//    String prettyJsonString = gson.toJson(je);
    //System.out.println("pretty string is " +prettyJsonString);
  }

}