package com.example.johnny.a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class sign_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void storedb(View view)
    {
        DBAdapter db = new DBAdapter(this);
        db.open();
        EditText n=(EditText)findViewById(R.id.editText5);
        EditText i=(EditText)findViewById(R.id.editText6);
        EditText e=(EditText)findViewById(R.id.editText7);
        EditText d=(EditText)findViewById(R.id.editText8);
        EditText ph=(EditText)findViewById(R.id.editText9);
        EditText p=(EditText)findViewById(R.id.editText12);
        EditText p2=(EditText)findViewById(R.id.editText13);
        String na=n.getText().toString();
        String id=i.getText().toString();
        String email=e.getText().toString();
        String dept=d.getText().toString();
        String phone=ph.getText().toString();
        String pass=p.getText().toString();
        String pass2=p2.getText().toString();
        if(!pass.equals(pass2))
        {
            Toast.makeText(getApplicationContext(),"Enetr correct paasword...........",Toast.LENGTH_LONG).show();
        }
        else
        {
            long idd = db.insertContact(na, id, email, dept, phone, pass);

            if (idd != 0)
                Toast.makeText(getApplicationContext(), "Data Inserted Successfully ", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getApplicationContext(), "Data could not be inserted ", Toast.LENGTH_LONG).show();
                db.close();

            }
        }
    }


}
