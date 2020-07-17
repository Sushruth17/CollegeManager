package com.example.login_and_signup.model

import com.google.gson.annotations.SerializedName

data class ProfileDataModel(

	@field:SerializedName("user_type")
	val userType: String? = null,

	@field:SerializedName("user_email_id")
	val userEmailId: String? = null,

	@field:SerializedName("user_username")
	val userUsername: String? = null,

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("user_phone_number")
	val userPhoneNumber: String? = null,

	@field:SerializedName("user_status")
	val userStatus: String? = null
)
