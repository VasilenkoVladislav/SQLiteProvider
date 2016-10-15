package com.example.user.sqlite.UI;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.sqlite.DB.ContentContract;
import com.example.user.sqlite.R;

public class MainActivity extends AppCompatActivity implements  OnClickListener {
    EditText edittextId;
    EditText edittextName;
    EditText edittextSurname;
    EditText edittextAge;

    Button btnSelectAll;
    Button btnPut;
    Button btnDeleteAll;
    Button btnUpddate;

    ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittextId=(EditText)findViewById(R.id.textId);
        edittextName=(EditText)findViewById(R.id.textName);
        edittextSurname=(EditText)findViewById(R.id.textSurName);
        edittextAge=(EditText)findViewById(R.id.textAge);

        lvContact = (ListView) findViewById(R.id.lvContact);

        btnSelectAll=(Button)findViewById(R.id.buttonSelectAll);
        btnSelectAll.setOnClickListener(this);

        btnPut=(Button)findViewById(R.id.buttonPut);
        btnPut.setOnClickListener(this);

        btnDeleteAll=(Button)findViewById(R.id.buttonDeleteAll);
        btnDeleteAll.setOnClickListener(this);

        btnUpddate=(Button)findViewById(R.id.buttonUpdate);
        btnUpddate.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int id=Integer.parseInt( edittextId.getText().toString());
        String name =  edittextName.getText().toString();
        String surname =  edittextSurname.getText().toString();
        int age = Integer.parseInt( edittextAge.getText().toString());

        switch (v.getId())
        {
            case R.id.buttonSelectAll:
                Cursor cursor = getContentResolver().query(ContentContract.PersonEntry.CONTENT_URI, null, null,
                        null, null);
                startManagingCursor(cursor);

                String from[] = {
                        ContentContract.PersonEntry.COLUMN_PERSON_ID,
                        ContentContract.PersonEntry.COLUMN_PERSON_NAME,
                        ContentContract.PersonEntry.COLUMN_PERSON_SURNAME,
                        ContentContract.PersonEntry.COLUMN_PERSON_AGE};
                int to[] = {R.id.textViewId, R.id.textViewName,R.id.textViewSurName,R.id.textViewAge };
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.my_list_item, cursor, from, to);
                lvContact.setAdapter(adapter);
                break;

            case R.id.buttonPut:
                ContentValues cv = new ContentValues();
                cv.put(ContentContract.PersonEntry.COLUMN_PERSON_ID, id);
                cv.put(ContentContract.PersonEntry.COLUMN_PERSON_NAME, name);
                cv.put(ContentContract.PersonEntry.COLUMN_PERSON_SURNAME, surname);
                cv.put(ContentContract.PersonEntry.COLUMN_PERSON_AGE,age);
                getContentResolver().insert(ContentContract.PersonEntry.CONTENT_URI, cv);
                break;

            case R.id.buttonDeleteAll:
                getContentResolver().delete(ContentContract.PersonEntry.CONTENT_URI, null, null);
                break;

            case R.id.buttonUpdate:
                ContentValues values = new ContentValues();
                values.put("Name", name);
                values.put("SurName", surname);
                values.put("Age", age);
                getContentResolver().update(ContentContract.PersonEntry.CONTENT_URI,values,ContentContract.PersonEntry.COLUMN_PERSON_ID + " = "+ id,null);
                break;
        }
    }
}
