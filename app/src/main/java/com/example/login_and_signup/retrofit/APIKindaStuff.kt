package com.example.login_and_signup

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class APIKindaStuff {

    interface APIService {
        @GET("/")
        fun greetUser(@Path("") user: String): retrofit2.Call<ResponseBody>
        @Headers("Content-type: application/json")
        @POST("/api/post_some_data")
        fun getVectors(@Body body: JsonObject): retrofit2.Call<ResponseBody>
        fun enqueue(callback: Callback<ResponseBody>): Any?
    }
//    companion object {
//        private val retrofit = Retrofit.Builder()
//                .baseUrl("http://127.0.0.1:5000")
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//                .build()
//
//        var service = retrofit.create(APIService::class.java)
//    }
    public fun addRetroFit(): APIService {
        val logging = HttpLoggingInterceptor ()
        logging.level = HttpLoggingInterceptor.Level.BODY;
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor(logging);
        val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        return retrofit.create(APIService::class.java)
    }

}