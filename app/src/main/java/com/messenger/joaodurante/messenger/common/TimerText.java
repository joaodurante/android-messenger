package com.messenger.joaodurante.messenger.common;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class TimerText {
    TextView text;
    public TimerText(TextView textView, long finish){
        text = textView;
        text.setVisibility(View.VISIBLE);
        startTimer(finish);
    }

    private void startTimer(final long finish){
        CountDownTimer timer = new CountDownTimer(finish, 1000) {

            public void onTick(long millisUntilFinished) {
                text.setText((int)(millisUntilFinished / 1000) + " seconds remaining");
            }

            public void onFinish() {
                text.setText("Time is up.");
            }
        }.start();
    }
}
