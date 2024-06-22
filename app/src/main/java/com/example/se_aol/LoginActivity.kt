package com.example.se_aol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var buttonSignIn: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputUsername = findViewById(R.id.inputTextUsername)
        inputPassword = findViewById(R.id.inputTextPassword)
        buttonSignIn = findViewById(R.id.buttonSignIn)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonSignIn.setOnClickListener {
            val username = inputUsername.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if (username.isEmpty()) {
                inputUsername.error = "Username is required"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                inputPassword.error = "Password is required"
                return@setOnClickListener
            }

            // Dummy validation: Check if username is "admin" and password is "password"
            if (username == "admin" && password == "password") {
                // Replace with your login success logic
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Example: Navigate to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Replace with your actual authentication logic
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
