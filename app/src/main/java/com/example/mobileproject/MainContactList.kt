package com.example.mobileproject

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.ContactData
import com.example.mobileproject.datas.UserData
import com.example.mobileproject.networking.ApiEndPoint
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainContactList.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainContactList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_contact_list, container, false)
        fetchContacts()
        // Inflate the layout for this fragment
        return rootView
    }

    private fun fetchContacts() {
        var contactData = ArrayList<ContactData>()

        // Status info
        val loading = ProgressDialog(activity)
        loading.setMessage("Logging In . . .")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CONTACT_READ_CONTACT)
            .addBodyParameter("user_id", SESSION_USER_ID)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val jsonArray = response?.optJSONArray("result")
                    if (jsonArray?.length() == 0) {
                        Toast.makeText(this@MainContactList.context, "Contact Data Fetch Failed", Toast.LENGTH_SHORT)
                            .show()
                        loading.dismiss()
                    } else {
                        Toast.makeText(this@MainContactList.context,
                            "Contact Data Fetch Success", Toast.LENGTH_SHORT)
                            .show()
                        for (i in 0 until jsonArray?.length()!!) {
                            val jsonObject = jsonArray.optJSONObject(i)
                            contactData.add(
                                ContactData(
                                    jsonObject.getString("contact_id"),
                                    jsonObject.getString("contact_ppicture"),
                                    jsonObject.getString("contact_fname"),
                                    jsonObject.getString("contact_lname"),
                                    jsonObject.getString("contact_email"),
                                    jsonObject.getString("contact_phone_number"),
                                    jsonObject.getString("contact_stars"),
                                    jsonObject.getString("fk_contact_type_id"),
                                    jsonObject.getString("fk_user_id")
                                )
                            )

                            if (jsonArray.length() - 1 == i) {
                                loading.dismiss()
                            }
                        }
                        Log.i("Contacts data", contactData.toString())
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.e("ON ERROR", anError?.errorDetail.toString())
                    Toast.makeText(
                        this@MainContactList.context,
                        "Failure, " + anError.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainContactList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}