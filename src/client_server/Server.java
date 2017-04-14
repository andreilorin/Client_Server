package client_server;

import java.io.*;
import java.net.*;

public class Server {
    
    private static final int PORT = 4141;
    
    public static void main(String[] args) {
        System.out.println("Listening on port " + PORT + " CTRL + C to stop");
        
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Connected !");
                try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
                    out.println("Hello from the server socket !");
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
