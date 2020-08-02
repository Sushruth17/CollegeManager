package com.example.login_and_signup.utils

import android.text.InputFilter
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.login_and_signup.R

class StringUtils {
    companion object{

        val PASS_PERCENTAGE: String = "Pass Percentage"
        val USER_DETAILS: String = "User Details"
        val TOPPER_LIST: String = "Topper List"
        val STUDENT_DETAILS: String = "Student Details"
        var baseUrl :String = "http://7d470fd05ac8.ngrok.io"

        fun checkRegex(src: CharSequence?, regex: String): Boolean {
            Log.i("regex","---------REGEX_ inside checkRefex--------")
            if (src.toString().matches(regex.toRegex())) {
                Log.i("regex", "---------REGEX_ if matches--------")
                return true
            }
            return false
        }

        fun validateET(regex: String): Array<InputFilter>? {
            return arrayOf(
                InputFilter { src, start, end, dst, dstart, dend ->
                    if (src == "") { // for backspace
                        Log.i("regex","---------REGEX_ if backspace--------")
                        return@InputFilter src

                    }
                    if (checkRegex(src,regex)){
                        Log.i("regex","---------REGEX_ if given expression--------")
                        src
                    } else ""

                }
            )

        }




        val USER_STATUS: String = "USER_STATUS"
        val NOT_CREATED: String = "not created"
        val INACTIVE: String = "inactive"
        val ACTIVE: String = "active"
        val PAID: String? = "Paid"
        val NOT_PAID: String? = "Not Paid"
        val STUDENT_FEES_DATA: String? = "STUDENT_FEES_DATA"
        val CREATE: String = "CREATE"
        val CHANGE: String = "CHANGE"
        val USER_DATA: String? = "USER_DATA"
        val CHANGE_USER_ROLE: String? = "CHANGE_USER_ROLE"
        val CREATE_USER: String? = "CREATE_USER"
        val USER_TYPE: String = "USER_TYPE"
        val USER_NAME: String = "USER_NAME"
        val USER_USERNAME: String = "USER_USERNAME"
        val USER_PHONE_NUMBER: String = "USER_PHONE_NUMBER"
        val USER_EMAIL: String = "USER_EMAIL"


        val NOT_VALID = "NOT_VALID"

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
