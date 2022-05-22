package com.example.calofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ViewAll extends ListActivity implements View.OnClickListener {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        ListView listApplications = getListView();

        try {
            SQLiteOpenHelper applicationHelper =  new ApplicationsHelper(this);
            db = applicationHelper.getReadableDatabase();
            cursor = db.query("ApplicationsTable", new String[]{"_id", "company_name","_position"}, null, null, null,null,null);

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.two_line_list_item,
                    cursor,
                    new String[]{"company_name", "_position"},
                    new int[]{android.R.id.text1, android.R.id.text2},0);
            listApplications.setAdapter(listAdapter);
            listApplications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> listApplications, View view, int i, long l) {
                    System.out.println("I: " + i);
                    Cursor cursor = (Cursor) listAdapter.getItem(i);
                    int _id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    System.out.println(_id);
                    Intent intent = new Intent(ViewAll.this, EditApplication.class);
                    intent.putExtra(EditApplication.EXTRA_ID, _id);
                    startActivity(intent);

                }
            });
        }catch(SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }


    @Override
    public void onClick(View view) {

    }
}