package com.example.mobileproject

import android.app.Activity
import android.app.AlertDialog
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
import com.example.mobileproject.datas.*
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
        }

        user_profile_commit_save_changes.setOnClickListener {  // Action after SAVE button clicked
            saveProfileChanges()
        }

        user_profile_commit_delete_account.setOnClickListener {  // Action after DELETE button clicked
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Account Deletion Confirmation")
            builder.setMessage("Are you sure want to delete this Account?\nThis action cannot be undo")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes"){dialogInterface, which ->
                deleteAccount()

            }

            builder.setNeutralButton("Cancel"){dialogInterface , which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
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
        Log.i("User Profile", userData.toString())
        user_profile_username.setText(data.userName)
        user_profile_password.setText(data.userPassword)
        user_profile_phone_number.setText(data.userPhoneNumber)
        user_profile_email.setText(data.userEmail)
        user_profile_fname.setText(data.userFName)
        user_profile_lname.setText(data.userLName)
        Log.i("User gender", data.userGender)
        user_profile_gender_rg.check(if (data.userGender == "M") 1 else 2)  // Bug in second view profile -> radio button not checked
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
                            Log.i("User profile update", response.toString())
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
//                            this@UserProfile.finish()
                            // Reset App state
                            SESSION_LOGIN = false
                            SESSION_LOGIN_DT = ""
                            SESSION_USER_ID = ""
                            SESSION_CONTACT_TYPE_DATA_FETCH = false
                            SESSION_USER_DATA_FETCH = false
                            SESSION_CONTACT_DATA_FETCH = false
                            SINGLE_FILES = ""

                            startActivity(Intent(this@UserProfile, MainActivity::class.java))
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
