package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.login_and_signup.R
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.InfoUserItem
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_individual_student.*
import kotlinx.android.synthetic.main.activity_individual_user_data.*

class IndividualUserData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_user_data)
        val itemReceived =intent.getParcelableExtra<InfoUserItem>(StringUtils.USER_DATA)
        Log.i("Data","----Data received-----"+ itemReceived)
        indv_user_username.text = itemReceived.userUsername
        indv_user_name.text = itemReceived.userName
        indv_user_email_id.text =itemReceived.userEmailId
        indv_user_phone_number.text = itemReceived.userPhoneNumber
        indv_user_role.text = itemReceived.userType
        indv_user_status.text = itemReceived.userStatus

/*        val emailIdClick = findViewById<TextView>(R.id.ind_user_profile_email_id)
        emailIdClick!!.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                data = Uri.parse("mailto:")
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, itemReceived.userEmailId)
                putExtra(Intent.EXTRA_SUBJECT, "Subject of Email")
            }
            if (intent.resolveActivity(packageManager) != null) {
                intent.setPackage("com.google.android.gm")
                startActivity(intent)
            } else {
                Toast.makeText(this,"No app available to send email", Toast.LENGTH_LONG).show()
                Log.d("TAG", "No app available to send email.")
            }
        }*/
    }
}