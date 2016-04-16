package xoma.myapplication02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        final Button b = (Button) findViewById(R.id.button);
        final GridLayout l = (GridLayout) findViewById(R.id.main);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                for(int i=0; i<l.getChildCount(); i++)
                l.getChildAt(i).startAnimation(anim);
            }
        });
    }

}
