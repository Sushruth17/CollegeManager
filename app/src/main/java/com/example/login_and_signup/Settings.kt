package com.example.login_and_signup

import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

/*        val datePicker = findViewById<DatePicker>(R.id.datePicker1)
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        }*/
        val start = findViewById<TextView>(R.id.start)
        val start_date= findViewById<TextView>(R.id.start_date)
        val end  = findViewById<TextView>(R.id.end)
        val end_date= findViewById<TextView>(R.id.end_date)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        start_date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                start_date.text = "" + dayOfMonth + " " + (monthOfYear + 1)
            }, year, month, day)
            dpd.show()
        }

        end_date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                end_date.text = "" + dayOfMonth + " " + (monthOfYear + 1)
            }, year, month, day)
            dpd.show()
        }

        val darkModeSwitch = findViewById<Switch>(R.id.dark_mode_switch)
        darkModeSwitch.isChecked = true
        darkModeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Dark mode enabled" else "Dark mode disabled"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }
}