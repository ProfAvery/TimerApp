package edu.fullerton.ecs.timerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {

    private Timer timer;

    private TimerApplication app;

    private TextView millisTextView;
    private Button startStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        millisTextView = (TextView) findViewById(R.id.millisTextView);
        app = (TimerApplication) getApplication();
        app.setStartTime(System.currentTimeMillis());
        updateCurrentTime();

        startStopButton = (Button) findViewById(R.id.startStopButton);
        startStopButton.setOnClickListener(this);
        updateButton();
    }

    private void start() {
        app.setTimerRunning(true);
        app.setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                millisTextView.post(new Runnable() {

                    @Override
                    public void run() {
                        updateCurrentTime();
                    }
                });
            }
        };

        timer = new Timer(true);
        timer.schedule(task, 0, 10);

        updateButton();

        Intent intent = new Intent(this, TimerService.class);
        startService(intent);
    }

    private void stop() {
        app.setTimerRunning(false);
        timer.cancel();

        updateButton();

        Intent intent = new Intent(this, TimerService.class);
        stopService(intent);
    }

    void updateCurrentTime() {
        long currentTime = System.currentTimeMillis() -
                app.getStartTime();
        double currentSeconds = currentTime / 1000.0;
        millisTextView.setText(Double.toString(currentSeconds));

    }

    void updateButton() {
        if (app.isTimerRunning()) {
            startStopButton.setText("Stop");
        } else {
            startStopButton.setText("Start");
        }
    }

    @Override
    public void onClick(View view) {
        if (app.isTimerRunning()) {
            stop();
        } else {
            start();
        }
    }
}
