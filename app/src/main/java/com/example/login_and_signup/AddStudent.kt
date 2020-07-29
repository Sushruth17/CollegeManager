package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_edit_student.*
import kotlinx.android.synthetic.main.fragment_particular_year.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddStudent : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        add_std_toolbar.setNavigationIcon(R.drawable.ic_back)
        add_std_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })

        add_name.filters = StringUtils.validateET("[A-Za-z ]+")
        add_parent_name.filters = StringUtils.validateET("([A-Za-z ])+")

        val spinnerBranch: Spinner? = findViewById(R.id.add_spinner_branch)
        if (spinnerBranch != null) {
            spinnerBranch.onItemSelectedListener = this
        }
        val arrayBranch = R.array.branch_add_std_array
        applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                arrayBranch,
                R.layout.spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinnerBranch != null) {
                    spinnerBranch.adapter = adapter
                }
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        val selectedBranch: String = add_spinner_branch.selectedItem.toString()
        Log.i("add", "--------spinnerBranch-------------- " + selectedBranch)

        val button = findViewById<Button>(R.id.add)
        button.setOnClickListener {

            val name_added: String = add_name.text.toString()
            Log.i("add", "--------ADDNAME-------------- " + name_added)
            val address_added: String = add_address.text.toString()
            Log.i("add", "--------ADDADDRES-------------- " + address_added)
            val age_added: String = add_age.text.toString()
            Log.i("add", "--------ADDAGE-------------- " + age_added)
            val parentname_added: String = add_parent_name.text.toString()
            Log.i("add", "--------ADDPARENTNAME-------------- " + parentname_added)
            Log.i("add", "--------ADDBRANCCH-------------- " + selectedBranch)

            if (name_added.isEmpty() or address_added.isEmpty() or
                age_added.isEmpty() or parentname_added.isEmpty()
            ) {
                Toast.makeText(
                    applicationContext, "Please fill all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else{

            val jsonObj = JsonObject()
            jsonObj.addProperty("name", name_added)
            jsonObj.addProperty("address", address_added)
            jsonObj.addProperty("age", age_added)
            jsonObj.addProperty("parentname", parentname_added)
            jsonObj.addProperty("branch", selectedBranch)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.getVectors(jsonObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("api", "---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            Log.i("api", "---TTTT :: POST msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })

        }
    }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}