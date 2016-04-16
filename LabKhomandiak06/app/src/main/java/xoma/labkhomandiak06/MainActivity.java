package xoma.labkhomandiak06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//віддалена клавіатура. Клавіші що друкуються на Android пристрої - друкуються і на комп'ютері
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText text = (EditText) findViewById(R.id.field1);
        Button b = (Button) findViewById(R.id.button);
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
        final PrintWriter out;

        try
        {
            // Create the streams to send and receive information
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    out.println(text.getText());
                }
            });



        }
        catch(IOException ioe)
        {
            System.out.println("Exception during communication. Server probably closed connection.");
        }
        finally
        {
            try
            {
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
