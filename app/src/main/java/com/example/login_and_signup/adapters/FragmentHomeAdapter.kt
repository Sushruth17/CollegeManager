package com.example.login_and_signup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.Home
import com.example.login_and_signup.R
import com.example.login_and_signup.utils.SharedPreference
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.unit_fragment_home.view.*


class FragmentHomeAdapter: RecyclerView.Adapter<FragmentHomeAdapter.ViewHolder>(){
    private val layout: LinearLayout? = null
    val params: LinearLayout.LayoutParams? = null

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val btnTextType = itemView.btn_text
            val btnLogo = itemView.btn_logo
        }


    lateinit var data:List<String>
    fun setDataCustom(data: List<String>){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentHomeAdapter.ViewHolder {
        return FragmentHomeAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.unit_fragment_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FragmentHomeAdapter.ViewHolder, position: Int) {
        val unitData: String? = data[position]
        if (unitData != null) {
            holder.btnTextType?.text = unitData
            if (unitData == StringUtils.STUDENT_DETAILS)
                holder.btnLogo.setImageResource(R.drawable.ic_student_details)
            if (unitData == StringUtils.TOPPER_LIST)
                holder.btnLogo.setImageResource(R.drawable.ic_topper_list)
            if (unitData == StringUtils.USER_DETAILS)
                    holder.btnLogo.setImageResource(R.drawable.ic_user_details)
            if (unitData == StringUtils.PASS_PERCENTAGE)
                holder.btnLogo.setImageResource(R.drawable.ic_pass_percent)
        }
    }

}

