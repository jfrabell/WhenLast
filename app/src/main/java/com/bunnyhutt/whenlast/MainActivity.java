package com.bunnyhutt.whenlast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //vars
    final ArrayList<String> arrayEvents = new ArrayList<>();
    final Long tsLong = System.currentTimeMillis()/1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserPrefsClass userPrefsClass = new UserPrefsClass(this);
        final Button addButton = (Button) findViewById(R.id.button);

        userPrefsClass.saveData("name", "Jeff");
        userPrefsClass.saveData("list", "sex|oil|hair");


        String ts = tsLong.toString();


        String str_date="11-06-2017 02:30 PM";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
        try {
            Date date = (Date) formatter.parse(str_date);
            System.out.println("Today is " +date.getTime());

            long output=date.getTime()/1000L;
            String str=Long.toString(output);
            long timestamp = Long.parseLong(str) * 1000;
            System.out.println("And unix is " + String.valueOf(timestamp));
            userPrefsClass.saveData("hair",String.valueOf(timestamp));

        } catch (Exception E){
            System.out.println(E.toString());
        }



        arrayEvents.add(0, "fun - " + convertToDate(userPrefsClass.getData("sex")));
        arrayEvents.add(1, "oil - " + convertToDate(userPrefsClass.getData("oil")));
        arrayEvents.add(2, "hair - " + convertToDate(userPrefsClass.getData("hair")));
        updateListView(arrayEvents);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"yo Momma",Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void updateListView(ArrayList<String> arrayEvents) {
        //make listview 
        final ListView listView = (ListView) findViewById(R.id.lvThings);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayEvents);
        assert listView != null;
        listView.setAdapter(adapter);
    }

    protected String convertToDate(String timestamp){
        Long fromUserPrefs = Long.valueOf(timestamp);
        String theDifference;
        String str_date="11-06-2017 02:30 PM";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
        try {
            Date date = (Date) formatter.parse(str_date);
            Long now = date.getTime();
            Long workingDifference = now - fromUserPrefs;
            workingDifference = workingDifference / 1000 /60 /60 /24;
            theDifference = String.valueOf(workingDifference);
        } catch (Exception e){
            System.out.println(e);
            theDifference = "exception";
        }
        return theDifference;
    }

    }
