package com.example.johnny.a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by MrMcM on 2017-02-20.
 */

public class TextViewController extends AppCompatActivity {





        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.textviewcontroller);

            Bundle b = getIntent().getExtras();
            TextView keystroke = (TextView) findViewById(R.id.textView3);
            TextView words = (TextView) findViewById(R.id.textView4);
            TextView min = (TextView) findViewById(R.id.textView7);
            TextView sec = (TextView) findViewById(R.id.textView8);
            TextView millisec = (TextView) findViewById(R.id.textView5);

            keystroke.setText(b.getCharSequence("keystroke"));
            words.setText(b.getCharSequence("wordcount"));
            min.setText(b.getCharSequence("minutes"));
            sec.setText(b.getCharSequence("seconds"));
            millisec.setText(b.getCharSequence("milliseconds"));

        }
    public void retry (View v)
    {
        Intent i=new Intent(getApplicationContext(),Intro.class);
        startActivity(i);
    }
}
