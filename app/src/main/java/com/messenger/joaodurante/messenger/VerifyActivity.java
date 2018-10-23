package com.messenger.joaodurante.messenger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.messenger.joaodurante.messenger.common.MaskFormatter;
import com.messenger.joaodurante.messenger.common.PermissionValidator;
import com.messenger.joaodurante.messenger.common.TimerText;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    private EditText editCode;
    private Button buttonValidate;
    private TextView textPhone;
    private TextView textTimer;
    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider phoneAuthProvider;
    private String phoneNumber;
    private String mVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        editCode = findViewById(R.id.editCode);
        buttonValidate = findViewById(R.id.buttonValidate);
        textPhone = findViewById(R.id.textViewNumber);
        textTimer = findViewById(R.id.textTimer);
        firebaseAuth = FirebaseAuth.getInstance();
        phoneAuthProvider = PhoneAuthProvider.getInstance();
        Bundle extras = getIntent().getExtras();
        phoneNumber = extras.getString("phoneNumber");

        //Create editText mask
        final MaskFormatter maskFormatter = new MaskFormatter(
                "N - N - N - N - N - N",
                editCode
        );

        //Validate the permissions
        PermissionValidator permissionValidator = new PermissionValidator(this, 1);

        //Start 60 seconds timer
        TimerText timerText = new TimerText(textTimer, 90000);

        //Set TextView content
        String text = textPhone.getText().toString() + " to\n" + phoneNumber;
        textPhone.setText(text);


        //Send verification code to the phone
        sendVerificationCode();

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = maskFormatter.replaceMaskFormatter(editCode.getText().toString());
                if(code.isEmpty() || code.length() < 6){
                    editCode.setError("Enter a valid code");
                    editCode.requestFocus();
                    return;
                }

                //Verify the code entered by the user
                verifyVerificationCode(code);
            }
        });

    }

    private void sendVerificationCode(){
        this.phoneAuthProvider.verifyPhoneNumber(
                this.phoneNumber,
                90,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks
        );
    }

    private void verifyVerificationCode(String code){
        //create the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //get the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            if(code != null){
                editCode.setText(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token){
            super.onCodeSent(verificationId, token);
            mVerificationId = verificationId;
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), NameActivity.class);

                            startActivity(intent);
                        }else{
                            String message = "Something is wrong";
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            Log.e("Error: ", message);
                        }
                    }
                });
    }
}



