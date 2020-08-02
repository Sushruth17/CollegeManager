package com.example.login_and_signup


import android.os.Bundle

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.ImageAdapter
import com.google.firebase.database.*
import java.util.*

class ImagesActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: ImageAdapter? = null
    private lateinit var mProgressCircle: ProgressBar
    private var mDatabaseRef: DatabaseReference? = null
    lateinit var mUploads: MutableList<NewUpload?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mProgressCircle = findViewById(R.id.progress_circle)
        mUploads = ArrayList()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads")
        mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val upload = postSnapshot.getValue(NewUpload::class.java)
                    mUploads.add(upload)
                }
                mAdapter = ImageAdapter(this@ImagesActivity, mUploads)
                mRecyclerView.adapter = mAdapter
                mProgressCircle.visibility = View.INVISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ImagesActivity, databaseError.message, Toast.LENGTH_SHORT)
                    .show()
                mProgressCircle.visibility = View.INVISIBLE
            }
        })
    }
}