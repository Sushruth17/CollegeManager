package com.example.login_and_signup

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_documents.*

class Documents : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        documents_toolbar.setNavigationIcon(R.drawable.ic_back)
        documents_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

    }

}