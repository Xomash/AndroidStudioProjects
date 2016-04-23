package com.example.z1kses.recognition;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String[] words = {"совесть", "взрыв", "глагол", "татуировка", "логика", "отношения", "свеча", "вишня", "глина", "словарь", "нейтрон", "маргарин", "конфета", "экономика", "гост", "белорус", "ножницы", "дезертир", "каша", "бумага"};
    private ArrayList<String> pattern = new ArrayList<String>(Arrays.asList(words));

    private final int QUANTITY = 5;
    private int[] results = new int[QUANTITY];
    private int TEXT_TIME = 24;

    private TextView warningText;
    private TextView timerText;
    private TextView attempText;

    private Button listen;

    private EditText editText;

    private int MAX_TIME = 60;

    private int count = 0;
    private int time = MAX_TIME;

    private Handler handler;
    private Handler handler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        Client client = new Client();
        client.execute();
        editText.setFocusable(false);

        listen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                attempText.setText("Спроби: " + count + "/" + QUANTITY);
                warningText.setText("Прослуховування");

                listen.setClickable(false);

                editText.setFocusable(false);
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.text);
                mediaPlayer.start();

                handler = new Handler();
                final Runnable r = new Runnable()
                {
                    public void run()
                    {

                        editText.setFocusableInTouchMode(true);

                        warningText.setText("Введення");
                        timerText.setText(String.valueOf(time));

                        handler1 = new Handler();
                        final Runnable r1 = new Runnable()
                        {
                            public void run()
                            {

                                --time;
                                timerText.setText(String.valueOf(time));
                                handler1.postDelayed(this, 1000);

                                if(time == 0) {
                                    handler1.removeCallbacksAndMessages(null);

                                    String text = editText.getText().toString();
                                    analyze(text);

                                    editText.setText("");

                                    warningText.setText("Очікування");
                                    timerText.setText(String.valueOf(MAX_TIME));

                                    count++;
                                    attempText.setText("Спроби: " + count +"/"+QUANTITY);
                                    time = MAX_TIME;

                                    editText.setFocusable(false);
                                    listen.setClickable(true);

                                    if(count == QUANTITY){
                                        listen.setClickable(false);



                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setMessage("Тест пройдено!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Toast.makeText(getApplicationContext(), "Кінець!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                }
                            }
                        };
                        handler1.postDelayed(r1, 1000);

                    }
                };
                handler.postDelayed(r, TEXT_TIME*1000);

            }

        });

    }

    private class Client extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("192.168.137.1", 5555); // connect to the server

                ArrayList<Integer> intList = new ArrayList<Integer>();
                for (int index = 0; index < results.length; index++)
                {
                    intList.add(results[index]);
                }

                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(intList);
                out.flush();

                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    private void analyze(String text){
        ArrayList<String> pattern = (ArrayList<String>) this.pattern.clone();

        if(text!= null && !text.equals("")){
            String[] variants = text.split(" ");

            for(String v: variants){
                if(pattern.contains(v.toLowerCase())){
                    pattern.remove(v);
                    ++results[count];
                }
            }
        }else
            results[count] = 0;
    }

    private void initialize(){
        warningText = (TextView) findViewById(R.id.warningText);
        attempText = (TextView) findViewById(R.id.attempText);
        timerText = (TextView) findViewById(R.id.timerText);

        editText = (EditText) findViewById(R.id.editText);

        listen = (Button) findViewById(R.id.listen);
    }
}
