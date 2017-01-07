package com.github.grubber.tiangoumeitu.util

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by grubber on 2017/1/7.
 */
inline fun <reified T : Fragment> AppCompatActivity.newInstance(): T {
    return T::class.java.newInstance()
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.start() {
    startActivity(Intent(this, T::class.java))
}