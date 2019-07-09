package com.example.mahmoud.salahny;

import android.content.res.Resources;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mahmoud.salahny.data.PersonDbHelper;

import java.util.ArrayList;

public class TimeLine_Page extends AppCompatActivity {

    private PersonDbHelper dbHelper;

    private MainActivity mainActivity;

    public static String personId ;
    public static String personUserName ;
    ArrayList<ArrayList> listData ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line__page);

        dbHelper = new PersonDbHelper(this);

        listData = dbHelper.getAllPosts();

        ListView listView = (ListView) findViewById(R.id.listView);

        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(listData);

        listView.setAdapter(myCustomAdapter);


        System.out.println("this is Time Line = "+listData.get(0).get(2));

    }



    class MyCustomAdapter extends BaseAdapter{

        ArrayList<ArrayList> listData = new ArrayList<ArrayList>() ;

        public MyCustomAdapter(ArrayList<ArrayList> listData) {
            this.listData = listData;
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {

            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.timeline_view,null);

            ImageView imageView = (ImageView) view1.findViewById(R.id.TV_Image);
            TextView textModel = (TextView) view1.findViewById(R.id.TV_Model);
            TextView textDesc = (TextView) view1.findViewById(R.id.TV_Desc);
//            Button ButtonView = (Button) view1.findViewById(R.id.TV_Button);

            //imageView.setImageResource(Integer.parseInt("R.drwable."+listData.get(position).get(2)));

            int id = getResources().getIdentifier((String) listData.get(position).get(2), "drawable", getPackageName());

            imageView.setImageResource(id);

            textModel.setText((String)listData.get(position).get(3));
            textDesc.setText((String)listData.get(position).get(4));


            return view1;
        }
    }


}
