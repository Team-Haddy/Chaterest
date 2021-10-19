package com.teamhaddy.app.chaterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Button updateSettings,ChangeInterest;
    private EditText Username;
    private CircleImageView UserProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        InitializeFields();
    }

    private void InitializeFields() {
        updateSettings= (Button) findViewById(R.id.update_button);
        ChangeInterest= (Button) findViewById(R.id.set_profile_status);
        Username= (EditText) findViewById(R.id.set_username);
        UserProfilePic =(CircleImageView) findViewById(R.id.set_profile);
    }


    public void transition(View view) {
        Intent interest =new Intent(this,Interest.class);
        startActivity(interest);
        Toast.makeText(getApplicationContext(), "Update Your Interest", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        Button button = (Button) findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        Intent interest =new Intent(this,MainActivity.class);
        startActivity(interest);
        Toast.makeText(getApplicationContext(), "Interest Updated Successfully", Toast.LENGTH_SHORT).show();
    }
}
