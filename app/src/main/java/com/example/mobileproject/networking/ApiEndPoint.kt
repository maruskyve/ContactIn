package com.example.mobileproject.networking

class ApiEndPoint {
    // Not configured yet
    companion object {
        private val SERVER = "192.168.1.7/contactin"
        var CREATE = "$SERVER create.php"
        var READ = "$SERVER read-fakultas.php"
        var UPDATE = "$SERVER update-fakultas.php"
        var DELETE = "$SERVER delete-fakultas.php"
    }
}