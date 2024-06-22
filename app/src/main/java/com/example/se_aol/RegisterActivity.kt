package com.example.se_aol

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.se_aol.database.DatabaseHelper
import com.example.se_aol.model.Users
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbHelper : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = DatabaseHelper(this)

        val buttonRegister: Button = findViewById(R.id.buttonRegisterSignUp)
        buttonRegister.setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.inputTextEmail).text.toString()
            val username = findViewById<TextInputEditText>(R.id.inputTextUsername).text.toString()
            val password = findViewById<TextInputEditText>(R.id.inputTextPassword).text.toString()

            val validation = registerValidation(email, username, password)
            if(!validation) {
                return@setOnClickListener
            }
                Toast.makeText(this,"Register Success!", Toast.LENGTH_SHORT).show()
                val newUser = Users()
                newUser.username = username
                newUser.email = email
                newUser.password = password
                addUserToDatabase(newUser)



                val builder = AlertDialog.Builder(this)
                builder.setTitle("Registration")
                builder.setMessage("Registration success!")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext, "Registration Success!", Toast.LENGTH_SHORT).show()
                    goToLogin()
                }
            builder.show()
        }

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerValidation(email: String, username: String, password: String): Boolean {

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,"Cant Empty!", Toast.LENGTH_SHORT).show()
            return false
        }

        //valdiasi userrname
        if (userExists(username)) {
            //else if ( userList.any { it.Username == username.toString() }) {
            Toast.makeText(this, "Username already registered", Toast.LENGTH_LONG).show()
            return false
        } else if (username.length <8 || username.length > 15 ) {
            Toast.makeText(this,"Username’s length of at least 8 characters", Toast.LENGTH_LONG).show()
            return false
        } else if (!username.matches(Regex("^[a-zA-Z0-9]*$"))) {
            Toast.makeText(this, "Username must be alphabet and numeric", Toast.LENGTH_LONG).show()
            return false
        }
        //validasi Password
        if (password.length < 5) {
            Toast.makeText(this, "Password must be at least 5 characters", Toast.LENGTH_LONG).show()
            return false
        } else if (!isPasswordValid(password)) {
            Toast.makeText(this, "Password must contain at least 1 letter, 1 number, and 1 symbol", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    private fun userExists(username : String): Boolean {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users WHERE Username = ?", arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.length < 5) {
            return false
        }
        val hasLetter = password.any { it.isLetter() }
        val hasDigit = password.any { it.isDigit() }
        val hasSymbol = password.any { !it.isLetterOrDigit() }

        return hasLetter && hasDigit && hasSymbol
    }

    private fun addUserToDatabase(user: Users){
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val sql = "INSERT INTO Users (Email, Username, Password) VALUES (?, ?, ?)"
        val statement = db.compileStatement(sql)
        statement.bindString(1, user.email)
        statement.bindString(2, user.username)
        statement.bindString(3, user.password)
        statement.executeInsert()
    }
}