package com.example.calofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.calofinal.CreateApp;
import com.example.calofinal.R;
import com.example.calofinal.ViewAll;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button viewApps, newApp;
    TextView open, offer, interviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open = findViewById(R.id.openApps);
        offer = findViewById(R.id.offersCount);
        interviews = findViewById(R.id.interviewsOff);
        newApp = findViewById(R.id.newBtn);
        viewApps = findViewById(R.id.viewallBtn);


        newApp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateApp.class);
            startActivity(intent);
        });

        viewApps.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewAll.class);
            startActivity(intent);
        });

        ApplicationsHelper appHelper = new ApplicationsHelper(this);
        int intersNum = appHelper.getNumInters();
        int offersNum = appHelper.getNumOffers();
        int openNum = appHelper.getNumOpen();
        if(intersNum!=0){
            interviews.setText(Integer.toString(intersNum));

        }
        if(offersNum!=0){
            offer.setText(Integer.toString(offersNum));

        }
        if(openNum!=0){
            open.setText(Integer.toString(openNum));

        }


    }
    @Override
    protected void onStart(){
        super.onStart();
        open = findViewById(R.id.openApps);
        offer = findViewById(R.id.offersCount);
        interviews = findViewById(R.id.interviewsOff);
        newApp = findViewById(R.id.newBtn);
        viewApps = findViewById(R.id.viewallBtn);


        newApp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateApp.class);
            startActivity(intent);
        });

        viewApps.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewAll.class);
            startActivity(intent);
        });

        ApplicationsHelper appHelper = new ApplicationsHelper(this);
        int intersNum = appHelper.getNumInters();
        int offersNum = appHelper.getNumOffers();
        int openNum = appHelper.getNumOpen();
        if(intersNum!=0){
            interviews.setText(Integer.toString(intersNum));

        }
        if(offersNum!=0){
            offer.setText(Integer.toString(offersNum));

        }
        if(openNum!=0){
            open.setText(Integer.toString(openNum));

        }
    }
}