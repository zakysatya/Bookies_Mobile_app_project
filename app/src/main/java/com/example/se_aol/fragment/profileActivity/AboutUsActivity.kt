package com.example.se_aol.fragment.profileActivity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.se_aol.R

class AboutUsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        val buttonBack = findViewById<Button>(R.id.btnHistoryBack)
        buttonBack.setOnClickListener { onBackPressed() }
    }
}