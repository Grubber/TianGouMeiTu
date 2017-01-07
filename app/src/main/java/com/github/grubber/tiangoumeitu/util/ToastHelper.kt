package com.github.grubber.tiangoumeitu.util

import android.content.Context
import android.widget.Toast

/**
 * Created by grubber on 2017/1/7.
 */
class ToastHelper(private val _context: Context) {
    fun show(message: String) = Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()

    fun showLongToast(message: String) = Toast.makeText(_context, message, Toast.LENGTH_LONG).show()
}