package com.example.login_and_signup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Open : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)

        val handler = Handler()
        handler.postDelayed(Runnable {

        val settings = getSharedPreferences("Login", 0)
        val isLoggedIn = settings.getBoolean("LoggedIn", false)
        if (!isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, Home::class.java)
            intent.putExtra("calling-activity", ActivityConstants.ACTIVITY_3)
            startActivity(intent)
        }
        }, 2000)
    }
}