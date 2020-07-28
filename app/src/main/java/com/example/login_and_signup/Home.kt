package com.example.login_and_signup

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.login_and_signup.model.ProfileDataModel
import com.example.login_and_signup.utils.SharedPreference
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Home : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null

    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var context: Context
    lateinit var username : String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

  /*  val settings = getSharedPreferences("your_preference_name", 0)
    val isLoggedIn = settings.getBoolean("LoggedIn", false)
    if (!isLoggedIn) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    } else
    {*/
        when (intent.getIntExtra("calling-activity", 0)) {
            ActivityConstants.ACTIVITY_1 -> {

    username = intent.getStringExtra("USERNAME") ?: StringUtils.NOT_VALID
    val json = intent.getStringExtra("ProfileData") ?: StringUtils.NOT_VALID
    val gson = Gson()
    Log.i("profile_data", "json -->$json")
    val type: Type =
        object : TypeToken<ProfileDataModel>() {}.type
    val profileData = gson.fromJson<ProfileDataModel>(json, type)
    Log.i("profile_data", "ProfileData-->$profileData")
    val userType = profileData.userType ?: StringUtils.NOT_VALID
    Log.i("profile_data", "userType-->$userType")
    val userName = profileData.userName ?: StringUtils.NOT_VALID
    Log.i("profile_data", "userName-->$userName")
    val userUsername = profileData.userUsername ?: StringUtils.NOT_VALID
    Log.i("profile_data", "userUsername-->$userUsername")
    val userPhoneNumber = profileData.userPhoneNumber ?: StringUtils.NOT_VALID
    Log.i("profile_data", "userPhoneNumber-->$userPhoneNumber")
    val userEmailId = profileData.userEmailId ?: StringUtils.NOT_VALID
    Log.i("profile_data", "userEmailId-->$userEmailId")
    val userStatus = profileData.userStatus ?: StringUtils.NOT_VALID

    SharedPreference.save(this, StringUtils.USER_TYPE, userType)
    SharedPreference.save(this, StringUtils.USER_NAME, userName)
    SharedPreference.save(this, StringUtils.USER_USERNAME, userUsername)
    SharedPreference.save(this, StringUtils.USER_PHONE_NUMBER, userPhoneNumber)
    SharedPreference.save(this, StringUtils.USER_EMAIL, userEmailId)
    SharedPreference.save(this, StringUtils.USER_STATUS, userStatus)

    setUpNavigation()
            }
            ActivityConstants.ACTIVITY_3 -> {
                setUpNavigation()
            }
        }

}

    private fun setUpNavigation() {
        bottomNavigationView = findViewById(R.id.bttm_nav)
        val navHostFragment: NavHostFragment? = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHostFragment != null) {
            if (bottomNavigationView != null) {
                NavigationUI.setupWithNavController(
                    bottomNavigationView!!,
                    navHostFragment.getNavController()
                )
            }
        }
    }

}

