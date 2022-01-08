package com.example.mobileproject

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.networking.ApiEndPoint
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.json.JSONObject

class ContactDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_contact_details)
        inputInit()

        contact_details_back_btn.setOnClickListener {  // Action after BACK button pressed
            startActivity(Intent(this, MainActivity::class.java))
        }
        contact_details_commit_save_details.setOnClickListener {  // Action after SAVE button pressed
            saveDetails()
        }
        contact_details_commit_delete_contact.setOnClickListener {  // Action after DELETE contact button pressed
            deleteContact()
        }

    }

    private fun inputInit() {  // Initializer for multi choice input
        val contactTypes = resources.getStringArray(R.array.contact_type)

        if (contact_details_contact_type != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                contactTypes)
            contact_details_contact_type.adapter = adapter
        }

    }

    private fun saveDetails() {  // Save any change of contact details (UPDATE).
        // Contact UPDATE details data prep
        val idV = "320"  // Current contact id
        val phoneNumberV = contact_details_phone_number.text.toString()
        val emailV = contact_details_email.text.toString()
        val fnameV = contact_details_fname.text.toString()
        val lnameV = contact_details_lname.text.toString()
        val ppictureV = "updatedPicture.jpg"
        val starsV = "1"
        val fkContactTypeIdV = "2"

        fun processRequest() {
            // Status info
            val loading = ProgressDialog(this)
            loading.setMessage("Menambahkan data.......")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.CONTACT_UPDATE_CONTACT)
                .addBodyParameter("contact_id", idV)
                .addBodyParameter("contact_phone_number", phoneNumberV)
                .addBodyParameter("contact_email", emailV)
                .addBodyParameter("contact_fname", fnameV)
                .addBodyParameter("contact_lname", lnameV)
                .addBodyParameter("contact_ppicture", ppictureV)
                .addBodyParameter("contact_stars", starsV)
                .addBodyParameter("fk_contact_type_id", fkContactTypeIdV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(
                            applicationContext,
                            response?.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()

                        if (response?.getString("message")?.contains("successfully")!!) {
                            this@ContactDetails.finish()
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

    private fun deleteContact() {  // Delete current contact based on id (DELETE).
        // Contact DELETE data prep
        val idV = "60"  // Current contact id

        // Status info
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data.......")
        loading.show()

        fun processRequest() {
            AndroidNetworking.post(ApiEndPoint.CONTACT_DELETE_CONTACT)
                .addBodyParameter("contact_id", idV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        loading.dismiss()
                        Toast.makeText(
                            applicationContext,
                            response?.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()

                        if (response?.getString("message")?.contains("successfully")!!) {
                            this@ContactDetails.finish()
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
        startActivity(Intent(this, MainActivity::class.java))
    }
}