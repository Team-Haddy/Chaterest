package com.teamhaddy.app.chaterest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SettingsActivity extends AppCompatActivity {

    private Button ChangeUsername,SignOutBtn;
    private EditText editedUsername;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SignOutBtn= (Button) findViewById(R.id.sign_out_btn);
        ChangeUsername=(Button) findViewById(R.id.change_username_btn);
        editedUsername= (EditText) findViewById(R.id.set_username);


        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Settings");

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
        editedUsername.setText(user.getDisplayName());

    }

    //Change Username
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

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);

        builder.setMessage("If you sign out, you won't be able to access Chaterest unless you sign in again")
                .setTitle("Sign out?");

        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                FirebaseAuth.getInstance().signOut();
                finish();
                gotoLogin();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               dialog.cancel();
            }
        });

        AlertDialog SignOutAlert = builder.create();
        SignOutAlert.show();
    }

    public void gotoLogin(){
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

