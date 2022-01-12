package com.example.mobileproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.mobileproject.datas.ContactData
import com.example.mobileproject.datas.DataPrep
import com.example.mobileproject.datas.contactData
import com.example.mobileproject.networking.ApiEndPoint
import com.google.android.material.card.MaterialCardView
import org.json.JSONObject

private var starsState = ArrayList<Boolean>()

class RVAdapterContact (private val context: Context, private var arrayList: MutableList<ContactData>)
    : RecyclerView.Adapter<RVAdapterContact.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        starsState.clear()
        for (i in 0 until arrayList.size)
            starsState.add(arrayList[i].contactStars != "0")  // If contact stars != 0 then true otherwise false

        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.main_contact_list, parent,false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val idV = arrayList[position].contactId
        val ppictureV = arrayList[position].contactPPicture
        val nameV = arrayList[position].contactFName+" "+arrayList.get(position).contactLName
        val starsV = arrayList[position].contactStars

        holder.tvId.text = idV
        holder.tvPPicture.text = ppictureV
        holder.tvName.text = if(nameV.length >= 18) nameV.substring(0, 18)+"..."
                                else nameV
        holder.tvStars.background.setTint(
            if(starsV == "1") ContextCompat.getColor(context, R.color.goldenrod)
            else ContextCompat.getColor(context, R.color.light_grey))
        holder.tvStars.setOnClickListener {  // Action after STARS button clicked
            // Contact stars UPDATE data prep
            val phoneNumberV = arrayList.get(position).contactPhoneNumber
            val emailV = arrayList[position].contactEmail
            val fnameV = arrayList[position].contactFName
            val lnameV = arrayList[position].contactLName
            val newStarsV = if (contactData[position].contactStars == "0") "1" else "0"
            val fkContactTypeIdV = (arrayList[position].contactTypeId)

            AndroidNetworking.post(ApiEndPoint.CONTACT_UPDATE_CONTACT)
                .addBodyParameter("contact_id", idV)
                .addBodyParameter("contact_phone_number", phoneNumberV)
                .addBodyParameter("contact_email", emailV)
                .addBodyParameter("contact_fname", fnameV)
                .addBodyParameter("contact_lname", lnameV)
                .addBodyParameter("contact_ppicture", ppictureV)
                .addBodyParameter("contact_stars", newStarsV)
                .addBodyParameter("fk_contact_type_id", fkContactTypeIdV)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        if (response?.getString("message")?.contains("successfully")!!) {
                            DataPrep().fetchContactData()
                            starsState[position] = !starsState[position]
                            holder.tvStars.background.setTint(
                                if(starsState[position]) ContextCompat.getColor(context, R.color.goldenrod)
                                else ContextCompat.getColor(context, R.color.light_grey))
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("ON ERROR", anError?.errorDetail.toString())
                    }
                })
        }
        holder.cvContact.setOnClickListener {
            val i = Intent(context, ContactDetails::class.java)
            i.putExtra("contactId", arrayList.get(position).contactId)
            i.putExtra("itemPosition", holder.adapterPosition.toString())
            context.startActivity(i)
        }
        Log.d("RVAdapter, ", "onBindViewHolder : " + arrayList.size.toString())
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId : TextView = view.findViewById(R.id.contact_list_id)
        val tvPPicture : TextView = view.findViewById(R.id.contact_list_ppicture)
        val tvName : TextView = view.findViewById(R.id.contact_list_name)
        val tvStars : ImageButton = view.findViewById(R.id.contact_list_stars)
        val cvContact : MaterialCardView = view.findViewById(R.id.contact_list_cv)
    }
}
