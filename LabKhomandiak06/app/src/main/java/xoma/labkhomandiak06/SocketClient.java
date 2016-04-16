package xoma.labkhomandiak06;

import java.net.*;
import java.io.*; 
 
public class SocketClient
{ 
   
    public static void main(String[] args)
    { 
        String host="localhost";
        Socket s = null;

        try
        {
            s = new Socket(host, 8080);
        }
        catch(UnknownHostException uhe)
        {
            System.out.println("Unknown Host :" + host);
            s = null;
        }
        catch(IOException ioe)
        {
            System.out.println("Cant connect to server at 8080. Make sure it is running.");
            s = null;
        }

        if(s == null)
            System.exit(-1);

        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            // Create the streams to send and receive information
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

         System.out.println(in.readLine());


        }
        catch(IOException ioe)
        {
            System.out.println("Exception during communication. Server probably closed connection.");
        }
        finally
        {
            try
            {

                out.close();
                in.close();
                s.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}