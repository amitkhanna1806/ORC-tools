import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellComand {

  public static void main(String[] args) {

    ExecuteShellComand obj = new ExecuteShellComand();

    String domainName = "google.com";

    //in mac oxs
    String command = "who -m" ;

    //in windows
    //String command = "ping -n 3 " + domainName;

    String output = obj.executeCommand(command);

    System.out.println(output);

  }

  private String executeCommand(String command) {

    StringBuffer output = new StringBuffer();

    try {
      Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","who am i"});
      p.waitFor();
      System.out.println("p is" + p.getInputStream());
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(p.getInputStream()));

      String line = "";
      for(int i =0; i<5;i++)
      {	System.out.println("line is "+ reader.readLine());
        output.append(line + "\n");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return output.toString();

  }

}