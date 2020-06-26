package com.example.login_and_signup


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class TopperList : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topper_list)
        val spinner: Spinner? = findViewById(R.id.topper_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.topper_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinner != null) {
                    spinner.adapter = adapter
                }

            }
        }

    }




    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        when (pos) {
            0 -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, AcademicYear.newInstance("","")).commit()
            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, AnyYear.newInstance("","")).commit()
            else -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, ParticularYear.newInstance("","")).commit()
        }
        }



    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        showToast(message = "Nothing selected")
    }

    private fun showToast(
        context: Context? = getApplicationContext(),
        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()
    }
}