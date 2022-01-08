package com.example.mobileproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.networking.ApiEndPoint
import com.example.mobileproject.datas.UserData
import kotlinx.android.synthetic.main.activity_user_login.*
import org.json.JSONObject

class UserLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_user_login)

        login_commit_login.setOnClickListener {  // Action after LOGIN button clicked
            loginValidation()
        }

        login_commit_register.setOnClickListener {  // Action after REGISTER button clicked
            startActivity(Intent(this, UserRegister::class.java))
        }

        // TEMP
        login_commit_view_contact.setOnClickListener {
            startActivity(Intent(this, ContactDetails::class.java))
        }
    }


    private fun loginValidation() : Boolean {  // Validate login credential (READ)
        // Login data prep
        val usernameV = login_username.text.toString()
        val passwordV = login_password.text.toString()

        var validLogin = false
        var userData = ArrayList<UserData>()

        // Status info
        val loading = ProgressDialog(this)
        loading.setMessage("Logging In . . .")
        loading.show()

        fun processRequest() {
            AndroidNetworking.post(ApiEndPoint.USER_READ_LOGIN)
                .addBodyParameter("login_username", usernameV)
                .addBodyParameter("login_password", passwordV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val jsonArray = response?.optJSONArray("result")
                        if (jsonArray?.length() == 0) {
                            Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT)
                                .show()
                            loading.dismiss()
                        } else {
                            Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT)
                                .show()
                            for (i in 0 until jsonArray?.length()!!) {
                                val jsonObject = jsonArray.optJSONObject(i)
                                SESSION_USER_ID = jsonObject.getString("user_id").toString()
                                userData.add(
                                    UserData(
                                        jsonObject.getString("user_id"),
                                        jsonObject.getString("user_name"),
                                        jsonObject.getString("user_email"),
                                        jsonObject.getString("user_phone_number"),
                                        jsonObject.getString("user_password"),
                                        jsonObject.getString("user_fname"),
                                        jsonObject.getString("user_lname"),
                                        jsonObject.getString("user_gender"),
                                        jsonObject.getString("user_ppicture")
                                    )
                                )

                                if (jsonArray.length() - 1 == i) {
                                    loading.dismiss()
                                }
                            }
                            Log.i("SESSION USER ID", SESSION_USER_ID)
                            Log.i("USER ID", userData[0].toString())

                            validLogin = true
                            SESSION_LOGIN = true
                            SESSION_USER_ID = ""
                        }
                        Log.i("UserData: ", UserData::userName.toString())
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.e("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext,
                            "Failure, " + anError.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }

        //TEMP
        SESSION_USER_ID = "37578"
        SESSION_LOGIN = true
        //TEMP-END
        startActivity(Intent(this, MainActivity::class.java))

        return validLogin
    }
}