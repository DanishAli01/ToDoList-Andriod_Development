package com.example.danishali.assignment01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class EditActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main);

        initialize();

        intent = getIntent();

        value = intent.getStringExtra("value");

          existing_value = tdb.getitem(value);

        edit_title.setText(value);
        editText.setText(existing_value);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edit_title.getText().toString().matches("")||editText.getText().toString().matches("")){

                    edit_title.setHintTextColor(Color.RED);
                    editText.setHintTextColor(Color.RED);
                    editText.setHint("Cannot be Empty");
                    Animation shake = AnimationUtils.loadAnimation(EditActivity.this, R.anim.shake);
                    editText.startAnimation(shake);
                    edit_title.startAnimation(shake);
                    Toast.makeText(EditActivity.this, "Fields Cannot be Empty", Toast.LENGTH_SHORT).show();

                }
                else {

                    tdb.updateData(editText.getText().toString(), existing_value);

                    Toast.makeText(EditActivity.this, existing_value + "is changed to " + editText.getText().toString(), Toast.LENGTH_SHORT).show();

                    Intent switch_intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(switch_intent);
                }
            }
        });






    }

    protected  void initialize(){

        editText = (EditText) findViewById(R.id.edit_id);
        update_btn = (Button) findViewById(R.id.edit_btn);
        edit_title = (TextView) findViewById(R.id.edit_title);
        tdb = new TestDBOpenHepler(this);



    }


    private Intent intent;
    private String value;
    private EditText editText;
    private TestDBOpenHepler tdb;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter	caa;
    private String existing_value;
    private Button update_btn;
    private TextView edit_title;

}

