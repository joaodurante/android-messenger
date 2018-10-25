package com.messenger.joaodurante.messenger.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.messenger.joaodurante.messenger.R;
import com.messenger.joaodurante.messenger.common.FirebaseConfig;
import com.messenger.joaodurante.messenger.common.SwitchActivity;

public class NameActivity extends AppCompatActivity {

    private EditText editName;
    private Button buttonValidate;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        editName = findViewById(R.id.editName);
        buttonValidate = findViewById(R.id.buttonValidate);
        firebaseUser = FirebaseConfig.getFirebaseUser();
        editName.setText(getProfileName(firebaseUser));

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                if(name.isEmpty()){
                    editName.setError("Enter a valid name.");
                    editName.requestFocus();
                    return;
                }else{
                    setProfileName(name);
                }
            }
        });
    }

    private String getProfileName(FirebaseUser user){
        if(user != null){
            return (user.getDisplayName());
        }else
            return "";
    }

    private void setProfileName(String name){
        UserProfileChangeRequest profileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        firebaseUser.updateProfile(profileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    SwitchActivity.nextActivity(NameActivity.this, MainActivity.class);
                    finish();
                }

            }
        });
    }
}
