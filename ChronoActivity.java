package com.dwm.juicymuscle.controller;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChronoActivity extends AppCompatActivity {
    private TextView textViewInfo;
    private Chronometer chronometer;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chrono);

        this.textViewInfo = (TextView) findViewById(R.id.textView_info);
        this.chronometer = (Chronometer)findViewById(R.id.chrono);

        this.buttonStart = (Button)findViewById(R.id.button_start);
        this.buttonStop = (Button)findViewById(R.id.button_stop);
        this.buttonReset = (Button)findViewById(R.id.button_reset);
        this.buttonStop.setEnabled(false);
        this.buttonReset.setEnabled(false);

        this.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStart();
            }
        });

        this.buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStop();
            }
        });


        this.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doResetBaseTime();
            }
        });
    }

    
    private void showInfo(long totalMilliseconds)  {
        // Seconds
        long totalSecs = totalMilliseconds / 1000;
        // Show Info
        long hours = totalSecs / 3600;
        long minutes = (totalSecs % 3600) / 60;
        long seconds = totalSecs % 60;

        this.textViewInfo.setText("Base Time: " + totalSecs +" ~ " + hours + " hours " + minutes+" minutes " + seconds + " seconds");
    }

    private void doStart()  {

        long elapsedRealtime = SystemClock.elapsedRealtime();

        this.chronometer.setBase(elapsedRealtime);
        this.chronometer.start();
        this.showInfo(elapsedRealtime);
        //
        this.buttonStart.setEnabled(false);
        this.buttonStop.setEnabled(true);
        this.buttonReset.setEnabled(true);
    }

    private void doStop()  {
        this.chronometer.stop();
        //
        this.buttonStart.setEnabled(true);
        this.buttonStop.setEnabled(false);
        this.buttonReset.setEnabled(false);
    }

    private void doResetBaseTime()  {

        long elapsedRealtime = SystemClock.elapsedRealtime();

        this.chronometer.setBase(elapsedRealtime);
        this.showInfo(elapsedRealtime);
    }

}


