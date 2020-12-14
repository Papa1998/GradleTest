package com.papa.library2;

import android.util.Log;

import com.papa.library3.Library3Utils;
import com.papa.library4.Library4Utils;

public class Library2Utils {

    private static final String TAG = "Library2Utils";
    
    public static void showModuleName() {
        Log.d(TAG, "This is Library 2");
    }

    public void useModule() {
        Library3Utils.showModuleName();
        Library4Utils.showModuleName();
    }
}