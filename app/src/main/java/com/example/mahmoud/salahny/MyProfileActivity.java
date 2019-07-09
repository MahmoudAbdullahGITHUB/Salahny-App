package com.example.mahmoud.salahny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mahmoud.salahny.data.PersonDbHelper;

public class MyProfileActivity extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    private MainActivity mainActivity;

    public static String personId ;
    public static String personUserName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        dbHelper = new PersonDbHelper(this);

        mainActivity = new MainActivity();

        personId = mainActivity.personIdRoot;


    }


    public void SaveChangeButton(View view) {



    }
}
