package client_server;

import java.io.*;
import java.net.*;

public class Client {
    
    private static final int PORT = 4141;
    private static final String IP = "xxx.xxx.xxx.x";

    public static void main(String[] args) {
        try(Socket s = new Socket(InetAddress.getByName(IP), PORT)){
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println(input.readLine());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
