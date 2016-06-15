package com.example.student.to_dolistapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.view.View;
import android.widget.TextView;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private EditText edttxtTitle, edttxtContent, edttxtTags;
    private Button btnAddItem;
    private Switch swtchVisible;
    private TextView txtvwvList, txtvwvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        edttxtTitle = (EditText) findViewById(R.id.edttxtTitle);
        edttxtContent = (EditText) findViewById(R.id.edtxtContent);
        edttxtTags = (EditText) findViewById(R.id.edttxtTags);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        swtchVisible = (Switch) findViewById(R.id.swtchVisible);
        txtvwvList = (TextView) findViewById(R.id.txtvwvList);
        txtvwvResult = (TextView) findViewById(R.id.txtvwvResult);

        swtchVisible.setChecked(false);
        swtchVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    txtvwvList.setVisibility(View.VISIBLE);
                    txtvwvResult.setText("");
                } else {
                    txtvwvList.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
            //When edittext is clicked, the label is erased and cleared of any text
            public void eraseTitle(View vw) {
                edttxtTitle.setText("");
            }

            //When edittext is clicked, the label is erased and cleared of any text
            public void eraseContent(View vw) {
                edttxtContent.setText("");
            }


            //When edittext is clicked, the label is erased and cleared of any text
            public void eraseTags(View vw) {
                edttxtTags.setText("");
            }

            public void addItem(View vw) {

                /**
                 * 1. Create a new EmployeeDatabaseHelper variable. You will need to use the following call:
                 *    new EmployeeDatabaseHelper(this, null, null, 0);
                 * 2. Create variables for all information stored in the Employee database
                 * 3. Create a ContentValues variable
                 * 4. Get a Writable Database reference using the variable name db (Remember your
                 *    try-catch block. The if-else statement that follows should also go in your try block).
                 */

                ATDatabaseHelper listDatabaseHelper = new ATDatabaseHelper(this, null, null, 0);
                SQLiteDatabase db;
                String title, content, tags;
                Cursor searchCursor;
                ContentValues contentValues = new ContentValues();

                try {
                    db = listDatabaseHelper.getWritableDatabase();
                    //insert code from if-else statement here
                    if (edttxtTitle.getText().length() == 0 || edttxtContent.getText().length() == 0 ||
                            edttxtTags.getText().length() == 0) {
                        txtvwvResult.setText("You must enter all values to add an element!");
                    } else {
                        /**
                         * 1. Set each variable equal to the values from the EditTexts
                         * 2. put each value into the ContentValues variable
                         * 3. Call the EmployeeDatabaseHelper's insertElement method
                         * 4. Display that the element has been added successfully
                         */
                        title = edttxtTitle.getText().toString();
                        content = edttxtContent.getText().toString();
                        tags = edttxtTags.getText().toString();
                        contentValues.put("TITLE", title);
                        contentValues.put("CONTENT", content);
                        contentValues.put("TAGS", tags);
                        listDatabaseHelper.insertElement(db, contentValues);
                        txtvwvResult.setText("Element has been added successfully");
                        edttxtTitle.setText("Title");
                        edttxtContent.setText("Content");
                        edttxtTags.setText("Tags");
                        txtvwvList.setMovementMethod(new ScrollingMovementMethod());


                        searchCursor = db.query("LIST", null, null, null, null, null, null);

                        //How the databse is displayed
                        if (searchCursor.moveToFirst()) {
                            do  {
                                txtvwvList.setText(searchCursor.getString(1) + "\n"
                                        + searchCursor.getString(2) + "\n" + searchCursor.getString(3) + "\n");
                            }while (searchCursor.moveToNext());

                        } searchCursor.close();
                    }
                    db.close();
                } catch (SQLiteException e) {
                    //display that the database was not found
                    txtvwvResult.setText("Database is not found");
                }

            }
    //A pop-up dialog that asks the player when he/she clicks the Quit button
    public void quitApp(View vw) {
        AlertDialog.Builder quittingApp = new AlertDialog.Builder(NotesActivity.this);
        quittingApp.setTitle("Quit App?");
        quittingApp.setMessage("Are you sure you want to quit the app?");
        //If the player clicks yes, it will send it back to the main menu (intent)
        quittingApp.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent goBackToStart = new Intent(NotesActivity.this, MainMenuActivity.class);
                startActivity(goBackToStart);
                txtvwvList.setText("");
            }
        });
        //If the player clicks no, the pop-up dialog will disappear and the player cna continue playing
        quittingApp.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        //How the pop-up dialog be shown on the phone screen
        quittingApp.show();

    }
        }
