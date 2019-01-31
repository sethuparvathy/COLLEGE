package com.example.sethu.college;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2,e3,e4;
    String s1,s2,s3,s4;
    CLG mzc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.submit);
        b2=(Button)findViewById(R.id.search);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.rollno);
        e3=(EditText)findViewById(R.id.admno);
        e4=(EditText)findViewById(R.id.college);
        mzc=new CLG(this);
        mzc.getWritableDatabase();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                Log.d("name",s1);
                Log.d("rollno",s2);
                Log.d("admno",s3);
                Log.d("college",s4);


                boolean status=mzc.insertData(s1,s2,s3,s4);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"successfully insert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                }



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SEARCH.class);
                startActivity(i);
            }
        });




    }
}
