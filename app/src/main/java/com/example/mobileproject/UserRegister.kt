package com.example.mobileproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.example.mobileproject.networking.ApiEndPoint
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_user_register.*
import org.json.JSONObject

class UserRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_user_register)

        register_commit_back.setOnClickListener {  // Action after top bar BACK button clicked
            startActivity(Intent(this, UserLogin::class.java))
        }

        register_commit_register.setOnClickListener {  // Action after REGISTER button clicked
            registerValidation()
        }
    }

    private fun registerValidation() {  // temp - Checking registration data entered
        // Value prep
        val idV = (0..100000).random().toString()
        val fnameV = register_fname.text.toString()
        val lnameV = register_lname.text.toString()
        val usernameV = register_username.text.toString()
        var genderV = if (register_gender_rg.checkedRadioButtonId > 0)  // Error handler: unwanted rb id
            findViewById<RadioButton>(register_gender_rg.checkedRadioButtonId).text.toString() else ""
        val phoneNumberV = register_phone_number.text.toString()
        val emailV = register_email.text.toString()
        val passwordV = register_password.text.toString()
        val pPictureV = "picture.jpg"
        genderV = if (genderV == "Male") "M" else "F"

        // INSERT DATA TO DATABASE (STATUS: WORK WO PPICTURE)
        fun createAccount() {
            val loading = ProgressDialog(this)
            loading.setMessage("Menambahkan data.......")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.USER_CREATE_ACCOUNT)
                .addBodyParameter("user_id", idV)
                .addBodyParameter("user_fname", fnameV)
                .addBodyParameter("user_lname", lnameV)
                .addBodyParameter("user_name", usernameV)
                .addBodyParameter("user_gender", genderV)
                .addBodyParameter("user_phone_number", phoneNumberV)
                .addBodyParameter("user_email", emailV)
                .addBodyParameter("user_password", passwordV)
                .addBodyParameter("user_ppicture", pPictureV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(applicationContext, response?.getString("message"), Toast.LENGTH_SHORT).show()

                        if (response?.getString("message")?.contains("successfully")!!){
                            this@UserRegister.finish()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        createAccount()

        Toast.makeText(this, "Checking your data\n" +
                "ID = $idV\n" +
                "FName = ${fnameV}\n" +
                "LName = ${lnameV}\n" +
                "Gender = $genderV\n" +
                "Phone = ${phoneNumberV}\n" +
                "Email = ${emailV}", Toast.LENGTH_SHORT).show()

        // TEMP TESTING
//        startActivity(Intent(this, ContactDetails::class.java))
    }
}