package com.example.login_and_signup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.R
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.StudentInfoModel
import kotlinx.android.synthetic.main.student_info_unit.view.*

class StudentInfoAdapter  : RecyclerView.Adapter<StudentInfoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameType = itemView.name
        val ageType = itemView.age
        val addressType = itemView.address
        val parentNameType = itemView.parent_name
    }
    lateinit var data:StudentInfoModel
    fun setDataCustom(data:StudentInfoModel){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_info_unit, parent, false))
    }

    override fun getItemCount(): Int {
        Log.i("myyyaapp","ssiizzeeeeee-->" + (data.info?.size ?: 8766))
        return if(data.info!=null)
            data.info!!.size
        else
            0;

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unitData: InfoItem? = data.info?.get(position)
        if (unitData != null) {
            holder.nameType?.text = unitData.name
            holder.ageType?.text = unitData.age.toString()
            holder.addressType?.text = unitData.address
            holder.parentNameType?.text = unitData.parentname
        }
    }

    fun removeAt(position: Int) {
        data.info?.removeAt(position)
        notifyItemRemoved(position)
    }


}