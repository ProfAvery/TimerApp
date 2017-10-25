package edu.fullerton.ecs.timerapp;

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

    private long startTime;

    boolean isTimerRunning = false;

    private TextView millisTextView;
    private Button startStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        millisTextView = (TextView) findViewById(R.id.millisTextView);
        startTime = System.currentTimeMillis();
        updateCurrentTime();

        startStopButton = (Button) findViewById(R.id.startStopButton);
        startStopButton.setOnClickListener(this);
        updateButton();
    }

    private void start() {
        isTimerRunning = true;
        startTime = System.currentTimeMillis();


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

        //startStopButton.setText("Stop");
        updateButton();
    }

    private void stop() {
        isTimerRunning = false;
        timer.cancel();

        updateButton();
    }

    void updateCurrentTime() {
        long currentTime = System.currentTimeMillis() - startTime;
        double currentSeconds = currentTime / 1000.0;
        millisTextView.setText(Double.toString(currentSeconds));

    }

    void updateButton() {
        if (isTimerRunning) {
            startStopButton.setText("Stop");
        } else {
            startStopButton.setText("Start");
        }
    }

    @Override
    public void onClick(View view) {
        if (isTimerRunning) {
            stop();
        } else {
            start();
        }
    }
}
