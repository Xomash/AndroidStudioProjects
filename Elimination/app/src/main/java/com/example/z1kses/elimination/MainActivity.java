package com.example.z1kses.elimination;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String reason = "Невказано!";

    private ImageView v1;
    private ImageView v2;
    private ImageView v3;
    private ImageView v4;

    private TextView timerText;
    private TextView stepText;

    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private final int MAX = 22;
    private final int QUANTITY = 10;
    private final int NUMBER_OF_VARIANTS = 4;
    private int current;

    private Variant[][] variants;

    private int time;

    private Variant[] myVariants = new Variant[QUANTITY];

    private boolean end;

    final Handler h = new Handler();
    final int delay = 1000; //milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = 15;
        current = -1;
        variants = new Variant[MAX][NUMBER_OF_VARIANTS];

        initialize();
        prepareForTheGame();
        randomize();

        nextTask();
        h.postDelayed(new Runnable() {
            public void run() {

                synchronized (variants) {
                    if (!end) {
                        --time;

                        if (time < 0) {
                            myVariants[current] = variants[current][0];
                            myVariants[current].setRight(false);
                            myVariants[current].setReason("Невказано!");
                            myVariants[current].setVariantId(0);

                            nextTask();
                        }else{
                            timerText.setText("Час: " + time);
                            stepText.setText("Ходів: " + (QUANTITY - current));
                        }
                        h.postDelayed(this, delay);
                    }
                }
            }

        }, delay);
    }

    private void nextTask() {

        ++current;
        time = 15;

        timerText.setText("Час: " + time);
        stepText.setText("Ходів: " + (QUANTITY - current));

        if (current < QUANTITY) {

            Resources resources = getApplicationContext().getResources();
            final int id1 = resources.getIdentifier(variants[current][0].getImageSrc(), "drawable",
                    getApplicationContext().getPackageName());
            final int id2 = resources.getIdentifier(variants[current][1].getImageSrc(), "drawable",
                    getApplicationContext().getPackageName());
            final int id3 = resources.getIdentifier(variants[current][2].getImageSrc(), "drawable",
                    getApplicationContext().getPackageName());
            final int id4 = resources.getIdentifier(variants[current][3].getImageSrc(), "drawable",
                    getApplicationContext().getPackageName());
            v1.setImageResource(id1);
            v2.setImageResource(id2);
            v3.setImageResource(id3);
            v4.setImageResource(id4);
        } else {
            end = true;
            Client client = new Client();
            client.execute();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private class Client extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("192.168.137.1", 4444); // connect to the server

                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(new ArrayList<Variant>(Arrays.asList(myVariants)));
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

    public void select(View v) {
        synchronized (variants) {
            if (!end) {
                int id = v.getId();

                int variant = 0;

                switch (id) {
                    case R.id.v1:
                        variant = 0;
                        break;
                    case R.id.v2:
                        variant = 1;
                        break;
                    case R.id.v3:
                        variant = 2;
                        break;
                    case R.id.v4:
                        variant = 3;
                        break;
                }

                myVariants[current] = variants[current][variant];

                h.removeCallbacksAndMessages(null);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Введіть причину Вашого вибору");
                builder.setCancelable(false);

                final EditText input = new EditText(this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Відправити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reason = input.getText().toString();

                        if (reason == null || reason.equals(""))
                            reason = "Невказано!";

                        myVariants[current].setReason(reason);

                        nextTask();

                        h.postDelayed(new Runnable() {
                            public void run() {

                                synchronized (variants) {
                                    if (!end) {
                                        --time;

                                        if (time < 0)
                                            nextTask();
                                        else
                                            timerText.setText("Час: " + time);
                                        h.postDelayed(this, delay);
                                    }
                                }
                            }

                        }, delay);
                    }
                });

                builder.show();

            }
        }
    }

    private void initialize() {
        v1 = (ImageView) findViewById(R.id.v1);
        v2 = (ImageView) findViewById(R.id.v2);
        v3 = (ImageView) findViewById(R.id.v3);
        v4 = (ImageView) findViewById(R.id.v4);

        timerText = (TextView) findViewById(R.id.timerText);
        stepText = (TextView) findViewById(R.id.stepText);
    }

    private void prepareForTheGame() {

        for(int i=0;i<MAX;i++)
            addTask(i);

    }

    private void addTask(int number){
        Variant v1 = new Variant("i"+(number+1)+"v"+1, true, (number + 1), 1);
        Variant v2 = new Variant("i"+(number+1)+"v"+2, false, (number + 1), 2);
        Variant v3 = new Variant("i"+(number+1)+"v"+3, false, (number + 1), 3);
        Variant v4 = new Variant("i"+(number+1)+"v"+4, false, (number + 1), 4);

        variants[number][0] = v1;
        variants[number][1] = v2;
        variants[number][2] = v3;
        variants[number][3] = v4;
    }

    private void randomize() {

        Random r = new Random();

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < NUMBER_OF_VARIANTS; j++) {
                int x = r.nextInt(4);
                Variant temp = variants[i][j];
                variants[i][j] = variants[i][x];
                variants[i][x] = temp;
            }

        }

        for (int i = 0; i < MAX; i++) {
            int x = r.nextInt(MAX);
            Variant[] temp = variants[i];
            variants[i] = variants[x];
            variants[x] = temp;
        }

    }
}
