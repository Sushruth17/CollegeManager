package com.example.login_and_signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso


class DocsNew : AppCompatActivity() {

    private var mImageUri: Uri? = null
//    private val mButtonChooseImage: Button = findViewById<Button>(R.id.button_choose_image)
//    private val mButtonUpload: Button = findViewById<Button>(R.id.button_upload)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docs_new)
        val mButtonChooseImage: Button = findViewById(R.id.button_choose_image)
        val mButtonUpload: Button = findViewById(R.id.button_upload)
        val mTextViewShowUploads: TextView = findViewById(R.id.text_view_show_uploads)
        val mEditTextFileName: EditText = findViewById(R.id.edit_text_file_name)

//        val mProgressBar: ProgressBar = findViewById<ProgressBar>(R.id.progress_bar)
//        val mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
//        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads")
        val mUploadTask: StorageTask<*>? = null
        mButtonChooseImage.setOnClickListener(View.OnClickListener { openFileChooser() })
        mButtonUpload.setOnClickListener(View.OnClickListener {
            if (mUploadTask != null && mUploadTask.isInProgress) {
                Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT)
                    .show()
            } else {
                uploadFile()
            }
        })
        mTextViewShowUploads.setOnClickListener(View.OnClickListener { openImagesActivity() })
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val mImageView: ImageView = findViewById<ImageView>(R.id.image_view)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null
        ) {
            mImageUri = data.data
            Picasso.with(this).load(mImageUri).into(mImageView)
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun uploadFile() {
        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads")
        val mProgressBar: ProgressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
        val mEditTextFileName: EditText = findViewById<EditText>(R.id.edit_text_file_name)
        var mUploadTask: StorageTask<*>? = null
        if (mImageUri != null) {
            val fileReference = mStorageRef.child(
                System.currentTimeMillis()
                    .toString() + "." + getFileExtension(mImageUri!!)
            )
            mUploadTask = fileReference.putFile(mImageUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    val handler = Handler()
                    handler.postDelayed({ mProgressBar.progress = 0 }, 500)
                    Toast.makeText(this, "Upload successful", Toast.LENGTH_LONG)
                        .show()
                    val upload = NewUpload(
                        mEditTextFileName.text.toString().trim { it <= ' ' },
                        taskSnapshot.metadata?.reference?.downloadUrl.toString()
                    )
                    val uploadId = mDatabaseRef.push().key
                    mDatabaseRef.child(uploadId!!).setValue(upload)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress =
                        100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    mProgressBar.progress = progress.toInt()
                }
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImagesActivity() {
        val intent = Intent(this, ImagesActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}