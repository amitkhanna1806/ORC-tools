//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Date;
//import java.util.Properties;
//
///**
// * Created by amit.khanna on 5/25/18.
// */
//public class send_mail {
//  public static void main(String[] args){
//    sendMail("hi i am amit ", "amitkhanna1806", "40", "SUCCESSFULL");
//  }
//  private static void sendMail(String result, String requestedUsername, String queryHandle, String status) {
//    //todo send mail to requesteduser
//    Properties properties = System.getProperties();
//    properties.setProperty("mail.smtp.host","mrelay.corp.inmobi.com");
//    properties.setProperty("mail.smtp.port","25");
//    properties.setProperty("mail.defaultEncoding","UTF-8");
//    properties.setProperty("mail.smtp.timeout","30000");
//    properties.setProperty("mail.smtp.connectiontimeout","15000");
//
//    Session session = Session.getDefaultInstance(properties, null);
//
//
//    String QUERYHANDLEPATH = "/home/amit.khanna/query_handles";
//
////    Session session = Session.getDefaultInstance(properties, null);
//    try {
//      MimeMessage message = new MimeMessage(session);
//      message.setFrom(new InternetAddress("no-reply@inmobi.com"));
//      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("amit.khanna@inmobi.com"));
//
//      message.setSubject("Query " + status + " for " + queryHandle);
//      message.setSentDate(new Date());
//      MimeBodyPart messageBodyPart = new MimeBodyPart();
//      messageBodyPart.setContent("find attachment ", "text/html; charset=utf-8");
//      Multipart multipart = new MimeMultipart();
//      multipart.addBodyPart(messageBodyPart);
//      MimeBodyPart attachPart = new MimeBodyPart();
//      File queryResultFile = new File (QUERYHANDLEPATH + "/" + queryHandle +"_result.txt");
//      FileWriter queryFileWriter = new FileWriter(queryResultFile.getAbsoluteFile());
//      BufferedWriter queryBufferedWriter = new BufferedWriter(queryFileWriter);
//      queryBufferedWriter.write(result);
//      queryBufferedWriter.close();
//      queryFileWriter.close();
//      attachPart.attachFile(QUERYHANDLEPATH + "/" + queryHandle +"_result.txt");
//      multipart.addBodyPart(attachPart);
//      message.setContent(multipart);
//      Transport.send(message);
//      queryResultFile.delete();
//    } catch (MessagingException e) {
//      System.out.println("Error while sending mail " + e);
//      return;
//    } catch (Exception e) {
//      System.out.println("Error while sending mail " + e);
//      return;
//    }
//
//
//  }
//}
