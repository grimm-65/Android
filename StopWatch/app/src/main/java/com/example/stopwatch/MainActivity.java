package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;

    private boolean running ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        runTimer();
    }
    private void runTimer(){

        final TextView time_view = findViewById(R.id.time_view);
        final Handler handler = new Handler();
    
        handler.post(new Runnable() {
            @Override
            public void run() {

                    int hours = seconds/3600;
                    int minutes = (seconds%3600)/60;

                    String time = String.format("%d:%02d:%02d", hours, minutes, seconds);

                    time_view.setText(time);

                    if (running){
                        seconds++;
                    }
                        handler.postDelayed(this, 1000);
            }
        });

    }

    public void onClickStart(View view){

        running = true;

        runTimer();

    }

    public void onClickStop(View view){

        running = false;

    }

    public void onClickReset(View view){

        running = false;
        seconds = 0;
    }
}