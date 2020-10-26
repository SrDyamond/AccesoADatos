package com.afundacionfp;

import java.io .*;
import java.net.Socket;
import java.net.ServerSocket;

    public class Main {

        public static void main(String[] args) throws Exception {

            // create socket
            int port = 4444;
            ServerSocket serverSocket = new ServerSocket(port);
            System.err.println("Started server on port " + port);

            // repeatedly wait for connections, and process
            while (true) {

                // a "blocking" call which waits until a connection is requested
                Socket clientSocket = serverSocket.accept();
                System.err.println("Accepted connection from client");

                // open up IO streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                // waits for data and reads it in until connection dies
                // readLine() blocks until the server receives a new line from client
                String line = in.readLine();
                System.out.println("Recibido " + line + ". Reenviando...");
                out.writeBytes(line);

                // close IO streams, then socket
                System.err.println("Closing connection with client");
                out.close();
                in.close();
                clientSocket.close();
            }
        }
    }
