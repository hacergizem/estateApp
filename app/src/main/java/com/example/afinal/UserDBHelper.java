package com.example.afinal;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;


public class UserDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SIFRE = "sifre";
    private static final String KEY_MAIL = "mail";
    private static final String KEY_TELEFON = "telefon";

    public UserDBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_SIFRE + " TEXT,"
                + KEY_TELEFON + " TEXT,"
                + KEY_MAIL + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }


    //Kullanıcı kaydı ekledik
    void insertUserDetails(String name, String sifre, String mail,String telefon){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_SIFRE, sifre);
        cValues.put(KEY_TELEFON,telefon);
        cValues.put(KEY_MAIL, mail);

        long newRowId = db.insert(TABLE_Users,null, cValues);
        UserProfile.userid++;
        db.close();
    }




    //Kullanıcı listesi alma
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, sifre, mail, telefon FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
            user.put("location",cursor.getString(cursor.getColumnIndex(KEY_SIFRE)));
            user.put("telefon",cursor.getString(cursor.getColumnIndex(KEY_TELEFON)));
            userList.add(user);
        }
        return  userList;
    }



    //ID ile kullanıcı alma
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, sifre, mail, telefon FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_SIFRE, KEY_MAIL,KEY_TELEFON}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("mail",cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
            user.put("sifre",cursor.getString(cursor.getColumnIndex(KEY_SIFRE)));
            user.put("telefon",cursor.getString(cursor.getColumnIndex(KEY_TELEFON)));
            userList.add(user);
        }
        return  userList;
    }

    //Kullanıcı adı şifre doğrulama
    public boolean passwordCorrection(String userid,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, sifre, mail, telefon FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_SIFRE, KEY_MAIL,KEY_TELEFON}, KEY_NAME+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            return cursor.getString(cursor.getColumnIndex(KEY_SIFRE)).equals(pass);
        }
        return  false;
    }

    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }


    public int UpdateUserDetails(String sifre, String name, String telefon, String mail, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_NAME, name);
        cVals.put(KEY_SIFRE, sifre);
        cVals.put(KEY_MAIL, mail);
        cVals.put(KEY_TELEFON, telefon);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}