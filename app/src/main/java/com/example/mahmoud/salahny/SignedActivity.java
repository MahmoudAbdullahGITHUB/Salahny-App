package com.example.mahmoud.salahny;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mahmoud.salahny.data.PersonDbHelper;

import java.util.ArrayList;

public class SignedActivity extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    private MainActivity mainActivity;

    public static String personId ;
    public static String personUserName ;

    private Button timeLineButton ;
    private Button sharePostButton ;
    private Button defineLocationButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        timeLineButton = (Button) findViewById(R.id.Time_Line_Button);
        sharePostButton = (Button) findViewById(R.id.Share_Post_Button);
        defineLocationButton = (Button) findViewById(R.id.Define_Location_Button);

        dbHelper = new PersonDbHelper(this);

        mainActivity = new MainActivity();
        personId = mainActivity.personIdRoot;

        System.out.println("myid from signedActivity = "+personId);

        /*
        if(SignedUPActivity.personId != -1){

            personId = SignedUPActivity.personId;
            personUserName = SignedUPActivity.personUserName;
            System.out.println("onCreeate if condition = "+personId);
        }
*/


        /*
        TextView textView = (TextView) findViewById(R.id.textView3);
        Bundle b =getIntent().getExtras();
        String user = b.getString("userName");
        String pass = b.getString("password");
        textView.setText("name"+user+"  ,pass"+pass);
        */


    }

    public void Time_Line_Button(View view) {
        showData();
        Intent myIntent = new Intent(this, TimeLine_Page.class);
        startActivity(myIntent);

    }


    public void Share_Post_Button(View view) {

        Intent myIntent = new Intent(this, PostSharedDetails.class);
        startActivity(myIntent);
    }

    public void Define_Location_Button(View view) {

    }

    public void showData() {
        ArrayList<ArrayList> listData = dbHelper.getAllPosts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);

        //listView.setAdapter(arrayAdapter);
    }


    public void Profile_Button(View view) {
        Intent myIntent = new Intent(this, MyProfileActivity.class);
        startActivity(myIntent);

    }
}
