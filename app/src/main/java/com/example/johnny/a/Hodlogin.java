package com.example.johnny.a;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Hodlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodlogin);
    }


    public void hodsignin(View view)
    {
        String p1=new String();
        EditText n=(EditText)findViewById(R.id.editText);
        EditText p=(EditText)findViewById(R.id.editText2);
        String name=n.getText().toString();
        String pass=p.getText().toString();
        if(name.equals("")||pass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please enter the login name and password.........",Toast.LENGTH_LONG).show();
        }
        else
        {
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor c = db.getnamepass();
            if (c.moveToFirst()) {
                do {
                    if (c.getString(1).equals(name)) {
                        p1 = c.getString(6);
                        break;
                    }
                } while (c.moveToNext());
            }
            db.close();
        if(pass.equals(p1) )
        {
            Toast.makeText(getApplicationContext(),"Login sucessful.....",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplication(),Intro.class);
            startActivity(i);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Incorrect password......",Toast.LENGTH_SHORT).show();
        }
    }}

        public void  sign_up(View v)
        {
            Intent i=new Intent(getApplication(),sign_in.class);
            startActivity(i);
        }
}
