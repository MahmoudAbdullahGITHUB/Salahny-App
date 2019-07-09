package com.example.mahmoud.salahny.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mahmoud.salahny.data.PersonContract.PersonEntry;
import com.example.mahmoud.salahny.data.PersonContract.PostEntry;

import java.util.ArrayList;

public class PersonDbHelper extends SQLiteOpenHelper {


    // why i made it static
    public static String personId ;
    public static String personUserName ;


    /** Name of the database file */
    private static final String DATABASE_NAME = "User.db";
  //  private static final String DATABASE_NAME_2 = "Post.db";


    /** Database version. If you change the database schema, you must increment the database version. */

    private static final int DATABASE_VERSION = 1;
  //  private static final int DATABASE_VERSION_2 = 1;

    public PersonDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

/*    public PersonDbHelper(Context context,int DatabaseNumber) {
        super(context, DATABASE_NAME_2, null, DATABASE_VERSION_2);
    }
*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PERSONS_TABLE =  "CREATE TABLE " + PersonEntry.TABLE_NAME + " ("
                + PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PersonEntry.COLUMN_PERSON_NAME+ " TEXT NOT NULL, "
                + PersonEntry.COLUMN_PERSON_MAIL+" TEXT, "
                + PersonEntry.COLUMN_PERSON_PASSWORD+" TEXT ); ";


        String SQL_CREATE_POSTS_TABLE =  "CREATE TABLE " + PostEntry.TABLE_NAME + " ("
                + PostEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PostEntry.COLUMN_POST_OWNER_ID+ " TEXT, "
                + PostEntry.COLUMN_POST_IMAGE+" TEXT, "
                + PostEntry.COLUMN_POST_MODEL+ " TEXT NOT NULL, "
                + PostEntry.COLUMN_POST_DESCRIPTION+" TEXT ); ";

                /*+ PostEntry.COLUMN_POST_REGION+" TEXT, "
                + PostEntry.COLUMN_POST_DATE+" TEXT ); "; */

        db.execSQL(SQL_CREATE_PERSONS_TABLE);

        db.execSQL(SQL_CREATE_POSTS_TABLE);

    }


    public boolean insertData(String name , String mail , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PersonEntry.COLUMN_PERSON_NAME,name);
        contentValues.put(PersonEntry.COLUMN_PERSON_MAIL,mail);
        contentValues.put(PersonEntry.COLUMN_PERSON_PASSWORD,password);

        System.out.println("Content : "+contentValues);

        long Id = db.insert(PersonEntry.TABLE_NAME,null,contentValues);

        personId = Long.toString(Id);

        //String s = Long.toString(Id);

        System.out.println("inside insertdata id= "+personId);
        //System.out.println("inside insertdata id= "+s);

        personUserName = getPersonName();

        db.close();

        if (personId=="-1")
            return false;
        else
            return true;
    }


    public boolean insertPost( String personId ,String imageName , String model , String description ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PostEntry.COLUMN_POST_OWNER_ID,personId);

        contentValues.put(PostEntry.COLUMN_POST_IMAGE,imageName);
        contentValues.put(PostEntry.COLUMN_POST_MODEL,model);
        contentValues.put(PostEntry.COLUMN_POST_DESCRIPTION,description);

        System.out.println("Content22 : "+contentValues);

        long result = db.insert(PostEntry.TABLE_NAME,null,contentValues);

        System.out.println("mamo result = "+result);

        db.close();

        if (result==-1)
            return false;
        else
            return true;
    }




    public ArrayList getAllRecord(){
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PersonEntry.TABLE_NAME,null);
        res.moveToFirst();

        while (res.isAfterLast()==false){
            String t0 = res.getString(0);
            String t1 = res.getString(1);
            String t2 = res.getString(2);
            String t3 = res.getString(3);
            //arrayList.add(t0+"  -  "+t1+"  -  "+t2+"  -  "+t3+"  -  ");
            arrayList.add(t0);
            arrayList.add(t1);
            arrayList.add(t2);
            arrayList.add(t3);
            arrayList2.add(arrayList);
            arrayList = new ArrayList();
            res.moveToNext();
        }

        System.out.println("my list1 "+arrayList);

        res.close();

         return arrayList2;
    }


    public ArrayList getAllPosts(){
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PostEntry.TABLE_NAME,null);
        res.moveToFirst();

        while (res.isAfterLast()==false){
            String t0 = res.getString(0);
            String t1 = res.getString(1);
            String t2 = res.getString(2);
            String t3 = res.getString(3);
            String t4 = res.getString(4);
            //arrayList.add(t0+"  -  "+t1+"  -  "+t2+"  -  "+t3+"  -  ");
            arrayList.add(t0);
            arrayList.add(t1);
            arrayList.add(t2);
            arrayList.add(t3);
            arrayList.add(t4);
            arrayList2.add(arrayList);
            arrayList = new ArrayList();
            res.moveToNext();
        }

        System.out.println("my list2 Posts "+arrayList2);

        res.close();

        return arrayList2;

    }


/*
    public boolean updateData(String id, String name , String mail , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PersonEntry.COLUMN_PERSON_NAME,name);
        contentValues.put(PersonEntry.COLUMN_PERSON_MAIL,mail);
        contentValues.put(PersonEntry.COLUMN_PERSON_PASSWORD,password);


        db.update(PersonEntry.TABLE_NAME,contentValues,"id= ?" , new String[]{id} );

        db.close();

        return true;
    }
*/


    public boolean updateData(String his_Id,String name , String mail , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PersonEntry.COLUMN_PERSON_NAME,name);
        contentValues.put(PersonEntry.COLUMN_PERSON_MAIL,mail);
        contentValues.put(PersonEntry.COLUMN_PERSON_PASSWORD,password);

        System.out.println("Content : "+contentValues);

        //long Id = db.insert(PersonEntry.TABLE_NAME,null,contentValues);

        long Id = db.update(PersonEntry.TABLE_NAME,contentValues,"\""+PersonEntry._ID+" = ?",new String[] {his_Id} );

        personId = Long.toString(Id);

        //String s = Long.toString(Id);

        System.out.println("inside insertdata id= "+personId);
        //System.out.println("inside insertdata id= "+s);

        personUserName = getPersonName();

        db.close();

        if (personId=="-1")
            return false;
        else
            return true;

    }










    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("________________________________________");
        System.out.println("version " +newVersion);

        db.execSQL("DROP TABLE IF EXISTS "+PersonEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS "+PostEntry.TABLE_NAME);
        onCreate(db);

    }




    public String getPersonName(){

        System.out.println("inside insertdata2 id= "+personId);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+PersonEntry.TABLE_NAME + " where " + PersonEntry._ID + " = "+personId ,null);

        res.moveToFirst();

        String res2 = res.getString(1);
        System.out.println("your id type = "+res.getString(0).getClass().getName());
        System.out.println("res_Cursor = "+res2);
        //System.out.println("res_Cursor = "+res.getString(1));

        return res2;
    }




}










/*
String SQL_CREATE_PERSONS_TABLE =  "CREATE TABLE " + PersonEntry.TABLE_NAME + " ("
                + PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PersonEntry.COLUMN_PERSON_NAME+ " TEXT NOT NULL, "
                + PersonEntry.COLUMN_PERSON_MAIL+"TEXT,"
                + PersonEntry.COLUMN_PERSON_PASSWORD+"TEXT,"
                + PersonEntry.COLUMN_PERSON_REGION+"TEXT,"
                + PersonEntry.COLUMN_PERSON_PHONE+"INTEGER,"
                + PersonEntry.COLUMN_PERSON_ADDRESS+"TEXT,"
                + PersonEntry.COLUMN_PERSON_GENDER+" INTEGER NOT NULL );";


 */