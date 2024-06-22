package com.example.se_aol.fragment.communityActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.se_aol.R

class ForumActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_forum)

        val buttonBack = findViewById<Button>(R.id.btnSettingsBack)
        buttonBack.setOnClickListener { onBackPressed() }
    }
}