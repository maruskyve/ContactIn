package com.example.mobileproject

import android.app.Activity
import androidx.fragment.app.Fragment

private var loginStateV : Boolean = false
private var savedActivity : Activity = MainActivity()
private var savedFragment : Fragment = MainContactList()

class LoginState {
    fun getState() : Boolean {  // getter
        return loginStateV
    }

    fun setLogin(){  // setter
        loginStateV = !loginStateV
    }
}

// Unfixed class
class SavedActivity {
    fun getActivity() : Activity {
        return savedActivity
    }

    fun setActivity(activity: Activity) {
        savedActivity = activity
    }
}

class SavedFragment {
    fun getFragment() : Fragment {
        return savedFragment
    }
    fun setFragment(fragment: Fragment) {
        savedFragment = fragment
    }
}
