package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EditStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        val buttonAdd = findViewById<Button>(R.id.edit)
        buttonAdd.setOnClickListener {
//            val intent = Intent(this, StudentDetails::class.java)
//            startActivity(intent)
            finish()
        }
    }
}