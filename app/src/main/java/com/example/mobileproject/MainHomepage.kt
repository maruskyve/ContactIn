package com.example.mobileproject

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileproject.datas.DataPrep
import com.example.mobileproject.datas.contactData
import com.example.mobileproject.datas.contactTypeData
import com.example.mobileproject.datas.userData
import kotlinx.android.synthetic.main.fragment_main_homepage.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainHomepage.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHomepage : Fragment() {
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
        DataPrep().fetchUserData()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_homepage_commit_view_account.setOnClickListener {
            startActivity(Intent(activity, UserProfile::class.java))
        }

        main_homepage_commit_view_group_members.setOnClickListener {  // Action after view GROUP MEMBER button clicked
            startActivity(Intent(activity, GroupDetails::class.java))
        }

        main_homepage_commit_logout.setOnClickListener {  // Action after LOGOUT button pressed
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Logout Confirmation")
            builder.setMessage("Are you sure want to Logout?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                val loading = ProgressDialog(activity)
                loading.setMessage("System Logout . . .")
                loading.show()

                resetState()
                startActivity(Intent(this@MainHomepage.context, UserLogin::class.java))

                loading.dismiss()
            }

            builder.setNeutralButton("Cancel"){dialogInterface , which -> }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun resetState() {
        // Reset App state
        SESSION_LOGIN = false
        SESSION_LOGIN_DT = ""
        SESSION_USER_ID = ""
        SESSION_CONTACT_TYPE_DATA_FETCH = false
        SESSION_USER_DATA_FETCH = false
        SESSION_CONTACT_DATA_FETCH = false
        SINGLE_FILES = ""

        // Reset datas
        contactTypeData.clear()
        userData.clear()
        contactData.clear()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHomepage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainHomepage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
