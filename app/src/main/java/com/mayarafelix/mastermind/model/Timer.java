package com.mayarafelix.mastermind.model;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.mayarafelix.mastermind.libraries.Constants;

/**
 * Created by mlcf on 2017-11-25.
 */

public class Timer implements Runnable
{
    private final long MILLIS_IN_SEC = 1000L;
    private final int SECS_IN_MIN = 60;

    private long initialTime;
    private long curTime;
    private long pausedTime;
    private long lastTime;
    private boolean isInited;
    private boolean isRunning;
    private boolean stop;
    private Handler handler;
    private TextView timerLabel;

    public Timer(TextView timerLabel)
    {
        //Log.i(Constants.TIMER_TAG, "OnTimerCreated");

        if (handler == null)
        {
            Log.i(Constants.TIMER_TAG, "NewHandler");
            handler = new Handler();
        }

        initialTime = 0;
        curTime = 0;
        pausedTime = 0;
        lastTime = 0;
        isRunning = false;
        isInited = false;
        this.timerLabel = timerLabel;
    }

    public void destroyTimer()
    {
        this.stop = true;
        this.handler = null;
    }

    public void StartTimer()
    {
        //Log.i(Constants.TIMER_TAG, "OnStartTimer");
        isRunning = true;
        initialTime = System.currentTimeMillis();
        curTime = 0;
        pausedTime = 0;
        stop = false;

        if (!isInited)
        {
            isInited = true;
            handler.postDelayed(this, MILLIS_IN_SEC);
        }
    }

    public void PauseTimer()
    {
        //Log.i(Constants.TIMER_TAG, "OnPauseTimer");
        isRunning = false;
    }

    public void ResumeTimer()
    {
        //Log.i(Constants.TIMER_TAG, "OnResumeTimer");
        isRunning = true;
    }

    protected long DeltaTime()
    {
        return System.currentTimeMillis() - lastTime;
    }

    @Override
    public void run()
    {
        //while (!stop)
        //{
            if (isRunning)
            {
                curTime = ((System.currentTimeMillis() - initialTime) - curTime - pausedTime) / MILLIS_IN_SEC;
                //Log.i(Constants.TIMER_TAG, "Timer Running: " + curTime);
            }
            else
            {
                pausedTime += DeltaTime();
                //Log.i(Constants.TIMER_TAG, "Paused Time: " + pausedTime/MILLIS_IN_SEC);
                curTime = curTime;
            }

            lastTime = System.currentTimeMillis();
            String time = String.format("%02d:%02d", curTime / SECS_IN_MIN, curTime % SECS_IN_MIN);
            timerLabel.setText(time);
            handler.postDelayed(this, MILLIS_IN_SEC);
        //}
    }

    public void destroy() {
        handler.removeCallbacksAndMessages(null);
    }
}