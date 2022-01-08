package com.example.mobileproject

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.ContactData
import com.example.mobileproject.datas.UserData
import com.example.mobileproject.datas.contactData
import com.example.mobileproject.networking.ApiEndPoint
import com.example.universtitaskotlin.RVAdapterContact
import kotlinx.android.synthetic.main.fragment_main_contact_list.*
import kotlinx.android.synthetic.main.main_contact_list.*
import kotlinx.android.synthetic.main.main_contact_list.view.*
import org.json.JSONObject
import java.lang.Exception

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
//    private var layoutManagerV : RecyclerView.LayoutManager? = null
//    private var adapterV : RecyclerView.Adapter<RVAdapterContact.Holder>? = null
    private lateinit var rvContact : RecyclerView

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
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main_contact_list, container, false)
        fetchContacts()
//        contact_list_rv.apply {
//            layoutManagerV = LinearLayoutManager(activity)
//            adapterV = RVAdapterContact(requireActivity().applicationContext, contactData)
//        }
        rvContact = rootView.findViewById(R.id.contact_list_rv)
        rvContact.setHasFixedSize(true)
        rvContact.layoutManager =LinearLayoutManager(context)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        loadContact()
    }

    private fun loadContact() {
        val adapter= context?.let { RVAdapterContact(it, contactData) }
        adapter?.notifyDataSetChanged()
        rvContact.adapter = adapter
    }

    private fun fetchContacts() {
        if (!SESSION_CONTACT_DATA_FETCH) {
            // Status info
            val loading = ProgressDialog(activity)
            loading.setMessage("Loading . . .")
            loading.show()

            AndroidNetworking.post(ApiEndPoint.CONTACT_READ_CONTACT)
                .addBodyParameter("user_id", SESSION_USER_ID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val jsonArray = response?.optJSONArray("result")
                        if (jsonArray?.length() == 0) {
                            Toast.makeText(
                                this@MainContactList.context,
                                "Contact Data Fetch Failed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            loading.dismiss()
                        } else {
                            Toast.makeText(
                                this@MainContactList.context,
                                "Contact Data Fetch Success", Toast.LENGTH_SHORT
                            )
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
            SESSION_CONTACT_DATA_FETCH = true
        }
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