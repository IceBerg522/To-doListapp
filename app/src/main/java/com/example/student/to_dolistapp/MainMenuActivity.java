package com.example.student.to_dolistapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {

    private EditText edttxtPin;
    private ImageButton imgbtnCheck;
    String strPassPin = "1122";
    String strpinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        edttxtPin = (EditText) findViewById(R.id.edttxtPin);
        imgbtnCheck = (ImageButton) findViewById(R.id.imgbtnCheck);

    }

    public void eraseText (View vw) {
        edttxtPin.setText("");
    }

    public void btncheckPin (View vw) {
        strpinCode = edttxtPin.getText().toString();
        AsyncTaskMaker runner = new AsyncTaskMaker();
        runner.execute();
    }

    public void gotoCredits (View vw) {
        Intent toCredits = new Intent(MainMenuActivity.this, CreditsActivity.class);

        startActivity(toCredits);
    }

    private class AsyncTaskMaker extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params){

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (strpinCode.equals(strPassPin)){
                Intent ontoTheNext = new Intent(MainMenuActivity.this, NotesActivity.class);

                startActivity(ontoTheNext);
            }
            else {
                edttxtPin.setText("Wrong Pin!");
            }
        }
    }




}






