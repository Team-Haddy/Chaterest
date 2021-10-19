package com.teamhaddy.app.chaterest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
}