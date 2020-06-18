package com.example.login_and_signup

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_student.*

class SearchStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)

        fun Context.hideKeyboard(view: View) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val buttonsearch = findViewById<Button>(R.id.search_name)
        buttonsearch.setOnClickListener {

            fun Activity.hideKeyboard() {
                hideKeyboard(currentFocus ?: View(this))
            }
            hideKeyboard()
            val editName = findViewById(R.id.editName) as EditText
//        val name: String = editName.getText().toString()
            Toast.makeText(this, editName.text, Toast.LENGTH_SHORT).show()
        }
    }
}