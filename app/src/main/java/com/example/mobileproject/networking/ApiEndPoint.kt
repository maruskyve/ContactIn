package com.example.mobileproject.networking

class ApiEndPoint {
    // Not configured yet

    companion object {
        private var IP_ADDRESS = "192.168.1.8"
        private val SERVER = "http://$IP_ADDRESS/contactin/api/"

        fun setAddress(address: String) {

        }

        var USER_CREATE_ACCOUNT = SERVER+"user_create_account.php"
        var USER_READ_LOGIN = SERVER+"user_read_login.php"
        var USER_READ_ACCOUNT = SERVER+"user_read_account.php"
        var USER_UPDATE_ACCOUNT = "$SERVER user_update_account.php"
        var USER_DELETE_ACCOUNT = "$SERVER user_delete_account.php"

        var CONTACT_CREATE = "$SERVER contact_create.php"
        var CONTACT_READ = "$SERVER contact_read.php"
        var CONTACT_UPDATE = "$SERVER contact_update.php"
        var CONTACT_DELETE = "$SERVER contact_delete.php"

        var CONTACT_TYPE_READ = "$SERVER contact_type_read.php"
    }
}


