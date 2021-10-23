package com.teamhaddy.app.chaterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Button ChangeUsername,SignOutBtn;
    private EditText editedUsername;
    private CircleImageView UserProfilePic;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SignOutBtn= (Button) findViewById(R.id.sign_out_btn);
        ChangeUsername=(Button) findViewById(R.id.change_username_btn);
        editedUsername= (EditText) findViewById(R.id.set_username);
        UserProfilePic =(CircleImageView) findViewById(R.id.set_profile);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        ChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        //String dispName=user.getDisplayName();
        editedUsername.setText(user.getDisplayName());


        UserProfilePic.setImageURI(user.getPhotoUrl());
    }



    public void transition(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void updateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(editedUsername.getText().toString())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated");
                            Toast.makeText(getApplicationContext(), "Username Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void SignOut(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, GoogleSignInActivity.class));
    }

    //Method for back button in the action-bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

