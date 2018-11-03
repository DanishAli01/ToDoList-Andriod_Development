package com.example.danishali.assignment01;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private CustomArrayAdapter.ViewHolder viewHolder;
    private ListView lv_mainlist;
    private EditText et_new_strings;
    private EditText et_title;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter	caa;
    private TestDBOpenHepler tdb;
    private Button add_btn;
    private TextView add_task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);

        initialize();




        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_title.getText().toString().matches("")||et_new_strings.getText().toString().matches("")
                        ){

                    et_new_strings.setHintTextColor(Color.RED);
                    et_title.setHintTextColor(Color.RED);
                    Animation shake = AnimationUtils.loadAnimation(SecondActivity.this, R.anim.shake);
                    et_title.startAnimation(shake);
                    et_new_strings.startAnimation(shake);
                    Toast.makeText(SecondActivity.this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();

                }
                else {
                   if (tdb.addData(et_title.getText().toString(), et_new_strings.getText().toString())) {


                       tdb.totalrecords();
                       al_items.add(new CustomItem(et_title.getText().toString(), System.currentTimeMillis()));
                       caa.notifyDataSetChanged();
                       InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                       add_task.setText("Task Added");
                       Intent i = new Intent(SecondActivity.this, MainActivity.class);
                       startActivity(i);
                   }
                   else{
                       Animation shake = AnimationUtils.loadAnimation(SecondActivity.this, R.anim.shake);
                       et_title.startAnimation(shake);
                       et_title.setTextColor(Color.RED);
                       Toast.makeText(SecondActivity.this, "Title Already Exists, Please Modify Existing Title", Toast.LENGTH_SHORT).show();

                   }
                }
            }
        });


        lv_mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int pos, long id) {


                Toast.makeText(SecondActivity.this,"Task "+al_items.get(pos).getName() + " to be added ", Toast.LENGTH_SHORT).show();

            }
        });

        lv_mainlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {

                Toast.makeText(SecondActivity.this, et_new_strings.getText()+" at "+al_items.get(pos).getDate(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings_three) {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_settings_two){
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_settings_one){
            tdb.clearDatabase(tdb.TABLE_NAME);
            tdb.addData("Title","Body");
            tdb.addData("Sec_Title", "Sec_Body");
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    protected  void initialize(){

        tdb = new TestDBOpenHepler(this);
        lv_mainlist = (ListView)findViewById(R.id.lv_mainlist);
        et_new_strings =(EditText)findViewById(R.id.et_new_strings);
        al_items = new ArrayList<CustomItem>();
        caa= new CustomArrayAdapter(this,al_items);
        et_title = (EditText)findViewById(R.id.et_title);
        add_btn = (Button)findViewById(R.id.add_btn);
        add_task = (TextView)findViewById(R.id.add_task);
        lv_mainlist.setAdapter(caa);
        add_task.setText("Task To Be Added");

    }
}
