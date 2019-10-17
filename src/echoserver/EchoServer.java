package echoserver;
import java.net.*;
import java.io.*;


public class EchoServer {
  public static final int portNumber = 6013;

  public static void main(String[] args) {
    try {
      // Start listening on the specified port
      ServerSocket sock = new ServerSocket(portNumber);

      // Run forever, which is common for server style services
      while (true) {
        // Wait until someone connects
        Socket client = sock.accept();
        System.out.println("Got a request!");

        // Construct a writer so we can write to the socket, thereby
        // sending something back to the client.
        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        // Reading from inputstream and writing to the client
        int x;
        while((x = input.read())!=-1){
	       	output.write(x);
        }

        //flush output
	      output.flush();

        // Close the client socket/input/output since we're done.
        client.close();
        input.close();
        output.close();
      }
    // *Very* minimal error handling.
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
