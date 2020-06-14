package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val button = findViewById<Button>(R.id.add)
        button.setOnClickListener {
            val intent = Intent(this, stdDetails::class.java)
            startActivity(intent)
        }
    }
}