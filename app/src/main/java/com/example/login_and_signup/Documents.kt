package com.example.login_and_signup

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_documents.*
import java.io.IOException


class Documents : AppCompatActivity(),View.OnClickListener {
    private val PICK_IMAGE_REQUEST = 234

    private val CAMERA_PERMISSION_CODE = 100
    private val STORAGE_PERMISSION_CODE = 101

    //Buttons
    private lateinit var buttonChoose: Button
    private lateinit var buttonUpload: Button

    //ImageView
    private val imageView: ImageView? = null

    //a Uri object to store file path
    private var filePath: Uri? = null

    private val mStorageRef: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        documents_toolbar.setNavigationIcon(R.drawable.ic_back)
        documents_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        //getting views from layout
        buttonChoose =  findViewById<Button>(R.id.button_Choose);
        buttonUpload =  findViewById<Button>(R.id.button_Upload);
        val imageView =  findViewById<ImageView>(R.id.image_View);

        //attaching listener
        buttonChoose.setOnClickListener(this)
        buttonUpload.setOnClickListener(this)

    }
    private fun showFileChooser() {
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    //handling the image chooser activity result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED){
            if (requestCode == PICK_IMAGE_REQUEST && data != null && data.data != null) {
                filePath = data.data
                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                    imageView?.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        //if the clicked button is choose
        if (v == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (v == buttonUpload) {
            uploadFile();
        }
    }

    private fun uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading")
            progressDialog.show()
            val riversRef: StorageReference =
                FirebaseStorage.getInstance().reference.child("images/pic.jpg")
            riversRef.putFile(filePath!!)
            riversRef.downloadUrl.addOnSuccessListener { Uri->

                val imageURL = Uri.toString()
                val imagetest = findViewById<ImageView>(R.id.image_View)
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "File Uploaded ",
                    Toast.LENGTH_LONG).show()

                Glide.with(this).load(imageURL)
                    .into(imagetest)

            }

                .addOnFailureListener { exception -> //if the upload is not successfull
                    //hiding the progress dialog
                    progressDialog.dismiss()

                    //and displaying error message
                    Toast.makeText(applicationContext, exception.message,
                        Toast.LENGTH_LONG).show()
                }

        } else {
            Toast.makeText(applicationContext, "error ", Toast.LENGTH_LONG).show()
        }
    }

    // Function to check and request permission
    fun checkPermission(permission: String, requestCode: Int) {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(this, permission)
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat
                .requestPermissions(this, arrayOf(permission),
                    requestCode)
        } else {
            Toast
                .makeText(this, "Permission already granted",
                    Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {

                // Showing the toast message
                Toast.makeText(this, "Camera Permission Granted",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied",
                    Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Storage Permission Granted",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }






}