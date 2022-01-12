package com.example.mobileproject

import android.app.AlertDialog
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
import com.example.mobileproject.datas.contactData
import com.example.mobileproject.datas.contactTypeData
import com.example.mobileproject.networking.ApiEndPoint
import kotlinx.android.synthetic.main.activity_contact_details.*
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.json.JSONObject

class ContactDetails : AppCompatActivity() {
    private var currentItemPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hide action bar
        setContentView(R.layout.activity_contact_details)
        currentItemPosition = intent.getStringExtra("itemPosition")!!.toInt()
        inputInit()
        dataInit()

        contact_details_back_btn.setOnClickListener {  // Action after BACK button pressed
            startActivity(Intent(this, MainActivity::class.java))
        }
        contact_details_commit_save_details.setOnClickListener {  // Action after SAVE button pressed
            saveDetails()
        }
        contact_details_commit_delete_contact.setOnClickListener {  // Action after DELETE contact button pressed
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Contact Deletion Confirmation")
            builder.setMessage("Are you sure want to delete this Contact?\nThis action cannot be undo")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes"){dialogInterface, which ->
                deleteContact()
            }

            builder.setNeutralButton("Cancel"){dialogInterface , which ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun inputInit() {  // Initializer for multi choice input
        val contactTypeName = arrayListOf<String>()
        for (x in contactTypeData) {  // Fetch type name/per row
            contactTypeName.add(x.contactTypeName)
        }
        if (contact_details_contact_type != null) {  // Set contact type spinner values based on position
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                contactTypeName)
            contact_details_contact_type.adapter = adapter
            contact_details_contact_type.setSelection(contactData[currentItemPosition].contactTypeId.toInt()-1)
        }
    }

    private fun dataInit() {
        val data = contactData[intent.getStringExtra("itemPosition")!!.toInt()]

        contact_details_fname.setText(data.contactFName)
        contact_details_lname.setText(data.contactLName)
        contact_details_phone_number.setText(data.contactPhoneNumber)
        contact_details_email.setText(data.contactEmail)
    }

    private fun saveDetails() {  // Save any change of contact details (UPDATE).
        // Contact UPDATE details data prep
        val idV = intent.getStringExtra("contactId").toString()  // Current contact id
        val phoneNumberV = contact_details_phone_number.text.toString()
        val emailV = contact_details_email.text.toString()
        val fnameV = contact_details_fname.text.toString()
        val lnameV = contact_details_lname.text.toString()
        val ppictureV = fnameV.first().uppercase()+lnameV.first().uppercase()
        val starsV = "0"
        val fkContactTypeIdV = (contact_details_contact_type.selectedItemId+1).toString()

        fun processRequest() {
            // Status info
            val loading = ProgressDialog(this)
            loading.setMessage("Updating Contact . . .")
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
        val idV = intent.getStringExtra("contactId").toString()  // Current contact id

        // Status info
        val loading = ProgressDialog(this)
        loading.setMessage("Deleting Contact . . .")
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