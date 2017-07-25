package com.amcaicedo.sena.apppruebavoz;

import  android.util.Log;
/**
 * Created by YEIMY BRAVO on 20/05/2017.
 */

public class AppLog {
    private static final String APP_TAG = "AudioRecorder";

    public static int logString(String message){
        return Log.i(APP_TAG, message);
    }

}
