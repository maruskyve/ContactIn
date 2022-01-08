package com.example.mobileproject.datas

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.SESSION_CONTACT_TYPE_DATA_FETCH
import com.example.mobileproject.SESSION_USER_ID
import com.example.mobileproject.networking.ApiEndPoint
import org.json.JSONObject

class DataPrep {
    fun FetchContactTypeData() {
        if (!SESSION_CONTACT_TYPE_DATA_FETCH) {
            AndroidNetworking.post(ApiEndPoint.CONTACT_TYPE_READ)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val jsonArray = response?.optJSONArray("result")

                        if (jsonArray?.length() == 0) {
                            Log.i("", "Contact Data Type is empty")
                        } else {
                            for (i in 0 until jsonArray?.length()!!) {
                                val jsonObject = jsonArray.optJSONObject(i)
                                contactTypeData.add(
                                    ContactTypeData(
                                        jsonObject.getString("contact_type_id"),
                                        jsonObject.getString("contact_type_name")
                                    )
                                )

                                if (jsonArray.length() - 1 == i) {
                                    Log.i("", "Data fetch complete")
                                }
                            }
                            Log.i("", "Contact Data Type found")
                            Log.i("Contact Type Data", contactTypeData.toString())
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Failure", anError?.errorDetail.toString())
                    }
                })
        }
    }

    fun FetchUserData() {
        // Reset datas
        userData.clear()

        AndroidNetworking.get(ApiEndPoint.USER_READ_ACCOUNT)
            .addQueryParameter("requestContext", "fetch")
            .addQueryParameter("user_id", SESSION_USER_ID)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val jsonArray = response?.optJSONArray("result")
                    if (jsonArray?.length() == 0) {
                    }
                    else {
                        Log.i("Data Prep","Data Fetch Success")
                        for (i in 0 until jsonArray?.length()!!) {
                            val jsonObject = jsonArray.optJSONObject(i)
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
                            }
                        }
                    }
                    Log.i("Data Prep", userData.toString())
                }

                override fun onError(anError: ANError?) {
                    Log.e("Data Prep ON ERROR", anError?.errorDetail.toString())
                }
            })
    }

    fun FetchContactData() {
        // Reset datas
        contactData.clear()

        AndroidNetworking.post(ApiEndPoint.CONTACT_READ_CONTACT)
            .addBodyParameter("user_id", SESSION_USER_ID)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val jsonArray = response?.optJSONArray("result")
                    if (jsonArray?.length() == 0) {
                        Log.i("Data Prep", "Contact Data Fetch Failed")
                    } else {
                        Log.i("Data Prep", "Contact Data Fetch Success")

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

                            }
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("ON ERROR", anError?.errorDetail.toString())
                }
            })
    }
}