package edu.fullerton.ecs.timerapp;

import android.app.Application;

/**
 * Created by AD\kavery on 10/26/17.
 */

public class TimerApplication extends Application {

    private long startTime;
    boolean isTimerRunning;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        isTimerRunning = timerRunning;
    }
}
