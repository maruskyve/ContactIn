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

        login_commit_btn.setOnClickListener {  // Action after LOGIN button clicked
            loginValidation()
        }

        account_register.setOnClickListener {  // Action after REGISTER button clicked
            startActivity(Intent(this, UserRegister::class.java))
        }
    }
    private fun loginValidation() : Boolean {
        var userData = ArrayList<UserData>()
        var validData = false
        // Data prep
        val sampleUsername = "" // default: admin
        val samplePassword = ""

//        Toast.makeText(this, "Checking yodur credentials\n" +
//                "Username: ${login_username.text.toString()}\n" +
//                "Password: ${login_password.text.toString()}", Toast.LENGTH_SHORT).show()

        fun validateCredential() {
            val usernameV = login_username.text.toString()
            val passwordV = login_password.text.toString()
            val usernameParameter = "?login_username=$usernameV"
            val passwordParameter = "&login_password=$passwordV"
            Log.i("", ApiEndPoint.USER_READ_LOGIN+usernameParameter+passwordParameter)

            AndroidNetworking.get(ApiEndPoint.USER_READ_LOGIN+usernameParameter+passwordParameter)
//                .addPathParameter("login_username", usernameV)
//                .addPathParameter("login_password", passwordV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val jsonArray = response?.optJSONArray("result")

                        if (jsonArray?.length() == 0)  {
                            Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                        }

                        for (i in 0 until jsonArray?.length()!!){
                            val jsonObject = jsonArray.optJSONObject(i)
                            userData.add(
                                UserData(jsonObject.getString("user_id"),
                                    jsonObject.getString("user_name"),
                                    jsonObject.getString("user_email"),
                                    jsonObject.getString("user_phone_number"),
                                    jsonObject.getString("user_password"),
                                    jsonObject.getString("user_fname"),
                                    jsonObject.getString("user_lname"),
                                    jsonObject.getString("user_gender"),
                                    jsonObject.getString("user_ppicture")))

                            if (jsonArray.length()-1 == i){
//                                loading.dismiss()
                            }
                        }
//                        Log.i("MainActivity ", "Load all notes : "+ arrayList.size.toString())
                    }

                    override fun onError(anError: ANError?) {
//                        loading.dismiss()
                        Log.e("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext, "Failure," + anError.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        validateCredential()

        Log.d("", "USER LOGIN")
        for (i in userData) {
            Log.d("PRINT", i.userName)
        }

//        if (username == sampleUsername && password == samplePassword) {
//            Toast.makeText(this, "Credential match\nLogging In", Toast.LENGTH_SHORT).show()
//
//            LoginState().setLogin()
//            startActivity(Intent(this, MainActivity::class.java))
//        }

        return validData
    }
}