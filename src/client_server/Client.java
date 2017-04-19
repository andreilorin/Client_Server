package client_server;

import java.io.*;
import java.net.*;

public class Client {
    
    private static final int PORT = 4141;
    private static final String IP = "localhost";

    public static void main(String[] args) {
        try(    Socket socket = new Socket(InetAddress.getByName(IP), PORT);
                DataOutputStream dout = new DataOutputStream((socket.getOutputStream()));
                DataInputStream din = new DataInputStream(socket.getInputStream());
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            
            System.out.println("Write something to the server: \n");
            
            String received = "", sent = "";
            
            while (!received.equals("end")) {
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
