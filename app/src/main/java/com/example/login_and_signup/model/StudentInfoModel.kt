package com.example.login_and_signup.model

data class StudentInfoModel(
	val info: List<InfoItem?>? = null
)

data class InfoItem(
	val address: String? = null,
	val id: Int? = null,
	val parentname: String? = null,
	val bid: Int? = null,
	val age: Int? = null,
	val name: String? = null

)

