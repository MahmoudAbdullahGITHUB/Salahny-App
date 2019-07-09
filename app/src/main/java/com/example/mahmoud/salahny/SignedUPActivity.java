package com.example.mahmoud.salahny;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mahmoud.salahny.data.PersonDbHelper;

import java.util.ArrayList;

public class SignedUPActivity extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    private MainActivity mainActivity;

    public static String personId ;
    public static String personUserName ;


    private EditText name,
            mail,
            password,
            assuredPassword,
            region,
            phone_Naumber,
            address,
            gender;
    private Button submit_Button;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_up);

        dbHelper = new PersonDbHelper(this);

        name = (EditText) findViewById(R.id.editView1);
        mail = (EditText) findViewById(R.id.editView2);
        password = (EditText) findViewById(R.id.editView3);
        /* assuredPassword= (EditText) findViewById(R.id.editView4);
        region= (EditText) findViewById(R.id.editView5);
        phone_Naumber= (EditText) findViewById(R.id.editView6);
        address= (EditText) findViewById(R.id.editView7);
        gender = (EditText) findViewById(R.id.editView8); */
        submit_Button = (Button) findViewById(R.id.Submit_Button);
        listView = (ListView) findViewById(R.id.ListView1);

        mainActivity = new MainActivity();

        /*initial id for knowing if it change*/
        //personId = -1;

        showData();


    }

    public void Submit(View view) {

        ArrayList<ArrayList> listData = dbHelper.getAllRecord();
        ArrayList<String> stringsList = new ArrayList<>();
        System.out.println("my Database  ");
        System.out.println(listData);

        boolean valid = true;

        String square1 = name.getText().toString();
        String square2 = mail.getText().toString();
        String square3 = password.getText().toString();

        /**
         * firstly find if the database is empty ot not
         */
        if(listData.size()!=0){

            System.out.println(listData.get(0));
            stringsList = listData.get(0);

            String getName = stringsList.get(1);
            String getMail = stringsList.get(2);
            String getPassword = stringsList.get(3);


            for (int i = 0; i < listData.size(); i++) {
                stringsList = listData.get(i);
                getName = stringsList.get(1);
                getMail = stringsList.get(2);
                getPassword = stringsList.get(3);

                if (square1.equals(getName)) {
                    name.setError("invalid name");
                    name.setTextColor(Color.RED);
                    System.out.println("id = " + (i + 1));
                    System.out.println("Erorr ");
                    Toast.makeText(this, "name is invalid ", Toast.LENGTH_SHORT).show();
                    valid = false;
                    break;
                } else if (square1.equals("")) {
                    valid = false;
                    name.setError("Enter The Name");
                    break;
                } else if (square3.equals("")) {
                    valid = false;
                    password.setError("Enter The Password.");
                    break;
                }
            }

        }else {

            if (square1.equals("")) {
                valid = false;
                name.setError("Enter The Name");
            }else if (square3.equals("")) {
                valid = false;
                password.setError("Enter The Password.");
            }
        }


        if (valid) {
            boolean inserted = dbHelper.insertData(name.getText().toString(),
                    mail.getText().toString(),
                    password.getText().toString());
            if (inserted == true) {

                personId = dbHelper.personId;
                mainActivity.personIdRoot = personId;

                System.out.println("myid from SignedUpActivity = "+personId);

                /*personUserName = square1;
                System.out.println("sq1 = : "+personUserName);
                personId = dbHelper.personId;
                System.out.println("sq2 = : "+personId);
*/

                Toast.makeText(this, "inserted True", Toast.LENGTH_SHORT).show();
                showData();
            } else {
                Toast.makeText(this, "inserted Fasle", Toast.LENGTH_SHORT).show();
                showData();
            }



            /** empty all variables
             *
             */
            name.setText("");
            mail.setText("");
            password.setText("");


            Intent myIntent = new Intent(this, SignedActivity.class);


            Bundle b = new Bundle();
            b.putString("userName", name.getText().toString());
            b.putString("password", password.getText().toString());
            myIntent.putExtras(b);


            startActivity(myIntent);

            Toast.makeText(this, name.getText(), Toast.LENGTH_SHORT).show();

        }


    }


    public void showData() {
        ArrayList<ArrayList> listData = dbHelper.getAllRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);

        listView.setAdapter(arrayAdapter);
    }


    public void upDateButton(View view) {


    }


}
