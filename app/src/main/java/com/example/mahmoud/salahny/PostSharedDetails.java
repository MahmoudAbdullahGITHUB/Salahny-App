package com.example.mahmoud.salahny;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mahmoud.salahny.data.PersonDbHelper;

public class PostSharedDetails extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    private MainActivity mainActivity;

    //private static MainActivity mainActivity;

    private static String personId ;
    private static String personUserName ;


    private ImageView imageProblem;
    private EditText imageProblemName;
    private EditText model;
    private EditText description;
    private Button shareItButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_shared_details);

        imageProblem = (ImageView) findViewById(R.id.ImageProblem);

        imageProblemName = (EditText) findViewById(R.id.ImageProblemName);
        model = (EditText) findViewById(R.id.Model);
        description = (EditText) findViewById(R.id.Description);

        shareItButton = (Button) findViewById(R.id.ShareItButton);

        dbHelper = new PersonDbHelper(this);

        mainActivity = new MainActivity();
        personId = mainActivity.personIdRoot;
        System.out.println("myid from PostSharedActivity = "+personId);

    }


    public void ShareItButton(View view) {


        String square1 = imageProblemName.getText().toString();
        String square2 = model.getText().toString();
        String square3 = description.getText().toString();

        personId = mainActivity.personIdRoot;
        System.out.println("so "+personId);


        System.out.println("sasa ="+personId+" "+square1+" "+square2+" "+square3);

        boolean inserted = dbHelper.insertPost(personId,square1,square2,square3);

        if (inserted == true) {
            Toast.makeText(this, "inserted Post True", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "inserted Post Fasle", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
