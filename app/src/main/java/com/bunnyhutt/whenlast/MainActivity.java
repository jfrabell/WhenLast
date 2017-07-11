package com.bunnyhutt.whenlast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //vars
    final ArrayList<String> arrayEvents = new ArrayList<>();
    final Long tsLong = System.currentTimeMillis()/1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UserPrefsClass userPrefsClass = new UserPrefsClass(this);  //storage (sqlite?)
        final Button addButton = (Button) findViewById(R.id.button);     //add button
        final ListView listView = (ListView) findViewById(R.id.lvThings); //the list

        String listOfFields = userPrefsClass.getData("list");             //get the list (empty?)
        final String[] separated = listOfFields.split(",");                     //split the list (empty?)

        final int size = separated.length;                                //cycle through list and display
        for (int i = 0; i < size; i++)
        {
            String listItem = separated[i];
            arrayEvents.add(i, listItem + " - " + convertToDate(userPrefsClass.getData(listItem)));
        }
        updateListView(arrayEvents);



                                                                        //add clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//Do I want to go to an add/edit activity?
                Toast.makeText(MainActivity.this,"yo Momma",Toast.LENGTH_LONG).show();
            }
        });


                                                                        //list item clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//Edit the event name / date
//Do I want this in an activity?
//What about delete?
                Toast.makeText(MainActivity.this, separated[position], Toast.LENGTH_SHORT).show();
            }
        });
    }                                                                   //end on create



                                                                    //change what's in the listview
    protected void updateListView(ArrayList<String> arrayEvents) {
        //make listview 
        final ListView listView = (ListView) findViewById(R.id.lvThings);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayEvents);
        assert listView != null;
        listView.setAdapter(adapter);
    }                                                               //end function

                                                    //  converts a time stamp to how long ago it was
    protected String convertToDate(String timestamp){
        Long fromUserPrefs = Long.valueOf(timestamp);
        String theDifference;
        Long workingDifference;
        String str_date="11-06-2017 02:30 PM";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa");
        try {
            Date date = (Date) formatter.parse(str_date);
            long now= System.currentTimeMillis();
            workingDifference = now - fromUserPrefs;
        } catch (Exception e){
            System.out.println(e);
            workingDifference = Long.valueOf("0");
            theDifference = "exception";
        }

        String days = String.valueOf(workingDifference / 1000 / 60 / 60 / 24);
        while(workingDifference > (1000*60*60*24)){
            workingDifference = workingDifference - (1000*60*60*24);
        }
        String hours = String.valueOf(workingDifference / 1000 / 60 / 60);
        while(workingDifference > (1000*60*60)){
            workingDifference = workingDifference - (1000*60*60);
        }
        String minutes = String.valueOf(workingDifference / 1000 / 60);
        return days + " Day(s) "+ hours +" hour(s) " + minutes + " minute(s)";
    }                                                                           //end function

    }                                                                           //end file
