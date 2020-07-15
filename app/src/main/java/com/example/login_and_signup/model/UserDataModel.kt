package com.example.login_and_signup.model

import com.google.gson.annotations.SerializedName

data class UserDataModel(

	@field:SerializedName("infoUser")
	val infoUser: List<InfoUserItem?>? = null
)

data class InfoUserItem(

	@field:SerializedName("user_email_id")
	val userEmailId: String? = null,

	@field:SerializedName("user_phone_number")
	val userPhoneNumber: String? = null,

	@field:SerializedName("user_type")
	val userType: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("user_username")
	val userUsername: String? = null
)
