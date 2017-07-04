package com.example.johnny.a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Rakesh on 03-05-2017.
 */

public class Intro extends AppCompatActivity {

    Button start;
    Button start2;
    Button start3;
    EditText t1;
    String time;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

    }


    public void start_click1(View view)
    {



        Bundle bun = new Bundle();
        Intent intent = new Intent(Intro.this, MainActivity.class);

        //Inserts a String value into the mapping of this Bundle
        bun.putString("lvl1", "level1");
        bun.putString("lvl2", "null");
        bun.putString("lvl3", "null");





        //Add the bundle to the intent.
        intent.putExtras(bun);


        //Intent intent = new Intent(MainActivity.this, TextViewController.class);
        startActivity(intent);
   //     Intent intent = new Intent(Intro.this, MainActivity.class);

    }

    public void start_click2(View view)
    {



        Bundle bun = new Bundle();
        Intent intent = new Intent(Intro.this, MainActivity.class);

        //Inserts a String value into the mapping of this Bundle
        bun.putString("lvl2", "level2");
        bun.putString("lvl1", "null");
        bun.putString("lvl3", "null");





        //Add the bundle to the intent.
        intent.putExtras(bun);


        //Intent intent = new Intent(MainActivity.this, TextViewController.class);
        startActivity(intent);
        //     Intent intent = new Intent(Intro.this, MainActivity.class);

    }
    public void start_click3(View view)
    {



        Bundle bun = new Bundle();
        Intent intent = new Intent(Intro.this, MainActivity.class);

        //Inserts a String value into the mapping of this Bundle
        bun.putString("lvl3","level3" );
        bun.putString("lvl1", "null");
        bun.putString("lvl2", "null");





        //Add the bundle to the intent.
        intent.putExtras(bun);


        //Intent intent = new Intent(MainActivity.this, TextViewController.class);
        startActivity(intent);
        //     Intent intent = new Intent(Intro.this, MainActivity.class);

    }
}


