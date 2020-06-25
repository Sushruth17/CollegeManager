package com.example.login_and_signup.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.login_and_signup.R
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.MarksItem
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.model.StudentMarksModel
import kotlinx.android.synthetic.main.student_info_unit.view.*
import kotlinx.android.synthetic.main.student_marks_unit.view.*

class StudentMarksAdapter: RecyclerView.Adapter<StudentMarksAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectType = itemView.sub
        val marksType = itemView.sub_marks
    }

    lateinit var data: StudentMarksModel
    fun setDataCustom(data: StudentMarksModel) {
        this.data = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return StudentMarksAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate
                (R.layout.student_marks_unit, parent, false)
        )
    }


    override fun getItemCount(): Int {
        Log.i("myyyaapp", "ssiizzeeeeee-->" + (data.infoMarks?.size ?: 8766))
        return if (data.infoMarks != null)
            data.infoMarks!!.size
        else
            0;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unitData: MarksItem? = data.infoMarks?.get(position)
        if (unitData != null) {
            holder.subjectType?.text = unitData.name
            holder.marksType?.text = unitData.marks.toString()
        }

    }
}