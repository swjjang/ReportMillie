package com.swjjang7.reportmillie.util

import android.content.res.Resources
import android.util.TypedValue

private const val SCREEN_MEDIUM = 600f

val Int.dp: Int
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics)
            .toInt()
    }

val screenWidth: Int
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return metrics.widthPixels
    }

val screenWidthDp: Float
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return metrics.widthPixels / metrics.density
    }


val overCompact: Boolean
    get() {
        return screenWidthDp >= SCREEN_MEDIUM
    }