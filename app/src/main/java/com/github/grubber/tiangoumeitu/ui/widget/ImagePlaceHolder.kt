package com.github.grubber.tiangoumeitu.ui.widget

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.github.grubber.tiangoumeitu.R

/**
 * Created by grubber on 2017/1/7.
 */
fun getPlaceholder(context: Context): Drawable {
    return ColorDrawable(ContextCompat.getColor(context, R.color.image_placeholder))
}