package com.example.sethu.college;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SEARCH extends AppCompatActivity {
    Button b;
    EditText e;
    String s, s1,s2,s3;
    CLG database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        database = new CLG(this);
        database.getWritableDatabase();

        b = (Button) findViewById(R.id.search);
        e = (EditText) findViewById(R.id.name);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = e.getText().toString();
                Log.d("name", s);
                Cursor cur = database.searchdata(s);
                if (cur.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "NO NAME FOUND", Toast.LENGTH_LONG).show();
                } else {
                    while (cur.moveToNext()) {
                        s1 = cur.getString(2);
                        Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                        s2=cur.getString(3);
                        Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
                        s3=cur.getString(4);
                        Toast.makeText(getApplicationContext(),s3,Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
