package com.myproject;

import android.app.AlertDialog;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ccwu on 1/1/19.
 */
public class Worker implements Runnable {

    private ArrayList<float[]> recordingGestureTrace_current;
    private boolean DEBUG_current;
    private String detected_gid_current;
    private Handler alertHandler_current;
    private GestureRecognizer myGestureRecognizer_current;

    public Worker(ArrayList<float[]> recordingGestureTrace_copy, boolean DEBUG, GestureRecognizer myGestureRecognizer) {
        // store parameter for later user
        recordingGestureTrace_current= recordingGestureTrace_copy;
        DEBUG_current = DEBUG;
        myGestureRecognizer_current = myGestureRecognizer;
    }

    public void run() {
        Gesture candidate = new Gesture(null, recordingGestureTrace_current);
        if (DEBUG_current)
            Log.d("stopRecGesture-Thread", "Attempting Gesture Recognition Trace-Length: " + recordingGestureTrace_current.size());
        if(myGestureRecognizer_current == null) Log.d("stopRecGesture-Thread","myGestureRecognizer_current == null");
        String gid = myGestureRecognizer_current.recognize_gesture(candidate);
        if (DEBUG_current)
            Log.d("stopRecGesture-Thread", "===================================== \n" +
                    "Recognized Gesture: " + gid +
                    "\n===================================");
        // set gid as currently detected gid
        Acceleration.detected_gid = gid;

    }
}
