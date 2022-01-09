package com.example.universtitaskotlin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileproject.ContactDetails
import com.example.mobileproject.R
import com.example.mobileproject.datas.ContactData
import com.google.android.material.card.MaterialCardView

class RVAdapterContact (private val context: Context, private val arrayList: ArrayList<ContactData>)
    : RecyclerView.Adapter<RVAdapterContact.Holder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.main_contact_list, parent,false))
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvId.text = arrayList.get(position).contactId
        holder.tvPPicture.text = arrayList.get(position).contactPPicture
        holder.tvName.text = arrayList.get(position).contactFName
        holder.tvStars.background.setTint(
            if(arrayList.get(position).contactStars == "1") ContextCompat.getColor(context, R.color.goldenrod)
            else ContextCompat.getColor(context, R.color.light_grey))
//        holder.tvStars.setOnClickListener {
//        }
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
