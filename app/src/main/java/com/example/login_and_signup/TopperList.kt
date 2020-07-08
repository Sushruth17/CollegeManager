package com.example.login_and_signup


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_topper_list.*
import java.util.*


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
        val navController = Navigation.findNavController(this,R.id.nav_topper_host_fragment)
        when (pos) {
/*            0 -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, TopperAcademicYear.newInstance("", "")).commit()
            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, TopperAnyYear.newInstance("", "")).commit()
            else -> supportFragmentManager.beginTransaction()
                .replace(R.id.topper_spinner, TopperParticularYear.newInstance("", "")).commit()*/
                0 -> {navController.navigate(R.id.topperAcademicYear) }
                1 -> {navController.navigate(R.id.topperAnyYear) }
                2 -> { navController.navigate(R.id.topperParticularYear)
                    }
        }
    }
/*        if (pos == 0)
            showToast(message = "Spinner 1 Position:${pos}")
        else if (pos == 1)
            showToast(message = "Spinner 1 Position:${pos}")
        else if (pos == 2) {
            showToast(message = "Spinner 1 Position:${pos}")
            year_spinner.visibility = View.VISIBLE
        }
    }*/

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