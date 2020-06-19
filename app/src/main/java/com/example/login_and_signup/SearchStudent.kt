package com.example.login_and_signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.utils.ApiSearchStudent
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_search_student.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchStudent : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)
        context = this
        fun Context.hideKeyboard(view: View) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val buttonsearch = findViewById<Button>(R.id.search_name)
        buttonsearch.setOnClickListener {

            fun Activity.hideKeyboard() {
                hideKeyboard(currentFocus ?: View(this))
            }
            hideKeyboard()
//            val editName = findViewById(R.id.editName) as EditText
            val name_entered: String = editName.getText().toString()
            Log.i("name","--------EDIT NAME-------------- " + name_entered)
            Toast.makeText(this, name_entered, Toast.LENGTH_SHORT).show()
            ApiSearchStudent()
                .addRetroFit()
                ?.nameSearched(name_entered)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("api","---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            val intent = Intent(context, AfterSearch::class.java)
                            intent.putExtra(StringUtils.STUDENT_SEARCH_DATA, msg)
                            startActivity(intent)
                            Log.i("api","---TTTT :: GET msg from server :: " + msg)
        //                            Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()

                        }
                    }
                })
        }
    }
}
