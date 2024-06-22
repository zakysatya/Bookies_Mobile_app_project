package com.example.se_aol.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.se_aol.LoginActivity
import com.example.se_aol.R
import com.example.se_aol.fragment.communityActivity.ForumActivity
import com.example.se_aol.model.Users

class HomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val userData = arguments?.getSerializable("user", Users::class.java) as Users

        val welcomeText: TextView = view.findViewById(R.id.welcomeText)
        if (userData != null) {
            welcomeText.text = "Welcome ${userData.username}!"
        }

        val buttonForum : CardView = view.findViewById(R.id.yourForumContainer)
        buttonForum.setOnClickListener {
            val intent = Intent(activity, ForumActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}