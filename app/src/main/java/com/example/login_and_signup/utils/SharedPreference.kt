package com.example.login_and_signup.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    companion object {


        private val PREFS_NAME = "loginSignupproject"


        fun save(context: Context, KEY_NAME: String, text: String) {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString(KEY_NAME, text)

            editor.apply()
        }
        fun getValueString(context: Context,KEY_NAME: String): String? {

            val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

            return sharedPref.getString(KEY_NAME, null)


        }

/*        fun save(KEY_NAME: String, value: Int) {
            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putInt(KEY_NAME, value)

            editor.apply()
        }

        fun save(KEY_NAME: String, status: Boolean) {

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putBoolean(KEY_NAME, status)

            editor.apply()
        }



        fun getValueInt(KEY_NAME: String): Int {

            return sharedPref.getInt(KEY_NAME, 0)
        }

        fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean {

            return sharedPref.getBoolean(KEY_NAME, defaultValue)

        }

        fun clearSharedPreference() {

            val editor: SharedPreferences.Editor = sharedPref.edit()

            //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

            editor.clear()
            editor.apply()
        }

        fun removeValue(KEY_NAME: String) {

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.remove(KEY_NAME)
            editor.apply()
        }*/
    }
}