package com.example.login_and_signup


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.R.id
import com.example.login_and_signup.R.layout
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    public override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        Log.i("add","--------inside activity main-------------- " )
        val settings = getSharedPreferences("Login", 0)

        var i: Int = 0
        btn_show_password.setOnClickListener {
                if (i == 0) {
                    Log.i("i", "--------i0-------------- $i")
//                    Toast.makeText(applicationContext, "Clicked once", Toast.LENGTH_SHORT).show()
                    sign_in_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    i += 1
                }
                else if (i == 1) {
                    Log.i("i", "--------i1-------------- $i")
//                    Toast.makeText(applicationContext, "Clicked again", Toast.LENGTH_SHORT).show()
                    sign_in_password.transformationMethod = PasswordTransformationMethod.getInstance()
                    i = 0
                }
        }
        val progressBar: ProgressBar = this.progress_loader
        val buttonSignin = findViewById<Button>(id.btn_signin)
        buttonSignin.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            mainActivityParent.visibility = View.GONE
            Log.i("add","--------clicked button-------------- " )
            val username = sign_in_username.getText().toString()
            Log.i("add","--------ADDNAME-------------- " + username)
            val password : String = sign_in_password.getText().toString()
            Log.i("add","--------ADDADDRES-------------- " + password)


                val jsonObj = JsonObject()
            jsonObj.addProperty("username", username)
            jsonObj.addProperty("password", password)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.signIn(jsonObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            if ((msg != StringUtils.WRONG_PASSWORD) && (msg != StringUtils.WRONG_USER))
                            {

                                    val editor = settings.edit()
                                    editor.putBoolean("LoggedIn", true)
                                    editor.apply()
                                    val intent = Intent(context, Home::class.java)
                                    intent.putExtra("ProfileData", msg)
                                    intent.putExtra("USERNAME", username)
                                    startActivity(intent)
                                    finish()
                            }
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
        val fotgotPassword = findViewById<TextView>(id.forgot_password)
        fotgotPassword.setOnClickListener{
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }

        val btnSignUp = findViewById<Button>(id.btn_signup)
        btnSignUp.setOnClickListener{

            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        val btnChangeUrl = findViewById<Button>(id.btn_change_base_url)
        btnChangeUrl.setOnClickListener{

            StringUtils.baseUrl =  change_base_url.text.toString()
            Log.i("url", "--------baseUrl-------------- "+StringUtils.baseUrl)
        }

    }

    override fun onStart() {
        super.onStart()
        val progressBar: ProgressBar = this.progress_loader
        progressBar.visibility = View.GONE
        mainActivityParent.visibility = View.VISIBLE
    }

/*    override fun onResume() {
        super.onResume()
        val progressBar: ProgressBar = this.progress_loader
        progressBar.visibility = View.GONE
        mainActivityParent.visibility = View.VISIBLE
    }*/

    override fun onBackPressed() {
        //do nothing
    }

/*    private fun progressBar(){

        val progressBar: ProgressBar = this.progress_loader
        Thread(Runnable {
            this.runOnUiThread(java.lang.Runnable {
                progressBar.visibility = View.VISIBLE
            })
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                progressBar.visibility = View.GONE
            })
        }).start()
    }*/
}

