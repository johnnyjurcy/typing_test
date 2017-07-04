package com.example.johnny.a;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Random rand;
    private final String[] words3 = new String[]{"Hi guys help me","Doing android project","Stack overflow","Github is great"};
    private final String[] words2=new String[]{"jkgjkdf","sdhfh","sdgfjsdhfsd"};
    private final String[] words=new String[]{"jkgjkdsfdf","ssdfsdfdhfh","sdgfjdsfdsfssdhfsd"};
    private int flag = 0; //0 words, 1 phrase
    private String temp; //Used for temporary comparisons from editext to displayed text
    private int len, prev, next;
    private String current;
    private SpannableString span;
    private EditText edit;
    private TextView text,text2,text3;
    private Button stop;
    public int keystroke=0;
    public int word_count=0;
    public int flag3=0;

    private Handler customHandler=new Handler() ;
    private int flag2 =0;

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    long millis;
    int seconds;
    int minutes;
    int milliseconds;
    Bundle bun2;
    String s2;
    String s3;
    String s4;
    int min_entrd;
    int sec_entrd;
    int level;




    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {



                millis = System.currentTimeMillis() - startTime;
                seconds = (int) (millis / 1000);
                minutes = seconds / 60;
                seconds = seconds % 60;
                milliseconds = (int) (millis % 1000);


                text2.setText(String.format("%d:%02d:%03d", minutes, seconds, milliseconds));

                timerHandler.postDelayed(this, 500);


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collect();


        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        /*

      bun2=getIntent().getExtras();
        s2=bun2.getCharSequence("minutes").toString();
        s3=Character.toString(s2.charAt(0))+Character.toString(s2.charAt(1));
        s4=Character.toString(s2.charAt(3))+Character.toString(s2.charAt(4));
        min_entrd=Integer.parseInt(s2);
        sec_entrd=Integer.parseInt(s4);
*/


        // customHandler=new Handler();
        rand = new Random();

        if(flag == 0 ) workingWithWords();
        else workingWithPhrases();

        Button b = (Button) findViewById(R.id.button5);
       // b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
               // if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                   // b.setText("start");
                    Bundle bun = new Bundle();
                    Intent intent = new Intent(MainActivity.this, TextViewController.class);

                    //Inserts a String value into the mapping of this Bundle
                    bun.putString("keystroke", Integer.toString(keystroke));
                    bun.putString("wordcount", Integer.toString(word_count));
                    bun.putString("minutes", Integer.toString(minutes));
                    bun.putString("seconds", Integer.toString(seconds));
                    bun.putString("milliseconds", Integer.toString(milliseconds));





                    //Add the bundle to the intent.
                    intent.putExtras(bun);


                    //Intent intent = new Intent(MainActivity.this, TextViewController.class);
                    startActivity(intent);

               // }
          //  else {

              //      startTime = System.currentTimeMillis();
             //       timerHandler.postDelayed(timerRunnable, 0);
              //      b.setText("stop");
              //  }


            }
        });

    }
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button5);
        b.setText("start");



    }






    private void workingWithWords()
    {
        if(level==1) {
            prev = rand.nextInt(words.length);
            next = prev;

            current = words[next];

            span = new SpannableString(current);

            createGUI();
            initListener();
        }
        if(level==2) {
            prev = rand.nextInt(words2.length);
            next = prev;

            current = words2[next];

            span = new SpannableString(current);

            createGUI();
            initListener();
        }
        if(level==3) {
            prev = rand.nextInt(words3.length);
            next = prev;

            current = words3[next];

            span = new SpannableString(current);

            createGUI();
            initListener();
        }
    }

    private void workingWithPhrases()
    {

    }

    public void collect()
    {
        Bundle bun_collect=getIntent().getExtras();
        String s1=bun_collect.getCharSequence("lvl1").toString();
        String s2=bun_collect.getCharSequence("lvl2").toString();
        String s3=bun_collect.getCharSequence("lvl3").toString();
        if(s1.equals("level1")&&s2.equals("null")&&s3.equals("null"))
        {
            level=1;
        }
        if(s2.equals("level2")&&s1.equals("null")&&s3.equals("null"))
        {
            level=2;
        }
        if(s3.equals("level3")&&s2.equals("null")&&s1.equals("null"))
        {
            level=3;
        }
    }
    private void createGUI()
    {
        text = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView);
        text3 = (TextView) findViewById(R.id.textView2);
        edit = (EditText) findViewById(R.id.editText);
        span.setSpan(new ForegroundColorSpan(Color.BLACK),0,current.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text.setText(span);
    }

    /**
     * Creates handler for keypress events
     */
    private void initListener()
    {


        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                keystroke++;

                Log.d("listen","after");
                //text3.setText(Integer.toString(keystroke));





                //Get what the user has typed in
                temp = edit.getText().toString();
                len = temp.length();

                //Word had been completely typed by user
                if(temp.equals(current)) {
                    String[] splitStr = current.split("\\s+");
                    word_count+=splitStr.length;
                    text3.setText(Integer.toString(word_count));




                    newWord();
                }

                //Word is incomplete
                else if(len <= current.length() && current.substring(0,len).equals(temp))
                    updateDisplay();

                text.setText(span);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
    }

    private void newWord()
    {
        updateWord();
        span = new SpannableString(current);
        span.setSpan(new ForegroundColorSpan(Color.BLACK),0,current.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        edit.setText("");
    }

    private void updateWord()
    {
        if(level==1) {
            do next = rand.nextInt(words.length);
            while (next == prev);

            prev = next;

            current = words[next];
        }
        if(level==2)
        {
            do next = rand.nextInt(words2.length);
            while (next == prev);

            prev = next;

            current = words2[next];
        }
        if (level==3)
        {
            do next = rand.nextInt(words3.length);
            while (next == prev);

            prev = next;

            current = words3[next];
        }
    }

    private void updateDisplay()
    {
        span.setSpan(new ForegroundColorSpan(Color.BLACK),0,current.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.GREEN),0,len, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
