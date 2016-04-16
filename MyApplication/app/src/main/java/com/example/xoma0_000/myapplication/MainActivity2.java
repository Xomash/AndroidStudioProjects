package com.example.xoma0_000.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by xoma0_000 on 19.01.2016.
 */
public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        final Button button = (Button) findViewById(R.id.animprog);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button b1 = (Button)findViewById(R.id.animprog);
                ObjectAnimator anim = ObjectAnimator.ofFloat(b1, "alpha", 1f, 0.5f);
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(b1, "rotationY", 180f, 0f);
                anim.setDuration(5000);
                // anim.start();
                anim.start();
                Log.d("olehd", "Program anim loaded");
            }
        });
        Log.d("olehdd","lisener attached");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //setContentView(R.layout.activity_main);
        return true;
    }
    View.OnClickListener clicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b1 = (Button)findViewById(R.id.animprog);
            ObjectAnimator anim = ObjectAnimator.ofFloat(b1, "alpha", 0f, 1f);
            anim.setDuration(1000);
            anim.start();
            Log.d("olehd", "Program anim loaded");
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
