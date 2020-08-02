package com.example.login_and_signup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_and_signup.R
import com.example.login_and_signup.Upload


class ImageViewAdapter(private val context: Context,
                       private var uploads: MutableList<Upload?>
) :
    RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_images, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upload = uploads?.get(position)
        if (upload != null) {
            holder.textViewName.text = upload.name
        }
        if (upload != null) {
            Glide.with(context).load(upload.url).into(holder.imageView)
//            Picasso.with(context).load(upload.url).into(holder.imageView);
        }
    }

    override fun getItemCount(): Int {

            return uploads!!.size

    }

     class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById<View>(R.id.textViewName) as TextView
        var imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView

    }

    fun setUploads(uploads: MutableList<Upload?>) {
        this.uploads= uploads
        notifyDataSetChanged()
    }

}
