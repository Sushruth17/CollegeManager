package com.example.login_and_signup.utils

import android.util.Log

class StringUtils {
    companion object{

        fun checkRegex(src: CharSequence?, regex: String): Boolean {
            Log.i("regex","---------REGEX_ inside checkRefex--------")
            if (src.toString().matches(regex.toRegex())) {
                Log.i("regex", "---------REGEX_ if matches--------")
                return true
            }
            return false
        }

        val STUDENT_INFO_DATA = "STUDENT_INFO_DATA"

        val STUDENT_SEARCH_DATA = "STUDENT_SEARCH_DATA"

        val STUDENT_EDIT_DATA = "STUDENT_EDIT_DATA"

    }

}
