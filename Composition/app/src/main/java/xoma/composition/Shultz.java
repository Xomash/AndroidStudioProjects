package xoma.composition;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shultz extends AppCompatActivity {
    ImageButton one;
    ImageButton two;
    ImageButton three;
    ImageButton four;
    ImageButton five;
    ImageButton six;
    ImageButton seven;
    ImageButton eight;
    ImageButton nine;
    ImageButton ten;
    ImageButton eleven;
    ImageButton twelve;
    ImageButton thirteen;
    ImageButton fourteen;
    ImageButton fifteen;
    ImageButton sixteen;
    ImageView restart;
    ImageView _start;
    ArrayList<Integer> list;
    List<ImageButton> imageButtonList;
    int errors;
    long start;
    long end;
    long time;
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shultz);
        initialize();
        make(imageButtonList, list);
    }


    public void make(List<ImageButton> buttonList, final List<Integer> list){

        int i = 0;
        int x = 0;
        for(ImageButton imageButton : buttonList){
            switch(++i){
                case 1:
                    x = R.mipmap.one;
                    break;
                case 2:
                    x = R.mipmap.two;
                    break;
                case 3:
                    x = R.mipmap.three;
                    break;
                case 4:
                    x = R.mipmap.four;
                    break;
                case 5:
                    x = R.mipmap.five;
                    break;
                case 6:
                    x = R.mipmap.six;
                    break;
                case 7:
                    x = R.mipmap.seven;
                    break;
                case 8:
                    x = R.mipmap.eight;
                    break;
                case 9:
                    x = R.mipmap.nine;
                    break;
                case 10:
                    x = R.mipmap.ten;
                    break;
                case 11:
                    x = R.mipmap.eleven;
                    break;
                case 12:
                    x = R.mipmap.twelve;
                    break;
                case 13:
                    x = R.mipmap.thirteen;
                    break;
                case 14:
                    x = R.mipmap.fourteen;
                    break;
                case 15:
                    x = R.mipmap.fifteen;
                    break;
                case 16:
                    x = R.mipmap.sixteen;
                    break;
            }
            imageButton.setImageResource(x);
            imageButton.setBackgroundColor(Color.LTGRAY);
        }
        imageButtonList.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.isEmpty()) {
                    imageButtonList.get(0).setBackgroundColor(Color.GREEN);
                    list.add(1);
                }
                else{
                    imageButtonList.get(0).setBackgroundColor(Color.RED);
                    errors++;
                }
                resetColor(imageButtonList.get(0));
            }
        });
        imageButtonList.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 1) {
                        imageButtonList.get(1).setBackgroundColor(Color.GREEN);
                        list.add(2);
                    }
                    else{
                        imageButtonList.get(1).setBackgroundColor(Color.RED);
                        errors++;
                    }
                resetColor(imageButtonList.get(1));
            }
        });
        imageButtonList.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 2){
                        imageButtonList.get(2).setBackgroundColor(Color.GREEN);
                        list.add(3);
                    }
                    else{
                        errors++;
                        imageButtonList.get(2).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(2));
            }
        });
        imageButtonList.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 3){
                        imageButtonList.get(3).setBackgroundColor(Color.GREEN);
                        list.add(4);
                    }
                    else{
                        errors++;
                        imageButtonList.get(3).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(3));
            }
        });
        imageButtonList.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 4){
                        imageButtonList.get(4).setBackgroundColor(Color.GREEN);
                        list.add(5);
                    }else{
                        errors++;
                        imageButtonList.get(4).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(4));}
        });
        imageButtonList.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 5){
                        imageButtonList.get(5).setBackgroundColor(Color.GREEN);
                        list.add(6);
                    }else{
                        errors++;
                        imageButtonList.get(5).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(5));}
        });
        imageButtonList.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 6){
                        imageButtonList.get(6).setBackgroundColor(Color.GREEN);
                        list.add(7);
                    }else{
                        errors++;
                        imageButtonList.get(6).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(6));}
        });
        imageButtonList.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 7){
                        imageButtonList.get(7).setBackgroundColor(Color.GREEN);
                        list.add(8);
                    }else{
                        errors++;
                        imageButtonList.get(7).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(7));}
        });
        imageButtonList.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 8){
                        imageButtonList.get(8).setBackgroundColor(Color.GREEN);
                        list.add(9);
                    }else{
                        errors++;
                        imageButtonList.get(8).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(8));}
        });
        imageButtonList.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 9){
                        imageButtonList.get(9).setBackgroundColor(Color.GREEN);
                        list.add(10);
                    }else{
                        errors++;
                        imageButtonList.get(9).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(9));}
        });
        imageButtonList.get(10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 10){
                        imageButtonList.get(10).setBackgroundColor(Color.GREEN);
                        list.add(11);
                    }else{
                        errors++;
                        imageButtonList.get(10).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(10));}
        });
        imageButtonList.get(11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 11){
                        imageButtonList.get(11).setBackgroundColor(Color.GREEN);
                        list.add(12);
                    }else{
                        errors++;
                        imageButtonList.get(11).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(11));}
        });
        imageButtonList.get(12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 12){
                        imageButtonList.get(12).setBackgroundColor(Color.GREEN);
                        list.add(13);
                    }else{
                        errors++;
                        imageButtonList.get(12).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(12));
            }
        });
        imageButtonList.get(13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 13){
                        imageButtonList.get(13).setBackgroundColor(Color.GREEN);
                        list.add(14);
                    }else{
                        errors++;
                        imageButtonList.get(13).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(13));}
        });
        imageButtonList.get(14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 14){
                        imageButtonList.get(14).setBackgroundColor(Color.GREEN);
                        list.add(15);
                    }else{
                        errors++;
                        imageButtonList.get(14).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(14));}
        });
        imageButtonList.get(15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty())
                    if (list.get(list.size() - 1) == 15) {
                        end = System.nanoTime();
                        imageButtonList.get(15).setBackgroundColor(Color.GREEN);
                        list.add(16);
                        list.clear();
                        time = (end-start) / 1000000000;
                        Toast.makeText(getApplicationContext(), "Your time: "+time+"s", Toast.LENGTH_SHORT).show();
                        Client client = new Client();
                        client.execute();
                    }
                    else{
                        errors++;
                        imageButtonList.get(15).setBackgroundColor(Color.RED);
                    }
                resetColor(imageButtonList.get(15));
            }
        });
        start = System.nanoTime();

    }

    public void resetColor(final ImageButton imageButton){
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.i("seconds remaining: ", ""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                imageButton.setBackgroundColor(Color.LTGRAY);
            }
        }.start();
    }

    public void initialize(){

        list = new ArrayList<>();
        errors = 0;
        restart = (ImageView) findViewById(R.id.imageButton17);
        if (restart != null) {
            restart.setImageResource(R.mipmap.restart);
        }
        _start = (ImageView) findViewById(R.id.imageView);
        if (_start != null) {
            _start.setImageResource(R.mipmap.start);
        }
        imageButtonList  = new ArrayList<>();
        one = (ImageButton) findViewById(R.id.imageButton);
        two = (ImageButton) findViewById(R.id.imageButton2);
        three = (ImageButton) findViewById(R.id.imageButton3);
        four = (ImageButton) findViewById(R.id.imageButton4);
        five = (ImageButton) findViewById(R.id.imageButton5);
        six = (ImageButton) findViewById(R.id.imageButton6);
        seven = (ImageButton) findViewById(R.id.imageButton7);
        eight = (ImageButton) findViewById(R.id.imageButton8);
        nine = (ImageButton) findViewById(R.id.imageButton9);
        ten = (ImageButton) findViewById(R.id.imageButton10);
        eleven = (ImageButton) findViewById(R.id.imageButton11);
        twelve = (ImageButton) findViewById(R.id.imageButton12);
        thirteen = (ImageButton) findViewById(R.id.imageButton13);
        fourteen = (ImageButton) findViewById(R.id.imageButton14);
        fifteen = (ImageButton) findViewById(R.id.imageButton15);
        sixteen = (ImageButton) findViewById(R.id.imageButton16);

        imageButtonList.add(one);
        imageButtonList.add(two);
        imageButtonList.add(three);
        imageButtonList.add(four);
        imageButtonList.add(five);
        imageButtonList.add(six);
        imageButtonList.add(seven);
        imageButtonList.add(eight);
        imageButtonList.add(nine);
        imageButtonList.add(ten);
        imageButtonList.add(eleven);
        imageButtonList.add(twelve);
        imageButtonList.add(thirteen);
        imageButtonList.add(fourteen);
        imageButtonList.add(fifteen);
        imageButtonList.add(sixteen);
        Collections.shuffle(imageButtonList);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                Collections.shuffle(imageButtonList);
                make(imageButtonList, list);
                Toast.makeText(getApplicationContext(), "Restarted", Toast.LENGTH_SHORT).show();
            }
        });
        _start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Shultz.this, MainActivity.class);
                Shultz.this.startActivity(myIntent);
            }
        });
        if(list.size()>=15){
            time = (end-start) / 1000000000;
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Тест пройдено!, Ваш час: "+time+"c.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getApplicationContext(), "Кінець!", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(Shultz.this, MainActivity.class);
                            Shultz.this.startActivity(myIntent);
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

                client = new Socket("192.168.219.1", 3333); // connect to the server

                out = new ObjectOutputStream(client.getOutputStream());
                out.writeInt(errors);
                out.writeLong(time);
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
}
