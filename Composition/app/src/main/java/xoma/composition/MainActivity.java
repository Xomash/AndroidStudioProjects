package xoma.composition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button elimination;
    Button recognition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button shultz = (Button) findViewById(R.id.button);
        elimination = (Button) findViewById(R.id.button2);
        recognition = (Button) findViewById(R.id.button3);


        if (shultz != null) {
            shultz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(MainActivity.this, Shultz.class);
                    MainActivity.this.startActivity(myIntent);
                }
            });
        }

        elimination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Elimination.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        recognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Recognition.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
