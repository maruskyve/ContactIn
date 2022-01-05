package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_login.*

class UserLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_user_login)

        login_commit_btn.setOnClickListener {  // Action after LOGIN button clicked
            loginValidation(login_username.text.toString(), login_password.text.toString())
        }
        account_register.setOnClickListener {  // Action after REGISTER button clicked
            startActivity(Intent(this, UserRegister::class.java))
        }
    }
    private fun loginValidation(username: String?, password: String?) : Boolean {
        var validData = false
        // Data prep
        val sampleUsername = "admin"
        val samplePassword = "admin"

        Toast.makeText(this, "Checking yodur credentials\n" +
                "Username: $username\nPassword: $password", Toast.LENGTH_SHORT).show()

        if (username == sampleUsername && password == samplePassword) {
            Toast.makeText(this, "Credential match\nLogging In", Toast.LENGTH_SHORT).show()
        }

        return validData
    }
}