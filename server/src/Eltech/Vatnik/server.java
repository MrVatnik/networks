package Eltech.Vatnik;

import java.io.*;
import java.net.*;

public class server {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Server side");
        BufferedReader in = null;
        PrintWriter    out= null;

        ServerSocket servers = null;
        Socket       fromclient = null;

        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient= servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
        long time = System.currentTimeMillis();
        in  = new BufferedReader(new
                InputStreamReader(fromclient.getInputStream()));
        out = new PrintWriter(fromclient.getOutputStream(),true);
        String         input,output;

        System.out.println("Wait for messages");
        while ((input = in.readLine()) != null) {
            System.out.println((System.currentTimeMillis()-time));
            if (input.equalsIgnoreCase("exit")) break;
            if(input.equals("void")) {
                if(System.currentTimeMillis()-time<15000)
                    out.println("void");
                else
                    out.println("Why so long???");
            }
            else{
                out.println("S :: "+Integer.parseInt(input)*Integer.parseInt(input));
                time = System.currentTimeMillis();
            }
            System.out.println(input);
        }
        out.close();
        in.close();
        fromclient.close();
        servers.close();
    }
}

