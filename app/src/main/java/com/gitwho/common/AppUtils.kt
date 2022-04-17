package com.gitwho.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.gitwho.common.Constants.CONSTANT_K
import com.gitwho.common.Constants.CONSTANT_M
import com.gitwho.common.Constants.MILLION
import com.gitwho.common.Constants.THOUSAND


object AppUtils {
    /**
     *This method is used to hide keyboard with view reference.
     *@param view view reference
     */
    fun hideKeyboard(view: View) {
        val imm =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getAlphaCount(count: Int): String {
        return when {
            count >= MILLION -> {
                (count / MILLION).toString() + CONSTANT_M
            }
            count >= THOUSAND -> {
                (count / THOUSAND).toString() + CONSTANT_K
            }
            else -> {
                count.toString()
            }
        }
    }
}