package com.android.qurhad.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Ayyu Andhysa on 8/17/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    //declare place of database
    private static String DB_Path= "/data/data/com.android.qurhad/";
    private static String DB_Name= "qurhad.sqlite";
    private SQLiteDatabase qurhadDatabase;
    private final Context qurhadContext;

    public DatabaseHelper(Context context){
        super(context, DB_Name,null,1);
        this.qurhadContext = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db){}

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try{
            String thePath = DB_Path + DB_Name;
            checkDB = SQLiteDatabase.openDatabase(thePath,null,SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e){

        }
        if (checkDB != null) checkDB.close();
        return checkDB !=null ? true : false;
    }

    private void copyDatabase() throws IOException {
        InputStream myInput= qurhadContext.getAssets().open(DB_Name);
        String outFileName=DB_Path+DB_Name;
        OutputStream myOutput=new FileOutputStream(outFileName);
        byte[] buffer=new byte[1024];
        int length;
        while((length =myInput.read(buffer))>0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException{
        String thePath = DB_Path +DB_Name;
        qurhadDatabase = SQLiteDatabase.openDatabase(thePath,null, SQLiteDatabase.OPEN_READWRITE);
    }

//    public  void  ExeSQLData(String sql) throws SQLException{
//        qurhadDatabase.execSQL(sql);
//    }

    public Cursor QueryData(String query) throws SQLException{
        return qurhadDatabase.rawQuery(query,null);
    }

    @Override
    public synchronized void close(){
        if (qurhadDatabase != null)
            qurhadDatabase.close();
        super.close();
    }
    public void checkAndCopyDatabase(){
        boolean dbExist=checkDatabase();
        if (dbExist){
            Log.d("TAG","Database sudah ada");
        }else{
            this.getReadableDatabase();
            try {
                    copyDatabase();
                } catch (IOException e){
                Log.d("TAG","Tidak bisa mengkopi database");
            }
        }
    }


}
