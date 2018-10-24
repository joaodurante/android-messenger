package com.messenger.joaodurante.messenger.common;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {
    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference databaseReference;
    private static FirebaseUser firebaseUser;

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null)
            databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference;
    }

    public static FirebaseUser getFirebaseUser(){
        if(firebaseUser == null)
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser;
    }
}
