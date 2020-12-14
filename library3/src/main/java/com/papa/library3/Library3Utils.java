package com.papa.library3;

import android.util.Log;

import com.papa.library4.Library4Utils;

public class Library3Utils {

    private static final String TAG = "Library3Utils";
    
    public static void showModuleName() {
        Log.d(TAG, "This is Library 3");
    }

    private void showModule() {
        Library4Utils.showModuleName();
    }
}