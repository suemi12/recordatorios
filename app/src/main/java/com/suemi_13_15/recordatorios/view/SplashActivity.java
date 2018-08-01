package com.suemi_13_15.recordatorios.view;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.suemi_13_15.recordatorios.R;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //SE DECLARA Y SE ASIGNA UN HANDLER
        Handler handler = new Handler();
        //(Runnable r,long delayMillis)
        handler.postDelayed(this, SPLASH_DELAY);
    }

    @Override //METODO PARA LA ACTION QUE SE QUIERE HACER DESPUES DEL POST
    public void run() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
