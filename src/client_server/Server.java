package client_server;

import java.io.*;
import java.net.*;

public class Server {
    
    private static final int PORT = 4141;
    
    public static void main(String[] args) {
        System.out.println("Listening on port " + PORT + " CTRL + C to stop");
        
        try(    ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept();
                //PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                DataOutputStream dout = new DataOutputStream((socket.getOutputStream()));
                DataInputStream din = new DataInputStream(socket.getInputStream());
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ){
            
                
                System.out.println("Connected !");
               
                System.out.println("Hello from the server socket !");
                    
                String received = "", sent = "";
                    
                while(!received.equals("end")) {
                        received = din.readUTF();
                        System.out.println(received);
                        sent = input.readLine();
                        dout.writeUTF(sent);
                        dout.flush();
                    }
                    //socket.close();
                
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
