package com.example.calofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationsHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "Applications.db";
    private static String DATABASE_TABLE = "ApplicationsTable";
    private static int DATABASE_VERSION = 1;

    public ApplicationsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DATABASE_VERSION);
        db.execSQL("CREATE TABLE ApplicationsTable (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "company_name TEXT, " +
                "_position TEXT, " +
                "_offer INTEGER DEFAULT 0," +
                "_interview INTEGER DEFAULT 0," +
                "_open INTEGER DEFAULT 0)");
    }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public boolean insertApplication(String company, String position, int interview, int offer, int open)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues applicationValues = new ContentValues();
        applicationValues.put("company_name", company);
        applicationValues.put("_position", position);
        applicationValues.put("_interview", interview);
        applicationValues.put("_offer", offer);
        applicationValues.put("_open", open);

        long sid = db.insert("ApplicationsTable", null, applicationValues);
        if(sid>0){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getApplication(int _id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("ApplicationsTable", new String[]{"_id",
                "company_name",
                "_position",
                "_interview",
                "_interview",
                "_offer",
                "_open"
         }, "_id=?",new String[]{ String.valueOf(_id) } , null, null, null);

        return cursor;
    };


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void updateEntry(String id, String company, String position, int interview, int offer, int open){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues applicationValues = new ContentValues();
        applicationValues.put("company_name", company);
        applicationValues.put("_position", position);
        applicationValues.put("_interview", interview);
        applicationValues.put("_offer", offer);
        applicationValues.put("_open", open);
        db.update("ApplicationsTable", applicationValues, "_id=?", new String[] {id});
    }
    public int getNumInters(){
        SQLiteDatabase db = getWritableDatabase();
        int numInts = (int)DatabaseUtils.queryNumEntries(db, "ApplicationsTable", "_interview=1");
        return numInts;


    }
    public int getNumOffers() {
        SQLiteDatabase db = getWritableDatabase();
        int numInts = (int) DatabaseUtils.queryNumEntries(db, "ApplicationsTable", "_offer=1");
        return numInts;

    }
    public int getNumOpen() {
        SQLiteDatabase db = getWritableDatabase();
        int numInts = (int) DatabaseUtils.queryNumEntries(db, "ApplicationsTable", "_open=1");
        return numInts;

    }

        public void deleteEntry(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("ApplicationsTable", "_id=?", new String[]{id});
    }
}
