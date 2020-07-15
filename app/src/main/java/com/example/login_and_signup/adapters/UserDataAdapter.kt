package com.example.login_and_signup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.R
import com.example.login_and_signup.model.InfoUserItem
import com.example.login_and_signup.model.UserDataModel
import kotlinx.android.synthetic.main.student_info_unit.view.*
import kotlinx.android.synthetic.main.user_details_unit.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.util.logging.Filter as Filter1

class UserDataAdapter  : RecyclerView.Adapter<UserDataAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameType = itemView.user_details_username
        val nameType = itemView.user_details_name
        val userTypeType = itemView.user_details_user_type
        val emialType = itemView.user_details_email
        val phoneNumberType = itemView.user_details_phone_number
    }


    lateinit var data:UserDataModel
    fun setDataCustom(data:UserDataModel){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.user_details_unit, parent, false))
    }

    override fun getItemCount(): Int {
        Log.i("myyyaapp","ssiizzeeeeee-->" + (data.infoUser?.size ?: 8766))
        return if(data.infoUser!=null)
            data.infoUser!!.size
        else
            0;

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unitData: InfoUserItem? = data.infoUser?.get(position)
        if (unitData != null) {
            holder.usernameType?.text = unitData.userUsername
            holder.nameType?.text = unitData.userName
            holder.userTypeType?.text = unitData.userType
            holder.emialType?.text = unitData.userEmailId
            holder.phoneNumberType?.text = unitData.userPhoneNumber
        }


    }


    fun removeAt(position: Int) {
        data.infoUser?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

}


