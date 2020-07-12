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

        val NOT_VALID = "NOT VALID"
        val STUDENT_INFO_DATA = "STUDENT_INFO_DATA"

        val STUDENT_SEARCH_DATA = "STUDENT_SEARCH_DATA"

        val STUDENT_EDIT_DATA = "STUDENT_EDIT_DATA"

        val STUDENT_MARKS_DATA = "STUDENT_MARKS_DATA"

        val WRONG_PASSWORD = "Password wrong"

        val WRONG_USER = "User wrong"

        val TEACHER = "teacher"
        val HOD = "HOD"
        val ADMIN = "admin"

        val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[\\(com\\|org\\|net\\){3}]+"

    }

}
