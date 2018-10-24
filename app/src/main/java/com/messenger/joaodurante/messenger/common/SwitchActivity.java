package com.messenger.joaodurante.messenger.common;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class SwitchActivity {

    public static void nextActivity(Context current, Class<?> next){
        Intent intent = new Intent(current, next);
        current.startActivity(intent);
    }

    public static void nextActivity(Context current, Class<?> next, String extraName, String extra){
        Intent intent = new Intent(current, next);
        intent.putExtra(extraName, extra);
        current.startActivity(intent);
    }
}
