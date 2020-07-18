package com.example.login_and_signup

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.SharedPreference
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProfile() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var username : String? = null
    fun FragmentProfile() {
        // Required empty public constructor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
/*        val usernameProfile = arguments?.getString("USERNAME")
        Log.i("username fragment", "profile username$usernameProfile")
        profile_username.text = usernameProfile*/
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userUsername = SharedPreference.getValueString(activity as Home, StringUtils.USER_USERNAME) ?: StringUtils.NOT_VALID
        Log.i("type", "--------userName-------------- " + userUsername)
        profile_username.text = userUsername

        val userName = SharedPreference.getValueString(activity as Home, StringUtils.USER_NAME) ?: StringUtils.NOT_VALID
        Log.i("type", "--------userName-------------- " + userName)
        profile_name.text = userName

        val userEmailId = SharedPreference.getValueString(activity as Home,StringUtils.USER_EMAIL) ?:StringUtils.NOT_VALID
        Log.i("type", "---userEmailId--- " + userEmailId)
        profile_email_id.text = userEmailId

        val userPhoneNumber = SharedPreference.getValueString(activity as Home,StringUtils.USER_PHONE_NUMBER) ?:StringUtils.NOT_VALID
        Log.i("type", "---userPhoneNumber--- " + userPhoneNumber)
        profile_phone_number.text = "+91 $userPhoneNumber"

        val userStatus = SharedPreference.getValueString(activity as Home, StringUtils.USER_STATUS) ?:StringUtils.NOT_VALID
        profile_status.text = userStatus

        val userRole = SharedPreference.getValueString(activity as Home, StringUtils.USER_TYPE) ?:StringUtils.NOT_VALID
        profile_role.text = userRole






        val btnLogout = getView()?.findViewById<TextView>(R.id.profile_logout)
        if (btnLogout != null) {
            btnLogout.setOnClickListener {
                Log.i("btntest", "Logout clicked")
                val intent = Intent(getActivity(), MainActivity::class.java)
                //          intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
                getActivity()?.startActivity(intent)

            }
        }
        val btnEdit = getView()?.findViewById<TextView>(R.id.profile_edit)
        if (btnEdit != null) {
            btnEdit.setOnClickListener {
                Log.i("btntest", "Logout clicked")
                val intent = Intent(activity, EditProfile::class.java)
                //          intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
                activity?.startActivity(intent)

            }
        }

        val btnSettings = getView()?.findViewById<ImageView>(R.id.profile_settings)
        btnSettings?.setOnClickListener{
            Log.i("btntest", "settings clicked")
            val intent = Intent(context, Settings::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment FragmentProfile.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                FragmentProfile().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }