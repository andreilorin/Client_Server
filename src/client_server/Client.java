package client_server;

import java.io.*;
import java.net.*;

public class Client {

    private static final int PORT = 4141;
    private static final String IP = "localhost";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Must include a command line argument, try again.");
            return;
        }
        
        Client client = new Client();
        
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            client.write(output, args[0]);
            String in = "";
            while ((in = input.readLine()) != null) {
                System.out.println("Received: " + in);
                if (in.equals("hi there")) { 
                    client.write(output, "how are you");                    
                }
                if (in.equals("busy")) {
                    break;
                }
                if (in.contains("fine, thanks!")) {
                    client.write(output, "nice");
                }
            }
            System.out.print("Closing socket.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(PrintWriter output, String message) {
        System.out.println("Sending: " + message);
        output.println(message);
    }
}
