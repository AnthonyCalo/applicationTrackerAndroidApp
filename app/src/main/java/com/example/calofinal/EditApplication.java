package com.example.calofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EditApplication extends AppCompatActivity {
    public static final String EXTRA_ID ="editId";
    private static Cursor cursor;
    private SQLiteDatabase db;
    Button updateBtn, deleteBtn;
    EditText company_name, position;
    Switch interviewSwitch, offerSwitch, openSwitch;
    String company, positionString;
    int interview, offa, open;
    String AppID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_application);
        //get items
        company_name = findViewById(R.id.companyName);
        position = findViewById(R.id.positionTxt);
        interviewSwitch = findViewById(R.id.interviewed);
        offerSwitch = findViewById(R.id.offeredJob);
        openSwitch = findViewById(R.id.openClosed);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);


        //get the ID from onclick
        int application_id = (Integer)getIntent().getExtras().get(EXTRA_ID);
        System.out.println(application_id);
        System.out.println("APLICATION ID UP");
        try {
            ApplicationsHelper applicationHelper =  new ApplicationsHelper(this);
            db = applicationHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM ApplicationsTable WHERE _id = ?", new String[]{Integer.toString(application_id)});
            cursor.moveToFirst();
            AppID = Integer.toString(cursor.getInt(0));
            company = cursor.getString(1);
            positionString = cursor.getString(2);
            System.out.println("LINE:" + cursor.getString(3));
            if(cursor.getInt(3)==1){
                interviewSwitch.setChecked(true);
            }
            if(cursor.getInt(4)==1){
                offerSwitch.setChecked(true);
            }
            if(cursor.getInt(5)==1){
                openSwitch.setChecked(true);
            }
            company_name.setText(company);
            position.setText(positionString);
//            String company  = cursor.getString(cursor.getColumnIndexOrThrow("company_name"));
//            System.out.println(company);

        }catch(SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        deleteBtn.setOnClickListener(view -> {
            ApplicationsHelper applicationsHelper = new ApplicationsHelper(this);
            applicationsHelper.deleteEntry(AppID);
            Intent intent = new Intent(EditApplication.this, MainActivity.class);
            startActivity(intent);
        });

        updateBtn.setOnClickListener(view -> {
            ApplicationsHelper applicationHelper =  new ApplicationsHelper(this);

            company = company_name.getText().toString();
            positionString = position.getText().toString();
            if(interviewSwitch.isChecked()){
                interview =1;
            }else{
                interview=0;
            }
            if(offerSwitch.isChecked()){
                offa =1;
            }else{
                offa=0;
            }
            if(openSwitch.isChecked()){
                open =1;
            }else{
                open=0;
            }

            applicationHelper.updateEntry(AppID, company, positionString, interview, offa, open);
            Intent intent = new Intent(EditApplication.this, MainActivity.class);
            startActivity(intent);

        });

    }
}