package com.example.se_aol

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.se_aol.database.DatabaseHelper
import com.example.se_aol.model.Users
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var buttonSignIn: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

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

            if (username == "admin" && password == "password") {
                // Replace with your login success logic
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Example: Navigate to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                val user = Users()
                user.email = "admin@admin.com"
                user.username = "admin"
                user.password = "password"
                intent.putExtra("user", user)
                startActivity(intent)
                finish()
            }

            if (username.isEmpty()) {
                inputUsername.error = "Username is required"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                inputPassword.error = "Password is required"
                return@setOnClickListener
            }
            if (!isUsernameExists(username)) {
                inputUsername.error = "Username not found"
                return@setOnClickListener
            }
            if (!isPasswordCorrect(username, password)) {
                inputPassword.error = "Incorrect password"
                return@setOnClickListener
            }

            login(username, password)
            // Dummy validation: Check if username is "admin" and password is "password"

        }
    }

    private fun login(username: String, password: String) {
        val user = getUserFromDatabase(username)
        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
    }

    fun getUserFromDatabase(username: String?): Users? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users WHERE Username = ?", arrayOf(username))
        if (cursor.moveToFirst()) {
            val email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
            val username = cursor.getString(cursor.getColumnIndexOrThrow("Username"))
            val password = cursor.getString(cursor.getColumnIndexOrThrow("Password"))
            cursor.close()
            val user = Users()
            user.email =email
            user.username =username
            user.password = password
            return user
        }
        cursor.close()
        return null
    }

    private fun isUsernameExists(username: String): Boolean {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT 1 FROM Users WHERE Username = ?", arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    private fun isPasswordCorrect(username: String, password: String): Boolean {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT 1 FROM Users WHERE Username = ? AND Password = ?", arrayOf(username, password))
        val correct = cursor.count > 0
        cursor.close()
        return correct
    }
}
