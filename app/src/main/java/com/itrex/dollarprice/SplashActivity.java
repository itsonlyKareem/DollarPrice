package com.itrex.dollarprice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    NetworkStatus status;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar(); // Hides the appbar at the top.
        actionBar.hide();


        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }

    public boolean isActive () {
        if (!NetworkStatus.getInstance(this).isOnline()) {
            return false;
        }
        else return true;
    }  // To Check for internet Connectivity.
}