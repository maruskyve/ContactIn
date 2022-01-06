package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // Inflate the layout for this fragment
        // !!!RootView is Important!!!
        // Initialize this code
        // DONT USE KOTLINX ID EXTENSIONS
        val rootView = inflater.inflate(R.layout.fragment_main_homepage, container, false)
        val viewMyAccount = rootView.findViewById<View>(R.id.main_homepage_commit_view_account)
        val viewGroupMembers = rootView.findViewById<View>(R.id.main_homepage_commit_view_group_members)

        viewMyAccount.setOnClickListener {  // Action after view MY ACCOUNT button pressed
            // !configured yet
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        viewGroupMembers.setOnClickListener {  // Action after view GROUP MEMBER button clicked
            val intent = Intent(activity, GroupDetails::class.java)
            startActivity(intent)
        }
        return rootView
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