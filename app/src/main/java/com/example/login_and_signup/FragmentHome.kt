package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.FragmentHomeAdapter
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.SharedPreference
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home1.*
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
 * Use the [Home1.newInstance] factory method to
 * create an instance of this fragment.
 */
public class FragmentHome : Fragment() {

//    @get:JvmName("getContext_")
//    lateinit var context: Context
    var bottomNavigationView: BottomNavigationView? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var userType: String = StringUtils.NOT_VALID

    fun FragmentHome() {

        // Required empty public constructor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            Log.i("lifecycle", "onCreate")


        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        Log.i("classview", "inside on create view home")

        Log.i("lifecycle", "onCreateView")
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("lifecycle", "onViewCreated")
        val buttonNamesList
                = listOf<String>(
            StringUtils.STUDENT_DETAILS,
            StringUtils.TOPPER_LIST,
            StringUtils.USER_DETAILS,
            StringUtils.PASS_PERCENTAGE
        )

        val rv_fragment_home = getView()?.findViewById<RecyclerView>(R.id.rv_fragment_home)
        val home_adapter = FragmentHomeAdapter()
        home_adapter.setDataCustom(buttonNamesList)
        rv_fragment_home?.layoutManager  = GridLayoutManager(
            activity,2, GridLayoutManager.VERTICAL, false)
        rv_fragment_home?.adapter = home_adapter

        val progressBar: ProgressBar = this.progress_bar_frag_home
        rv_fragment_home?.addOnItemTouchListener(RecyclerItemClickListenr
            (activity!!.applicationContext, rv_fragment_home, object :
            RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val itemSelected = home_adapter.data[position]

                if (itemSelected == StringUtils.STUDENT_DETAILS){
                    Toast.makeText(activity, "student details", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.VISIBLE
                    home_xml.visibility = View.GONE
                    Log.i("btntest", "Clicked student details button ")
                    ApiStudent()
                        .addRetroFit()
                        ?.greetUser()
                        ?.enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                            }

                            override fun onResponse(
                                call: Call<ResponseBody>,
                                response: Response<ResponseBody>
                            ) {
                                if (response.isSuccessful) {
                                    val msg = response.body()?.string()
                                    val intent = Intent(activity, StudentDetails::class.java)
                                    intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                                    activity?.startActivity(intent)
                                    Log.i("api", "---TTTT :: GET msg from server :: $msg")
                                }
                            }
                        })
                }

                if (itemSelected == StringUtils.TOPPER_LIST){
                    Toast.makeText(activity, "topper list", Toast.LENGTH_SHORT).show()
                    Log.i("btntest", "Clicked topper list button ")
                    val intent = Intent(activity, TopperList::class.java)
                    activity?.startActivity(intent)
                }

                if (itemSelected == StringUtils.USER_DETAILS){
                    Toast.makeText(activity, "user details", Toast.LENGTH_SHORT).show()
                    Log.i("btntest", "Clicked student details button ")
                    val intent = Intent(activity, UserDetails::class.java)
                    activity?.startActivity(intent)
                }

                if (itemSelected == StringUtils.PASS_PERCENTAGE){
                    Toast.makeText(context, "pass percentage", Toast.LENGTH_SHORT).show()
                    Log.i("btntest", "Clicked btnPassPercentage button ")
                    val intent = Intent(activity, PassPercentage::class.java)
                    activity?.startActivity(intent)
                }
            }

            override fun onItemLongClick(view: View?, position: Int) {
                TODO("Not yet implemented")
            }
        }))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
                super.onActivityCreated(savedInstanceState)
                Log.i("lifecycle", "onActivityCreated")
                userType = SharedPreference.getValueString(activity as Home, StringUtils.USER_TYPE)
                    ?: StringUtils.NOT_VALID

                if (userType == StringUtils.ADMIN) {
//                btn_user_details.visibility=View.VISIBLE
                    Log.i("type", "---usertype--- " + userType)
                }
            }


    override fun onStart() {
        super.onStart()
        val progressBar: ProgressBar = this.progress_bar_frag_home
        progressBar.visibility = View.GONE
        home_xml.visibility = View.VISIBLE
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
