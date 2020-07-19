package com.example.login_and_signup.model

import com.google.gson.annotations.SerializedName

data class FeesDataModel(
	@field:SerializedName("Sid")
	val sid: Int? = null,

	@field:SerializedName("fees_status")
	val feesStatus: String? = null,

	@field:SerializedName("actual_fee")
	val actualFee: String? = null,

	@field:SerializedName("fee_concession")
	val feeConcession: String? = null,

	@field:SerializedName("amount_to_be_paid")
	val amountToBePaid: String? = null,

	@field:SerializedName("amount_paid")
	val amountPaid: String? = null,

	@field:SerializedName("amount_due")
	val amountDue: String? = null
)
