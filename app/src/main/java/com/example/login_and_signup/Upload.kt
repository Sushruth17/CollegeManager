package com.example.login_and_signup

import android.util.Log

class Upload {
    var name: String? = null
    var url: String? = null


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    constructor() {}
    constructor(name: String?, url: String?) {
        this.name = name
        this.url = url
        Log.i("url", "---url -------- "+this.url)
        Log.i("url", "---name -------- "+this.name)
    }

}