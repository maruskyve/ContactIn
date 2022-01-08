package com.example.mobileproject

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.DataPrep
import com.example.mobileproject.datas.userData
import com.example.mobileproject.networking.ApiEndPoint
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_register.*
import org.json.JSONObject

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        supportActionBar?.hide()
        dataInit()

        user_profile_commit_back.setOnClickListener {  // Action after BACK button clicked
            startActivity(Intent(this, MainActivity::class.java))
        }

        user_profile_commit_ppicture.setOnClickListener {  // Action after CHANGE picture clicked
            openGalleryForImage()
//            DataPrep().FetchUserData()
        }

        user_profile_commit_save_changes.setOnClickListener {  // Action after SAVE button clicked
            saveProfileChanges()
        }

        user_profile_commit_delete_account.setOnClickListener {  // Action after DELETE button clicked
            deleteAccount()
        }
    }

    //  For photo profile
    val REQUEST_CODE = 100
    private fun openGalleryForImage() {  // Method to open gallery
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            user_profile_ppicture.setImageURI(data?.data) // handle chosen image
            Log.i("USER PPICTURE", data?.data.toString())
        }
    }

    private fun dataInit() {
        val data = userData[0]
//        Toast.makeText(this, userData.toString(), Toast.LENGTH_LONG).show()
        Log.i("User Profile", userData.toString())
        user_profile_username.setText(data.userName)
        user_profile_password.setText(data.userPassword)
        user_profile_phone_number.setText(data.userPhoneNumber)
        user_profile_email.setText(data.userEmail)
        user_profile_fname.setText(data.userFName)
        user_profile_lname.setText(data.userLName)
//        user_profile_gender_rg.check()
//        user_profile_ppicture.setImageURI()

    }

    private fun saveProfileChanges() {  // Save any changes of user profile (UPDATE)
        // Profile UPDATE data prep
        val idV = SESSION_USER_ID
        val usernameV = user_profile_username.text.toString()
        val phoneNumberV = user_profile_phone_number.text.toString()
        val emailV = user_profile_email.text.toString()
        val passwordV = user_profile_password.text.toString()
        val fnameV = user_profile_fname.text.toString()
        val lnameV = user_profile_lname.text.toString()
        var genderV = if (user_profile_gender_rg.checkedRadioButtonId > 0)  // Error handler: unwanted rb id
            findViewById<RadioButton>(user_profile_gender_rg.checkedRadioButtonId).text.toString() else ""
        genderV = if (genderV == "Male") "M" else "F"
        val ppictureV = "pic.jpg"

        fun processRequest() {
            val loading = ProgressDialog(this)
            loading.setMessage("Saving Profile . . .")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.USER_UPDATE_ACCOUNT)
                .addBodyParameter("user_id", idV)
                .addBodyParameter("user_name", usernameV)
                .addBodyParameter("user_phone_number", phoneNumberV)
                .addBodyParameter("user_email", emailV)
                .addBodyParameter("user_password", passwordV)
                .addBodyParameter("user_fname", fnameV)
                .addBodyParameter("user_lname", lnameV)
                .addBodyParameter("user_gender", genderV)
                .addBodyParameter("user_ppicture", ppictureV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(applicationContext, response?.getString("message"), Toast.LENGTH_SHORT).show()

                        if (response?.getString("message")?.contains("successfully")!!){
                            this@UserProfile.finish()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        processRequest()
        DataPrep().FetchUserData()
    }

    private fun deleteAccount() {  // Delete account of current user
        fun processRequest() {
            val loading = ProgressDialog(this)
            loading.setMessage("Deleting Account . . .")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.USER_DELETE_ACCOUNT)
                .addBodyParameter("user_id", SESSION_USER_ID)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(applicationContext, response?.getString("message"), Toast.LENGTH_SHORT).show()

                        if (response?.getString("message")?.contains("successfully")!!){
                            this@UserProfile.finish()
                        }
                    }

                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ON ERROR", anError?.errorDetail.toString())
                        Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        processRequest()
    }


}