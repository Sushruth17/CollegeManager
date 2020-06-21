package com.example.login_and_signup.utils

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class ApiSearchStudent {

    interface APIServicesearch {
        @GET("/student_data/{name}")
        fun nameSearched(@Path("name") name: String): Call<ResponseBody>
    }


    public fun addRetroFit(): APIServicesearch? {
        val logging = HttpLoggingInterceptor ()
        logging.level = HttpLoggingInterceptor.Level.BODY;
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor(logging);
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(APIServicesearch::class.java)
    }


}

class ApiAddStudent {

    interface APIServiceAdd {
        @POST("/api/post_some_data")
        fun getVectors(@Body body: JsonObject): Call<ResponseBody>
    }



    public fun addRetroFit(): APIServiceAdd? {
        val logging = HttpLoggingInterceptor ()
        logging.level = HttpLoggingInterceptor.Level.BODY;
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor(logging);
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(APIServiceAdd::class.java)
    }


}