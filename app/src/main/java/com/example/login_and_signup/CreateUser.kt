package com.example.login_and_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_particular_year.*

class CreateUser : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val spinnerCreateUser: Spinner? = findViewById(R.id.create_user_spinner)
        val arrayCreateUser = R.array.create_user_array

        if (spinnerCreateUser != null) {
            spinnerCreateUser.onItemSelectedListener = this
        }

        applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                arrayCreateUser,
                R.layout.spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinnerCreateUser != null) {
                    spinnerCreateUser.adapter = adapter
                }

            }
        }
        val buttonCreate = findViewById<Button>(R.id.btn_create)
        buttonCreate.setOnClickListener {
            val emailIdForCreateUser = create_user_input_email_id.getText().toString()
            Log.i("emailid", "--------emailId-------------- $emailIdForCreateUser")
            if (!StringUtils.checkRegex(emailIdForCreateUser, StringUtils.EMAIL_PATTERN)) {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val spinnerCreateUser: String = create_user_spinner.selectedItem.toString()
        Log.i("spinner","---selected ROLE---"+spinnerCreateUser)


    }
}