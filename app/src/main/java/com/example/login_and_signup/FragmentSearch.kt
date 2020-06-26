package com.example.login_and_signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_search_student.*
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
 * Use the [FragmentSearch.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSearch : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    fun FragmentSearch() {
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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun Context.hideKeyboard(view: View) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val buttonsearch = getView()?.findViewById<Button>(R.id.search_name)
        if (buttonsearch != null) {
            buttonsearch.setOnClickListener {

//                fun hideKeyboard(view: View) {
//                    hideKeyboard( View(getActivity()))
//                }
//                hideKeyboard(View(getActivity()))
    //            val editName = findViewById(R.id.editName) as EditText

                val name_entered: String = editName.getText().toString()
                //Toast.makeText(getActivity(), name_entered, Toast.LENGTH_SHORT).show()
                if(name_entered == "")
                    Toast.makeText(getActivity(),"Enter the name", Toast.LENGTH_SHORT).show()
                ApiStudent()
                    .addRetroFit()
                    ?.nameSearched(name_entered)
                    ?.enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("api","---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                if (msg != "{'info': ()}") {
                                    val intent = Intent(getActivity(), AfterSearch::class.java)
                                    intent.putExtra(StringUtils.STUDENT_SEARCH_DATA, msg)
                                    getActivity()?.startActivity(intent)
                                    Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                                    //                            Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()
                                }
                            else Toast.makeText(context,"Student does not exist", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
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
         * @return A new instance of fragment FragmentSearch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSearch().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}