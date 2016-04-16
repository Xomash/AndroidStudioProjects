package xoma.labkhomandiak05;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/**Програма виводить на екран скільки пальців його торкається */
public class MainActivity extends AppCompatActivity {

    GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gd = new GestureDetector(getApplicationContext(), a);
        gd.setIsLongpressEnabled(true);
    }
    long before=0;
    long after=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TextView t = (TextView) findViewById(R.id.textView);
        if(MotionEvent.ACTION_UP != event.getAction())
            t.setText(Integer.toString(event.getPointerCount()));
        else
            t.setText("");
        /*if(MotionEvent.ACTION_DOWN == event.getAction())
            before = System.nanoTime();
        if(MotionEvent.ACTION_UP== event.getAction()){
            after = System.nanoTime();
            long elapsed = after - before;
            t.setText(Long.toString(elapsed / 1000000000));}*/
        return gd.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener a = new GestureDetector.SimpleOnGestureListener(){
    };

}
