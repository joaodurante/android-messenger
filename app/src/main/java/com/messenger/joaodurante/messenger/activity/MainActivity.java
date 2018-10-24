package com.messenger.joaodurante.messenger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.messenger.joaodurante.messenger.R;
import com.messenger.joaodurante.messenger.common.FirebaseConfig;
import com.messenger.joaodurante.messenger.common.SwitchActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser firebaseUser = FirebaseConfig.getFirebaseUser();
        if(firebaseUser == null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Messenger");
        setSupportActionBar(toolbar);
    }

    //Exibe o menu_main ao invés do padrão
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.leave_item:
                logoutUser();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        SwitchActivity.nextActivity(this, LoginActivity.class);
        finish();
    }
}
