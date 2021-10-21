package com.teamhaddy.app.chaterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Interest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
    }

    public void toggle(View view) {
        Intent intent=new Intent(this,SettingsActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Interest Updated Successfully", Toast.LENGTH_SHORT).show();
    }
}