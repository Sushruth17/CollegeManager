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

/*class ApiSearchStudent {

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


}*/

class ApiStudent {


    interface APIService {
        @POST("/api/sign_in_data")
        fun signIn(@Body body: JsonObject): Call<ResponseBody>

        @POST("/api/sign_up_data")
        fun signUp(@Body body: JsonObject): Call<ResponseBody>

        @POST("/api/add_data")
        fun getVectors(@Body body: JsonObject): Call<ResponseBody>

        @POST("/api/edit_data")
        fun editStudent(@Body body: JsonObject): Call<ResponseBody>

        @GET("/student_data/{name}")
        fun nameSearched(@Path("name") name: String): Call<ResponseBody>

        @GET("/student_data")
        fun greetUser(): Call<ResponseBody>

        @GET("/{delstd}")
        fun deleteStudent(@Path("delstd") delstd: Int?): Call<ResponseBody>

        @GET("/marks/{studentid}")
        fun getMarks(@Path("studentid") studentid: Int?): Call<ResponseBody>

        @GET("/topper/{year}")
        fun getAcedamicTopper(@Path("year") year: Int?): Call<ResponseBody>

        @GET("/topper")
        fun getAnyYearTopper(): Call<ResponseBody>
    }



    public fun addRetroFit(): APIService? {
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