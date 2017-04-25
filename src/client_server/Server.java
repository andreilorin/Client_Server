package client_server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    private static final int PORT = 4141;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Server server = new Server();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Listening on port 4141, CRTL-C to quit ");
                Socket socket = serverSocket.accept();
                try (PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                    System.out.println("Connected");
                    String received = "";
                    while ((received = input.readLine()) != null) {
                        System.out.println("Received connection request from " + received.substring(7, received.length()));

                        if (received.contains("CONNECT")) {
                            server.write(output, "hi there " + received.substring(7, received.length()));
                        }

                        while (true) {
                            received = input.readLine();
                            System.out.println("Client: " + received);
                            String message = scanner.nextLine();
                            System.out.println("Server: " + message);
                            output.println(message);

                        }
//                        if (received.contains("CONNECT")) {
//                            server.write(output, "hi there " + received.substring(7, received.length()));
//                        }
//                        if (received.equals("bye")) {
//                            server.write(output, "see you");
//                            break;
//                        }
//                        if (received.contains("how are you")) {
//                            server.write(output, "fine, thanks!");
//                        }
                    }
                    System.out.print("Closing connection\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(PrintWriter output, String message) {
        System.out.println("Sending: " + message);
        output.println(message);
    }

}
