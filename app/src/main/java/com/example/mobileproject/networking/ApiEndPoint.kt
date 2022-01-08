package com.example.mobileproject.networking

class ApiEndPoint {
    // Not configured yet

    companion object {
        private var IP_ADDRESS = "192.168.1.8"
        private val SERVER = "http://$IP_ADDRESS/contactin/api/"

        var USER_CREATE_ACCOUNT = SERVER+"user_create_account.php"  // CreateAccount
        var USER_READ_ACCOUNT = SERVER+"user_read_account.php"  // UserLogin
        var USER_UPDATE_ACCOUNT = SERVER+"user_update_profile.php"  // UserProfile
        var USER_DELETE_ACCOUNT = SERVER+"user_delete_account.php"  // UserProfile

        var CONTACT_CREATE_CONTACT = SERVER+"contact_create_contact.php"  // CreateContact
        var CONTACT_READ_CONTACT = SERVER+"contact_read_contact.php"  // MainContactList.fetchContacts() (fragment)
        var CONTACT_UPDATE_CONTACT = SERVER+"contact_update_contact.php"  // ContactDetails.saveDetails()
        var CONTACT_DELETE_CONTACT = SERVER+"contact_delete_contact.php"  // ContactDetails.deleteContact()

        var CONTACT_TYPE_READ = SERVER+"contact_type_read.php"
    }
}


