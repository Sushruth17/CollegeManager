package com.example.login_and_signup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.NewUpload
import com.example.login_and_signup.R
import com.squareup.picasso.Picasso

class ImageAdapter(
    private val mContext: Context,
    private val mUploads: MutableList<NewUpload?>
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val v: View =
            LayoutInflater.from(mContext).inflate(R.layout.layout_images, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int
    ) {
        val uploadCurrent = mUploads[position]
        if (uploadCurrent != null) {
            holder.textViewName.text = uploadCurrent.name
        }
        if (uploadCurrent != null) {
            Picasso.with(mContext)
                .load(uploadCurrent.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return mUploads.size
    }

    inner class ImageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)

    }

}