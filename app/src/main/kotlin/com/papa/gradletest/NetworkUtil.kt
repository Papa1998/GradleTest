package com.papa.gradletest

/**
 * Created by Papa on 2020/12/1
 */
class NetworkUtil {
    companion object {
        // 检测网络是否可用
        fun isNetAvailable(): Boolean {
            return true
        }

        // 获取当前网络速度
        fun speedOfNet(): Int {
            return 20
        }

    }
}