package com.example.login_and_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        ind_user_profile_username.text = itemReceived.userUsername
        ind_user_profile_name.text = itemReceived.userName
        ind_user_profile_email_id.text =itemReceived.userEmailId
        ind_user_profile_phone_number.text = itemReceived.userPhoneNumber
        ind_user_role.text = itemReceived.userType
    }
}