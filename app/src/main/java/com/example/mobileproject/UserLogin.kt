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
import com.example.mobileproject.datas.userData
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
    }

    private fun loginValidation(){  // Validate login credential (READ)
        // Login data prep
        val usernameV = login_username.text.toString()
        val passwordV = login_password.text.toString()

        // Pre-defined login credential to skip login, please comment both variable above
//        val usernameV = "superadmin"
//        val passwordV = "superadminpw"

        fun processRequest() {
            // Status info
            val loading = ProgressDialog(this)
            loading.setMessage("Logging In . . .")
            loading.show()

            AndroidNetworking.get(ApiEndPoint.USER_READ_ACCOUNT)
                .addQueryParameter("requestContext", "login")
                .addQueryParameter("login_password", passwordV)
                .addQueryParameter("login_username", usernameV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val jsonArray = response?.optJSONArray("result")
                        if (jsonArray?.length() == 0) {
                            Toast.makeText(applicationContext, "Login Failed, Please Enter Correct Username and Password", Toast.LENGTH_SHORT)
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
                            SESSION_LOGIN = true
                            SESSION_USER_DATA_FETCH = true
                            startActivity(Intent(this@UserLogin, MainActivity::class.java))
                        }
                        Log.i("UserData: ", UserData::userName.toString())
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.e("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(
                            applicationContext,
                            "Failure, " + anError.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
        if (!SESSION_LOGIN) {
            processRequest()
        }
    }
}
