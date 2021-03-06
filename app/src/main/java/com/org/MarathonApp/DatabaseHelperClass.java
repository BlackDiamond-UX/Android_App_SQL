package com.org.MarathonApp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "candidat_database";
    //Database Table name
    private static final String TABLE_NAME = "CANDIDAT";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String VILLE ="Ville";
    public static  final  String AGE = "age";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
        " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL,"+VILLE+" TEXT NOT NULL,"+AGE+" DATE NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addCandidat(CandidatModelClass candidatModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, candidatModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL, candidatModelClass.getEmail());
        contentValues.put(DatabaseHelperClass.VILLE, candidatModelClass.getVille());
        contentValues.put(DatabaseHelperClass.AGE, candidatModelClass.getAge());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<CandidatModelClass> getCandidatList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<CandidatModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String Ville = cursor.getString(3);
                String age = cursor.getString(4);
                storeCandidat.add(new CandidatModelClass(id,name,email,Ville,age));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeCandidat;
    }

    public void updateCandidat(CandidatModelClass candidatModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, candidatModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL, candidatModelClass.getEmail());
        contentValues.put(DatabaseHelperClass.VILLE, candidatModelClass.getVille());
        contentValues.put(DatabaseHelperClass.AGE, candidatModelClass.getAge());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(candidatModelClass.getId())});
    }

    public void deleteCandidat(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
