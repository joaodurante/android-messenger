package com.messenger.joaodurante.messenger.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.messenger.joaodurante.messenger.R;
import com.messenger.joaodurante.messenger.common.FirebaseConfig;
import com.messenger.joaodurante.messenger.common.SwitchActivity;
import com.messenger.joaodurante.messenger.fragment.ChatFragment;
import com.messenger.joaodurante.messenger.fragment.ContactFragment;
import com.messenger.joaodurante.messenger.fragment.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser firebaseUser = FirebaseConfig.getFirebaseUser();
        if(firebaseUser == null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        SectionPageAdapter sectionPageAdapter;
        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Messenger");
        this.setSupportActionBar(toolbar);

        //set-up the viewpager with the sections adapter
        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "Chats");
        adapter.addFragment(new ContactFragment(), "Contacts");
        viewPager.setAdapter(adapter);
    }
}

