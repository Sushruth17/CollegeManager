package com.example.login_and_signup.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize


data class UserDataModel(

	@field:SerializedName("infoUser")
	val infoUser: MutableList<InfoUserItem?>? = null
)

@Parcelize
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
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString()
	)

	companion object : Parceler<InfoUserItem> {

		override fun InfoUserItem.write(parcel: Parcel, flags: Int) {
			parcel.writeString(userEmailId)
			parcel.writeString(userPhoneNumber)
			parcel.writeString(userType)
			parcel.writeValue(userId)
			parcel.writeString(userName)
			parcel.writeString(userUsername)
		}

		override fun create(parcel: Parcel): InfoUserItem {
			return InfoUserItem(parcel)
		}
	}
}
