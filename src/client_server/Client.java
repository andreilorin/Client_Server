package client_server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private final String NAME;
    private final String IP;
    private final int PORT;
    
    private Client(String name, String ip, int port){
        NAME = name;
        IP = ip;
        PORT = port;
    }
    
    public static void main(String[] args) {
    
        Scanner scanner = new Scanner(System.in);
        
        Client client = new Client("Andrei", "localhost", 4141);
        
        try (Socket socket = new Socket(InetAddress.getByName(client.IP), client.PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            
            //client.write(output, args[0]);
            System.out.println("Requesting connection");
            output.println("CONNECT" + client.NAME);
            
            String in = "";
            
            while (true) {
                in = input.readLine();
                System.out.println("Server: " + in);
                String message = scanner.nextLine();
                System.out.println("Client(" + client.NAME + "): " + message);
                output.println(message);
                
            }
            
//            while ((in = input.readLine()) != null) {
//                System.out.println("Received: " + in);
//                if (in.equals("hi there")) {
//                    
//                    String message = scanner.nextLine();
//                    
//                    client.write(output, message, client);
//                }
//                if (in.equals("busy")) {
//                    break;
//                }
//                if (in.contains("fine, thanks!")) {
//                    client.write(output, "nice", client);
//                }
//            }
//            System.out.print("Closing socket.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(PrintWriter output, String message, Client client) {
        System.out.println("Client(" + client.NAME + "): " + message);
        output.println(message);
    }
}
