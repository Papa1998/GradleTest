package com.papa.gradletest;

public class ZTFManager {

    private static final String MSG = "我是单例";

    private static volatile ZTFManager INSTANCE;

    private ZTFManager() {

    }

    public static ZTFManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ZTFManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ZTFManager();
                }
            }
        }
        return INSTANCE;
    }

    public static int netTest() {
        if (NetworkUtil.Companion.isNetAvailable()) {
            return NetworkUtil.Companion.speedOfNet();
        } else {
//            DebugLog.d("网络不可用");
            return 0;
        }
    }

}
