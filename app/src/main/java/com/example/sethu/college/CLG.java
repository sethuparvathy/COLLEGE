package com.example.sethu.college;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 1/29/2019.
 */
public class CLG extends SQLiteOpenHelper {
    public static final String Dbname="MyDb.db";
    public static final String Tablename="C0llege";
    public static final String c1="id";
    public static final String c2="name";
    public static final String c3="rollno";
    public static final String c4="admno";
    public static final String c5="college";
    public CLG(Context context ) {

super(context, Dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String query="create table " +Tablename+"(" +c1+" integer primary key autoincrement, "+c2+" text,"+c3+" text,"+c4+" text,"+c5+" text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="drop table if exists " +Tablename;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);


    }
    public boolean insertData(String name,String rollno,String admno,String college)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(c2,name);
        cv.put(c3,rollno);
        cv.put(c4,admno);
        cv.put(c5,college);
        Long status=sqLiteDatabase.insert(Tablename,null,cv);
        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor searchdata(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+Tablename+" WHERE "+c2+ "='"+name+"'", null );
        return cur;
    }
}
