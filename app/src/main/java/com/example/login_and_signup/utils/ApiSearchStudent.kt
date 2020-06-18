package com.example.login_and_signup.utils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class ApiSearchStudent {

    interface APIService {
        @GET("/student_data/{name}")
        fun nameSearched(@Path("user") user: String): Call<ResponseBody>
    }
}