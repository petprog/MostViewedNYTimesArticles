package com.petprog.nytimes.mostviewednytimes.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

object Extension {
    /**
     * Returns Color from resource.
     * @param id Color Resource ID
     */
    fun Activity.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)


    /**
     * Can show [Toast] from every [Activity].
     */
    fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(applicationContext, message, duration).show()
    }

}