package com.example.calofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class CreateApp extends AppCompatActivity {
    EditText company_name, position;
    Switch interview, offer, open;
    Button submitBtn;
    ApplicationsHelper dbHelper;

    String Company, Position;
    int iv, offa, opes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_app);
        company_name = findViewById(R.id.companyName);
        position = findViewById(R.id.positionTxt);
        interview = findViewById(R.id.interviewed);
        offer = findViewById(R.id.offeredJob);
        open = findViewById(R.id.openClosed);
        submitBtn = findViewById(R.id.submitBtn);
        dbHelper = new ApplicationsHelper(this);

        submitBtn.setOnClickListener(view -> {
            Company = company_name.getText().toString();
            Position = position.getText().toString();
            if(interview.isChecked()){
                iv =1;
            }else{
                iv=0;
            }
            if(offer.isChecked()){
                offa =1;
            }else{
                offa=0;
            }
            if(interview.isChecked()){
                iv =1;
            }else{
                opes=0;
            }
            boolean status = dbHelper.insertApplication(Company, Position, iv, offa, opes);
            if(status){
                Toast.makeText(CreateApp.this, "Inserted Succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateApp.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(CreateApp.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}