package com.example.login_and_signup

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.UserDataAdapter
import com.example.login_and_signup.model.UserDataModel
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_user_data.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class UserDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = arguments?.getString(StringUtils.USER_DATA)
        /*val json = msg*/
        val gson = Gson()
        Log.i("myyyaapp", "josn -->$json")
        val type: Type =
            object : TypeToken<UserDataModel>() {}.type
        val userData = gson.fromJson<UserDataModel>(json, type)
        Log.i("myyyaapp", "ssiizzeeeeee-->$userData")
        val rv_userData_list = getView()?.findViewById<RecyclerView>(R.id.rv_userData_list)


        val user_adapter = UserDataAdapter()
        user_adapter.setDataCustom(userData)

        rv_userData_list?.layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL ,false)
        rv_userData_list?.adapter = user_adapter


        val swipeController = SwipeController(object : SwipeControllerActions() {

            override fun onLeftClicked(position: Int) {
                val dataSelected = user_adapter.data.infoUser?.get(position)
//                val dataSelected = student_adapter.data
                Log.i("dataselected", "-----selected position-----" + position)
                Log.i("dataselected", "-----selected-----" + dataSelected)
                Log.i("btntest", "Clicked ChangeUserRole button ")
                val intent = Intent(context, CreateUser::class.java)
                intent.putExtra("buttonsActivity", StringUtils.CHANGE_USER_ROLE)
                intent.putExtra(StringUtils.USER_DATA,dataSelected)
                startActivity(intent)
            }

            override fun onRightClicked(position: Int) {

                val removed = user_adapter.data.infoUser?.get(position)?.userId
                user_adapter.removeAt(position)
                user_adapter.notifyItemRemoved(position)
                user_adapter.notifyItemRangeChanged(position, user_adapter.itemCount)
//                student_adapter.data.info?.get(position)?.id

                Log.i("del", "------removed----position------>" +position+"\n" + Gson().toJson(removed))
                ApiStudent()
                    .addRetroFit()
                    ?.deleteUser(removed)
                    ?.enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>,
                                                response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                //                            val msg = "{info:" + response.body()?.string() + "}"
                                //                            Log.i("api","msgg " + msg)
                                val msg = response.body()?.string()
                                //                                val intent = Intent(context, StudentDetails::class.java)
                                //                                intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                                //                                startActivity(intent)
                                Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })

            }
        })
        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(rv_userData_list)

        if (rv_userData_list != null) {
            rv_userData_list.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    swipeController.onDraw(c)
                }
            })
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}