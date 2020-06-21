package com.example.login_and_signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.login_and_signup.adapters.ViewPagerAdapter
import com.example.login_and_signup.model.MockData
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.utils.ApiSearchStudent
import com.example.login_and_signup.utils.StringUtils
import com.fxn.ariana.ArianaBackgroundListener
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var context: Context
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        menu_bottom.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home -> viewpager.currentItem = 0
                R.id.search -> viewpager.currentItem = 1
                R.id.profile -> viewpager.currentItem = 2
            }
        }

        viewpager.setOnTouchListener({ v, event -> true })
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager).apply {
            list = ArrayList<String>().apply {
                add("Home")
                add("Search")
                add("Profile")
            }
        }

        viewpager.addOnPageChangeListener(
            ArianaBackgroundListener(
                getColors(),
                img1,
                viewpager
            )
        )


        val buttonstd = findViewById<Button>(R.id.btn_stdDetails)
        context = this
        buttonstd.setOnClickListener {
            var apiKindaStuff = Turrr()
                .addRetroFit()
                .greetUser()
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
//                            val msg = "{info:" + response.body()?.string() + "}"
//                            Log.i("api","msgg " + msg)
                            val msg = response.body()?.string()
                            val intent = Intent(context, StudentDetails::class.java)
                            intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                            startActivity(intent)
                            Log.i("api", "---TTTT :: GET msg from server :: " + msg)
//                            Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()

                        }
                    }
                })
        }


//        Log.i("Learning","------ Home activity A on create --------- ")
//        var data = getData()

//        toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

//        drawerLayout = findViewById(R.id.drawer_layout)
//        navView = findViewById(R.id.nav_view)

//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, toolbar, 0, 0
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        navView.setNavigationItemSelectedListener(this)


        val buttonmarks = findViewById<Button>(R.id.btn_search_student)
        buttonmarks.setOnClickListener {
            val intent = Intent(context, SearchStudent::class.java)
//          intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Learning", "------ Home activity A on pause --------- ")
    }


    override fun onStop() {
        super.onStop()
        Log.i("Learning", "------ Home activity A on stop --------- ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Learning", "------ Home activity A on destroy --------- ")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Learning", "------ Home activity A on start --------- ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Learning", "------ Home activity A on restart --------- ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Learning", "------ Home activity A on resume --------- ")
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getData(): String {
//        val gson = Gson()
        val json = getDataFromRepo()
//        val studentInfoData = gson.fromJson(json, StudentInfoModel::class.java)
        return json
    }

    fun getDataFromRepo(): String {
        return MockData.data
    }

    private fun getColors(): IntArray {
        return intArrayOf(
            ContextCompat.getColor(this, R.color.home),
            ContextCompat.getColor(this, R.color.search),
            ContextCompat.getColor(this, R.color.profile),
            ContextCompat.getColor(this, R.color.home)

        )


    }
}