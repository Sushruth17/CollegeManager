package com.example.login_and_signup.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import java.nio.file.Files.write


@Parcelize
data class StudentInfoModel(
	val info: MutableList<InfoItem?>? = null

): Parcelable

	@Parcelize
	data class InfoItem(
		val address: String? = null,
		val id: Int? = null,
		val parentname: String? = null,
		val bid: Int? = null,
		val age: Int? = null,
		val name: String? = null
	) : Parcelable {
		constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readString(),
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readValue(Int::class.java.classLoader) as? Int,
			parcel.readString()
		) {
		}

		companion object : Parceler<InfoItem> {

			override fun InfoItem.write(parcel: Parcel, flags: Int) {
				parcel.writeString(address)
				parcel.writeValue(id)
				parcel.writeString(parentname)
				parcel.writeValue(bid)
				parcel.writeValue(age)
				parcel.writeString(name)
			}

			override fun create(parcel: Parcel): InfoItem {
				return InfoItem(parcel)
			}
		}
	}
