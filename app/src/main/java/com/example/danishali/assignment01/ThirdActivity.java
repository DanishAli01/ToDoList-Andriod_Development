package com.example.danishali.assignment01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private TestDBOpenHepler tdb;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter	caa;
    private ListView third_lv_task_db;
    private EditText et_new_strings;
    private Cursor data;
    private Button home_third;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_main);

        initialize();

       data = tdb.getListContents();

        while (data.moveToNext()) {
            al_items.add(new CustomItem(data.getString(2),data.getString(1)));

        }


        third_lv_task_db.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {

                Toast.makeText(ThirdActivity.this, al_items.get(pos).getName(), Toast.LENGTH_SHORT).show();
                tdb.removeData(al_items.get(pos).getName());
                tdb.totalrecords();
                al_items.remove(new CustomItem(al_items.get(pos).getName()));
                third_lv_task_db.setAdapter(caa);
                caa.notifyDataSetChanged();

                 Toast.makeText(ThirdActivity.this, al_items.get(pos).getName()+" Deleted ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                startActivity(intent);
                intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            }

        });

        third_lv_task_db.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {


                Intent changeover = new Intent(ThirdActivity.this,EditActivity.class);
                changeover.putExtra("value",al_items.get(pos).getName());
                //Toast.makeText(ThirdActivity.this, al_items.get(pos).getName(), Toast.LENGTH_SHORT).show();
                 startActivityForResult(changeover,16);

            }
        });

        home_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent changeover = new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(changeover);
            }
        });




    }


    public void onStart() {

        super.onStart();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    protected  void initialize(){

        tdb = new TestDBOpenHepler(this);
        third_lv_task_db = (ListView) findViewById(R.id.third_lv_task_db);
        et_new_strings =(EditText)findViewById(R.id.et_new_strings);
        home_third = (Button)findViewById(R.id.home_third);
        al_items = new ArrayList<CustomItem>();
        caa = new CustomArrayAdapter(this, al_items);
        third_lv_task_db.setAdapter(caa);
        caa.notifyDataSetChanged();


    }


}
