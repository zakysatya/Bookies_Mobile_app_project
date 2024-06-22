package com.example.se_aol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            if(!registerValidation()) {
                return@setOnClickListener
            }
            else {
                Toast.makeText(this,"Register Success!", Toast.LENGTH_SHORT).show()
                goToLogin()
            }
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

    private fun registerValidation(): Boolean {
        val email = findViewById<EditText>(R.id.inputTextEmail).text.toString()
        val username = findViewById<EditText>(R.id.inputTextUsername).text.toString()
        val password = findViewById<EditText>(R.id.inputTextPassword).text.toString()

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,"Cant Empty!", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (password.length < 3 || password.length >8) {
            return false
        }
        // TODO: masukkan data ke database 


        return true
    }
}