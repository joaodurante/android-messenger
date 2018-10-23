package com.messenger.joaodurante.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.messenger.joaodurante.messenger.common.MaskFormatter;
import com.messenger.joaodurante.messenger.common.PermissionValidator;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editPhone = findViewById(R.id.editPhone);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        final MaskFormatter maskFormatter = new MaskFormatter(
                "(NN) NNNNN-NNNN",
                editPhone
        );

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+55" + editPhone.getText().toString();
                phone = maskFormatter.replaceMaskFormatter(phone);
                if(phone.isEmpty() || phone.length()<14){
                    editPhone.setError("Enter a valid phone");
                    editPhone.requestFocus();
                    return;
                }else{
                    Intent intent = new Intent(getApplicationContext(), VerifyActivity.class);
                    intent.putExtra("phoneNumber", phone);
                    startActivity(intent);
                }

            }
        });
    }
}
