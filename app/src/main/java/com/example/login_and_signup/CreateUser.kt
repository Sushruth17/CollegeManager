package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.InfoUserItem
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_particular_year.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUser : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var buttonID : Int = 0
    var task :String= ""
    var userName :String? = ""
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        change_user_toolbar.setNavigationIcon(R.drawable.ic_back)
        change_user_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })

        val spinnerCreateUser: Spinner? = findViewById(R.id.create_user_spinner)
        val arrayCreateUser = R.array.create_user_array

        if (spinnerCreateUser != null) {
            spinnerCreateUser.onItemSelectedListener = this
        }

        applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                arrayCreateUser,
                R.layout.spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinnerCreateUser != null) {
                    spinnerCreateUser.adapter = adapter
                }

            }
        }

        val buttonsActivity = intent.getStringExtra("buttonsActivity")
        val dataReceived =intent.getParcelableExtra<InfoUserItem>(StringUtils.USER_DATA)
        Log.i("Data","----Data received-----"+ dataReceived)
        if (buttonsActivity == StringUtils.CHANGE_USER_ROLE){
            btn_create.visibility = View.GONE
            btn_change.visibility = View.VISIBLE
            change_user_txt.visibility = View.VISIBLE
            create_user_txt.visibility= View.GONE
            buttonID = R.id.btn_change
            task = StringUtils.CHANGE
            userName = dataReceived.userName
            Log.i("task","----task-----"+ task)

            create_user_input_email_id.setText(dataReceived.userEmailId)
            if (dataReceived.userType == StringUtils.TEACHER) {
                spinnerCreateUser?.setSelection(2)
            }
            else if  (dataReceived.userType == StringUtils.ADMIN) {
                spinnerCreateUser?.setSelection(0)
            }

            val userStatusSwitch = findViewById<Switch>(R.id.user_status_switch)
            userStatusSwitch?.setOnCheckedChangeListener { _, isChecked ->
                val message = if (isChecked) "Active" else "Inactive"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
        else{
            buttonID = R.id.btn_create
            user_status_switch.visibility = View.GONE
            task = StringUtils.CREATE
            Log.i("task","----task-----"+ task)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val spinnerCreateUser: String = create_user_spinner.selectedItem.toString()
        Log.i("spinner", "---selected ROLE---" + spinnerCreateUser)

        Log.i("buttonID", "--------buttonID-------------- $buttonID")
        val buttonCreate = findViewById<Button>(buttonID)
        buttonCreate.setOnClickListener {
            val emailIdForCreateUser = create_user_input_email_id.text.toString()
            Log.i("emailid", "--------emailId-------------- $emailIdForCreateUser")
            if (!StringUtils.checkRegex(emailIdForCreateUser, StringUtils.EMAIL_PATTERN)) {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {

            val jsonUserObj = JsonObject()
            jsonUserObj.addProperty("userType", spinnerCreateUser)
            jsonUserObj.addProperty("emailId", emailIdForCreateUser)
            jsonUserObj.addProperty("userName", userName)
            jsonUserObj.addProperty("task", task)

            ApiStudent()
                .addRetroFit()
                ?.addUser(jsonUserObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            finish()
                            finish()
                        }
                    }
                })
        }
    }

    }
}