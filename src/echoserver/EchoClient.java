package echoserver;
import java.net.*;
import java.io.*;


public class EchoClient {
  public static final int portNumber = 6013;

  public static void main(String[] args) throws IOException {
    String server;
    // Use "127.0.0.1", i.e., localhost, if no server is specified.
    if (args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    try {
      // Connect to the server
      Socket socket = new Socket(server, portNumber);

      InputStream input = socket.getInputStream(); // Get the input stream so we can read from that socket
      OutputStream output = socket.getOutputStream(); // Allows us to write to the socket

      // Print all the input we receive from the server
      int bite; //more like a byte
      while ((bite = System.in.read()) != -1) {
        output.write(bite);
        output.flush();
        System.out.write(input.read());
      }

      // Flush before closing so we don't lose some extra bytes
	    System.out.flush();
      // Close the socket/input/output when we're done reading from it
      socket.close();
      input.close();
      output.close();

    // Provide some minimal error handling.
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
