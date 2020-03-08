
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.PrivilegedActionException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLServer
{
  public static void main(String[] args)
    throws Exception
  {
    int intSSLport = 4443;
    
    
    System.setProperty("javax.net.ssl.keyStore", "server.ks");
    System.setProperty("javax.net.ssl.keyStorePassword", "JsEkey@4");
    try
    {
      SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(intSSLport);
      SSLSocket sslSocket = (SSLSocket)sslServerSocket.accept();
      for (;;)
      {
        PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
          new InputStreamReader(
          sslSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
          System.out.println(inputLine);
        }
        out.close();
        in.close();
        sslSocket.close();
        sslServerSocket.close();
      }
    }
    catch (Exception exp)
    {
      PrivilegedActionException priexp = new PrivilegedActionException(exp);
      System.out.println(" Priv exp --- " + priexp.getMessage());
      
      System.out.println(" Exception occurred .... " + exp);
      exp.printStackTrace();
    }
  }
}
