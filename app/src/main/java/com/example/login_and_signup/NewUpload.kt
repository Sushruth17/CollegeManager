package com.example.login_and_signup


class NewUpload {
    var name: String? = null
    var imageUrl: String? = null

    constructor() {
        //empty constructor needed
    }

    constructor(name: String, imageUrl: String?) {
        var name = name
        if (name.trim { it <= ' ' } == "") {
            name = "No Name"
        }
        this.name = name
        this.imageUrl = imageUrl
    }

/*    fun getName(): String? {
        return mName
    }

    fun setName(name: String) {
        mName = name
    }

    fun getImageUrl(): String? {
        return mImageUrl
    }

    fun setImageUrl(imageUrl: String) {
        mImageUrl = imageUrl
    }*/

}