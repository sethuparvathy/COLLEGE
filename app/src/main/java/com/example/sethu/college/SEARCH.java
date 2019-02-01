package com.example.sethu.college;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SEARCH extends AppCompatActivity {
    Button b,b1,b2;
    EditText e,e1,e2,e3;
    String s, s1,s2,s3,getid,newrolno,newadmno,newcolg;
    CLG database;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("are you sure want to delete ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"yes clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
                boolean status= database.deletedata(getid);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"delete successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error in delete",Toast.LENGTH_LONG).show();
                }
            }


        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"no clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });
        database = new CLG(this);
        database.getWritableDatabase();

        b = (Button) findViewById(R.id.search);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.del);
        e = (EditText) findViewById(R.id.name);
        e1=(EditText)findViewById(R.id.rno);
        e2=(EditText)findViewById(R.id.adno);
        e3=(EditText)findViewById(R.id.cg);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = e.getText().toString();
                Log.d("name", s);
                Cursor cur = database.searchdata(s);
                if (cur.getCount() == 0)
                {
                    Toast.makeText(getApplicationContext(), "NO NAME FOUND", Toast.LENGTH_LONG).show();
                } else
                {
                    while (cur.moveToNext())
                    {
                        e1.setText(s1);
                        e2.setText(s2);
                        e3.setText(s3);
                        s1 = cur.getString(2);
                        s2=cur.getString(3);
                        s3=cur.getString(4);
                        getid=cur.getString(0);

                    }
                }
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newrolno=e1.getText().toString();
                        newadmno=e2.getText().toString();
                        newcolg=e3.getText().toString();
                        boolean status=database.updatedata(getid,newrolno,newadmno,newcolg);
                        if(status==true)
                        {
                            Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"error updation",Toast.LENGTH_LONG).show();
                        }





                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();


                    }
                });


            }
        });
    }
}
