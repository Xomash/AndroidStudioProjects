package xoma.labkhomandiak06;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadedSocketServer {

    private ServerSocket serverSocket;
    private int port;

    public MultiThreadedSocketServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting the socket server at port:" + port);
        serverSocket = new ServerSocket(port);

        while (true) {

            System.out.println("Waiting for clients...");
            Socket client = serverSocket.accept();
            System.out.println(client.toString());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));

            String message = "";
            Scanner sc = new Scanner(System.in);




			String path = "E:" + File.separator + "hi.txt";
			File f = new File(path);
			f.getParentFile().mkdirs();
			f.createNewFile();
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println("Hello world");
            writer.close();
            out.close();
        }
    }

    public static void main(String[] args) {
        int portNumber = 8080;

        try {
            MultiThreadedSocketServer socketServer = new MultiThreadedSocketServer(portNumber);
            socketServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}