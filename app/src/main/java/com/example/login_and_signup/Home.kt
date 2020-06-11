package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    val buttonstd = findViewById<Button>(R.id.btn_stdDetails)
    buttonstd.setOnClickListener{
        val intent = Intent(this, stdDetails::class.java)
        startActivity(intent)
    }
}
}