package com.github.grubber.tiangoumeitu.util

/**
 * Created by grubber on 2017/1/7.
 */
fun <T> checkNotNull(t: T?): T {
    return t ?: throw NullPointerException()
}

fun <T> checkNotNull(t: T?, value: Any): T {
    return t ?: throw NullPointerException(value.toString())
}