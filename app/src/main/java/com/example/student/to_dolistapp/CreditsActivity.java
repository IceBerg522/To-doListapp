package com.example.student.to_dolistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CreditsActivity extends Activity {

    private ListView lstvwvCredits;
    private ArrayAdapter<String> adapter;
    private String[] credits = {"Making a listview",  "https://www.youtube.com/watch?v=5nZMoBjq6Qg \n","Displaying database in textview",
            "http://stackoverflow.com/questions/28593589/display-data-from-sqlite-db-into-different-textviews \n http://stackoverflow.com/questions/28133632/how-to-read-sqlite-by-cursor-and-display-in-textview \n http://stackoverflow.com/questions/12298835/how-to-retrieve-data-from-sqlite-database-in-android-and-display-it-in-textview",
             "Asynctask: ", "http://stackoverflow.com/questions/14250989/how-to-use-asynctask-correctly-in-android", "Mr. Buskell and Mr. Hardman's repositories in github \n https://github.com/UmlautBioEye \n https://github.com/mrhardman23"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        lstvwvCredits = (ListView) findViewById(R.id.lstvwvCredits);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, credits);
        lstvwvCredits.setAdapter(adapter);

    }

    public void gobacktoMain (View vw) {
        Intent toStart = new Intent(CreditsActivity.this, MainMenuActivity.class);

        startActivity(toStart);


    }
}
