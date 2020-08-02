package com.example.login_and_signup

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.ImageViewAdapter
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



class ShowImage : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null

    //adapter object
//    private lateinit var adapter: RecyclerView.Adapter<*>

    //database reference
    private var mDatabase: DatabaseReference? = null

    //progress dialog
    private var progressDialog: ProgressDialog? = null

    //list to hold all the uploaded images
    private lateinit var uploads: MutableList<Upload?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        uploads = ArrayList()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL ,false)
        val adapter = ImageViewAdapter(this, uploads)
        //ading adapter to recyclerview
        recyclerView?.adapter = adapter
        adapter.setUploads(uploads)

        progressDialog = ProgressDialog(this)

        //displaying progress dialog while fetching images
        progressDialog!!.setMessage("Please wait...")
        progressDialog!!.show()
        mDatabase = FirebaseDatabase.getInstance().getReference(Docs.Constants.DATABASE_PATH_UPLOADS)
        //creating adapter


        //adding an event listener to fetch values
        mDatabase!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //dismissing the progress dialog
                progressDialog!!.dismiss()

                //iterating through all the values in database
                for (postSnapshot in snapshot.children) {
                    val upload = postSnapshot.getValue(Upload::class.java)
                    uploads.add(upload)
                }
                adapter.setUploads(uploads)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog!!.dismiss()
            }
        })
    }
}