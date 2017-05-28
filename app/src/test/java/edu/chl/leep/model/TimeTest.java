package edu.chl.leep.model;

import android.app.Activity;
import android.content.Context;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import edu.chl.leep.ctrl.MainActivity;
import edu.chl.leep.ctrl.TimeLog;

import static org.junit.Assert.*;

/**
 * Created by Eli on 2017-05-25.
 */
public class TimeTest {

    @Test
    public void startTimer() throws Exception {
        final Time time = Time.getInstance(new MainActivity());
        assertTrue(time.getTime() == 0);

        time.startTimer();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                assertTrue(time.getTime() != 0);
            }
        },0,2000);

    }

    @Test
    public void stopTimer() throws Exception {
        final Time time = Time.getInstance(new MainActivity());
        time.startTimer();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                assertTrue(time.getTime() != 0);
                time.stopTimer();
                assertTrue(time.getTime() == 0);
            }
        },0,1000);
    }
}