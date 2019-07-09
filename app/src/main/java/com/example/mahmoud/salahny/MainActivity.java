package com.example.mahmoud.salahny;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmoud.salahny.data.PersonContract;
import com.example.mahmoud.salahny.data.PersonContract.PersonEntry;
import com.example.mahmoud.salahny.data.PersonDbHelper;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    public static String personIdRoot ;
    private static String personUserName ;



    private EditText userName;
    private EditText password;
    private Button buttonSubmit;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new PersonDbHelper(this);

        userName = (EditText) findViewById(R.id.edit1);
        password = (EditText) findViewById(R.id.edit2);
        buttonSubmit = (Button) findViewById(R.id.LogIn_Button);
        listView = (ListView) findViewById(R.id.ListView1);
        //addListenerOnButton();

        showData();
    }


    public void LogInClicked(View view) {


        ArrayList<ArrayList> listData = dbHelper.getAllRecord();
        ArrayList<String> stringsList = new ArrayList<>();
        System.out.println("my Database ");
        System.out.println(listData);

        String square1 = userName.getText().toString();
        String square3 = password.getText().toString();

        boolean valid = true;

        if(listData.size()!=0){

            System.out.println(listData.get(0));
            stringsList = listData.get(0);

            String getName = stringsList.get(1);
            String getPassword = stringsList.get(3);

            HashMap<String, String> map = new HashMap<String, String>();

            for (int i = 0; i < listData.size(); i++) {
                stringsList = listData.get(i);
                getName = stringsList.get(1);
                getPassword = stringsList.get(3);

                map.put(getName, getPassword);
            }

            if (square1.equals("")) {
                valid = false;
                userName.setError("Enter The Name");
            } else if (square3.equals("")) {
                valid = false;
                password.setError("Enter The Password.");
            } else if (!square3.equals(map.get(square1))) {
                userName.setError("invalid name or password");
                userName.setTextColor(Color.RED);

                Toast.makeText(this, "name is invalid ", Toast.LENGTH_SHORT).show();
                valid = false;

            } else {

                personUserName = square1;
                personIdRoot = getPersonIdRoot();

                System.out.println("myid from MainActivity = "+personIdRoot);

                /** empty all variables
                 *
                 */
                // userName.setText("");

                password.setText("");

                Intent myIntent = new Intent(this, SignedActivity.class);
                startActivity(myIntent);
                Toast.makeText(this, userName.getText(), Toast.LENGTH_SHORT).show();



            /*      Bundle b = new Bundle();
            b.putString("userName", userName.getText().toString());
            b.putString("password", password.getText().toString());
            myIntent.putExtras(b);
            */
            }
        }else {
            Toast.makeText(this, "The Database is Empty so your account is not existed ", Toast.LENGTH_SHORT).show();
            password.setText("");
        }




    }

    public void SignUpClicked(View view) {
        Intent myIntent = new Intent(this, SignedUPActivity.class);

        startActivity(myIntent);


    }


    public void showData() {
        ArrayList<ArrayList> listData = dbHelper.getAllRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);

        listView.setAdapter(arrayAdapter);
    }


    public String getPersonIdRoot(){
        System.out.println("PersonUserName= "+personUserName);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ PersonEntry.TABLE_NAME
                + " where " + PersonEntry.COLUMN_PERSON_NAME + " = '"+personUserName+"'",null);
        res.moveToFirst();

        String res2 = res.getString(0);

        System.out.println("33your id = "+personIdRoot);
        System.out.println("332your id = "+ res2.getClass().getName());
        System.out.println("33res_Cursor = "+res2);

        return res2;
    }


  /*

     // get person Id from another Activity

    public String getPersonIdRoot2(){
        return personIdRoot;
    }
*/

}












   /* private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PersonEntry._ID,
                PersonEntry.COLUMN_PERSON_NAME,
                PersonEntry.COLUMN_PERSON_MAIL,
                PersonEntry.COLUMN_PERSON_PASSWORD,
                PersonEntry.COLUMN_PERSON_REGION,
                PersonEntry.COLUMN_PERSON_PHONE,
                PersonEntry.COLUMN_PERSON_ADDRESS,
                PersonEntry.COLUMN_PERSON_GENDER};

        // Perform a query on the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContentResolver().query(
                PersonEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                null,                   // Selection criteria
                null,                   // Selection criteria
                null);                  // The sort order for the returned rows

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PersonEntry._ID + " - " +
                    PersonEntry.COLUMN_PET_NAME + " - " +
                    PersonEntry.COLUMN_PET_BREED + " - " +
                    PersonEntry.COLUMN_PET_GENDER + " - " +
                    PersonEntry.COLUMN_PET_WEIGHT + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PersonEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PersonEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(PersonEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PersonEntry.COLUMN_PET_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(PersonEntry.COLUMN_PET_WEIGHT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
*/

















/*    private void addListenerOnButton() {

        userName = (EditText) findViewById(R.id.edit1);
        password = (EditText) findViewById(R.id.edit2);
        buttonSubmit = (Button) findViewById(R.id.button);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(this,SignedActivity.class);

                Bundle b =new Bundle();
                b.putString("userName",userName.getText().toString());
                b.putString("password",password.getText().toString());
                myIntent.putExtra(b);



                startActivity(myIntent);

                Toast.makeText(MainActivity.this,userName.getText(),Toast.LENGTH_SHORT).show();
            }
        });


    }
*/










/*
        for (int i = 0; i < listData.size(); i++) {
            stringsList = listData.get(i);
            getName = stringsList.get(1);
            getPassword = stringsList.get(3);

            if ( ( !square1.equals(getName) || !square3.equals(getPassword) ) && (i == (listData.size()-1)) ) {    // المفروض هنا استخدم ال dictionary عشان ابحث بسرعة عن اليوزر نيم والباس

                userName.setText(getName);
                password.setText(getPassword);


                break;
            }
        }


        if (valid) {

            boolean inserted = dbHelper.insertData(userName.getText().toString(),
                    userName.getText().toString(),
                    password.getText().toString());
            if (inserted == true) {
                Toast.makeText(this, "inserted True", Toast.LENGTH_SHORT).show();
                showData();
            } else {
                Toast.makeText(this, "inserted Fasle", Toast.LENGTH_SHORT).show();
                showData();
            }


            /** empty all variables
             *
             */
/*            userName.setText("");
            password.setText("");


            Intent myIntent = new Intent(this, SignedActivity.class);

            Bundle b = new Bundle();
            b.putString("userName", userName.getText().toString());
            b.putString("password", password.getText().toString());
            myIntent.putExtras(b);

            startActivity(myIntent);
            Toast.makeText(this, userName.getText(), Toast.LENGTH_SHORT).show();

        }
*/




