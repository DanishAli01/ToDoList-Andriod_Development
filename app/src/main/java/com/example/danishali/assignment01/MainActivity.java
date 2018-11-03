package com.example.danishali.assignment01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    private Button btn;
    private Button btn_third;
    private TextView no_activities;
    private TestDBOpenHepler tdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btn.setOnClickListener(new View.OnClickListener() {
            // overridden method to handle a button click
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btn_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);

            }
        });

        no_activities.setText(tdb.totalrecords());


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected  void initialize(){

        btn = (Button) findViewById(R.id.btn);
        btn_third = (Button) findViewById(R.id.btn_third);
        no_activities = (TextView)findViewById(R.id.no_activities);
        tdb = new TestDBOpenHepler(this);

    }


}
