package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
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
//    @get:JvmName("getContext_")lateinit var context: Context
    var bottomNavigationView: BottomNavigationView? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    fun FragmentHome() {

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
        Log.i("classview", "inside on create view home")

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home1, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonstd = getView()?.findViewById<Button>(R.id.btn_stdDetails)
        buttonstd?.setOnClickListener {
            Log.i("btntest", "Clicked student details button ")
            Turrr()
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
                            // val msg = "{info:" + response.body()?.string() + "}"
                            //Log.i("api","msgg " + msg)
                            val msg = response.body()?.string()
                            val intent = Intent(getActivity(), StudentDetails::class.java)
                            intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                            getActivity()?.startActivity(intent)
                            Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                            // Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()

                        }
                    }
                })
        }

        val buttonsearch = getView()?.findViewById<Button>(R.id.btn_search_student)
        if (buttonsearch != null) {
            buttonsearch.setOnClickListener {
                Log.i("btntest", "Clicked search student button ")
                val intent = Intent(getActivity(), SearchStudent::class.java)
                //          intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
                getActivity()?.startActivity(intent)
            }
        }

        val btnTopper = getView()?.findViewById<Button>(R.id.btn_toppers_list)
        if (btnTopper != null) {
            btnTopper.setOnClickListener {
                Log.i("btntest", "Clicked search student button ")
                val intent = Intent(getActivity(), TopperList::class.java)
                //          intent.putExtra(StringUtils.STUDENT_INFO_DATA,getData())
                getActivity()?.startActivity(intent)
            }
        }
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