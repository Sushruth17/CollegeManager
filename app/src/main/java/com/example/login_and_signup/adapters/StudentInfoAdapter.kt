package com.example.login_and_signup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.R
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.StudentModel
import kotlinx.android.synthetic.main.student_info_unit.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.util.logging.Filter as Filter1

class StudentInfoAdapter  : RecyclerView.Adapter<StudentInfoAdapter.ViewHolder>(){

/*    var studentFilterList = data.info

    init {
         studentFilterList = data.info
    }*/


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameType = itemView.name
        val ageType = itemView.age
        val addressType = itemView.address
        val parentNameType = itemView.parent_name
    }


    lateinit var data:StudentModel
    fun setDataCustom(data:StudentModel){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate
            (R.layout.student_info_unit, parent, false))
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

/*    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    studentFilterList = data.info
                } else {
                    val resultList = data.info
                    for (row in data.info!!) {
                        if (row.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            if (resultList != null) {
                                resultList.add(row)
                            }
                        }
                    }
                    studentFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = studentFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    studentFilterList = results.values as MutableList<InfoItem?>?
                }
                notifyDataSetChanged()
            }

        }
    }*/


    fun removeAt(position: Int) {
        data.info?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

}
