package com.example.se_aol.fragment.profileActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.example.se_aol.R

class SettingsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        // TODO: settings berisi 3 menu cahnge nama, password, dan about us


        val buttonBack = findViewById<Button>(R.id.btnSettingsBack)
        buttonBack.setOnClickListener { onBackPressed() }

        val buttonAboutUs: Button = findViewById(R.id.btnSettingsAboutUs)
        buttonAboutUs.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
    }
}