package com.example.xoma0_000.myapplication;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
	import android.view.animation.Animation;
	import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    final int MENU_ALPHA_ID = 1;
    Button fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView tv = (TextView) findViewById(R.id.tv);
        final Animation  anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        final Button button = (Button) findViewById(R.id.animprog);
        final Button button1 = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.act);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final RelativeLayout rl = (RelativeLayout) findViewById (R.id.main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rl.startAnimation(anim);
            }
        });
    }
}
