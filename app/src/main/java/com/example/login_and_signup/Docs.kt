package com.example.login_and_signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException


class Docs : AppCompatActivity(), View.OnClickListener {
    object Constants {
        const val STORAGE_PATH_UPLOADS = "uploads/"
        const val DATABASE_PATH_UPLOADS = "uploads"
    }

    //constant to track image chooser intent
    private val PICK_IMAGE_REQUEST = 234

    //view objects
    private lateinit var buttonChoose: Button
    private lateinit var buttonUpload: Button
    private lateinit var editTextName: EditText
    private lateinit var textViewShow: TextView
    private lateinit var imageView: ImageView

    //uri to store file
    private var filePath: Uri? = null

    //firebase objects
    private var storageReference: StorageReference? = null
    private var mDatabase: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docs)

        buttonChoose = findViewById<Button>(R.id.buttonChoose);
        buttonUpload = findViewById<Button>(R.id.buttonUpload);
        imageView = findViewById<ImageView>(R.id.imageView);
        editTextName = findViewById<EditText>(R.id.editText);
        textViewShow = findViewById<TextView>(R.id.textViewShow);

        storageReference = FirebaseStorage.getInstance().reference;
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        textViewShow.setOnClickListener(this);

    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v == buttonChoose) {
            showFileChooser()
        } else if (v == buttonUpload) {
            uploadFile()
        } else if (v == textViewShow) {
            val intent = Intent(this, ImagesActivity::class.java)
            startActivity(intent)
        }
    }



        private fun uploadFile() {
            //checking if file is available
            if (filePath != null) {
                //displaying progress dialog while image is uploading
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading")
                progressDialog.show()

                //getting the storage reference
                val sRef: StorageReference = storageReference!!.child(
                    Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(
                        filePath
                    )
                )

                //adding the file to reference
                sRef.putFile(filePath!!)
                    .addOnSuccessListener { taskSnapshot -> //dismissing the progress dialog
                        progressDialog.dismiss()

                        //displaying success toast
                        Toast.makeText(
                            this,
                            "File Uploaded ",
                            Toast.LENGTH_LONG
                        ).show()

                        //creating the upload object to store uploaded image details
                        val upload = Upload(
                            editTextName.text.toString().trim(),
                            taskSnapshot.metadata?.reference?.downloadUrl.toString()
                        )

                        //adding an upload to firebase database
                        val uploadId: String? = mDatabase?.push()?.key
                        if (uploadId != null) {
                            mDatabase?.child(uploadId)?.setValue(upload)
                        }

                    }
                    .addOnFailureListener { exception ->
                        progressDialog.dismiss()
                        Toast.makeText(
                            this,
                            exception.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnProgressListener { taskSnapshot -> //displaying the upload progress
                        val progress =
                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                    }
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        }



        private fun getFileExtension(uri: Uri?): String? {
            val cR = contentResolver
            val mime = MimeTypeMap.getSingleton()
            return mime.getExtensionFromMimeType(cR.getType(uri!!))
        }



}

