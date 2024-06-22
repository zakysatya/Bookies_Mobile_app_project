package com.example.se_aol.fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.se_aol.R
import com.example.se_aol.fragment.profileActivity.HistoryActivity
import com.example.se_aol.fragment.profileActivity.SettingsActivity

class ProfileFragment: androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnSetting: Button = view.findViewById(R.id.btnProfileSettings)
        btnSetting.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
        val btnHistory: Button = view.findViewById(R.id.btnProfileHistory)
        btnHistory.setOnClickListener {
            val intent = Intent(activity, HistoryActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}