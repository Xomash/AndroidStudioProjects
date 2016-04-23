package xoma.labkhomandiak06;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Socket client;
    BufferedReader in;
    PrintWriter out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client cl = new Client();
        cl.execute();
    }

    private class Client extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("192.168.137.1", 4444); // connect to the server

                String message = "";
                in = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));

                out = new PrintWriter(new OutputStreamWriter(
                        client.getOutputStream()), true);



                runOnUiThread(new Runnable() {
                    public void run() {



                    }
                });


                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}