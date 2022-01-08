package com.example.mobileproject.datas

data class UserData(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userPhoneNumber: String,
    val userPassword: String,
    val userFName: String,
    val userLName: String,
    val userGender: String,
    val userPPicture: String
)

data class ContactData(
    val contactId : String,
    val contactPPicture : String,
    val contactFName : String,
    val contactLName : String,
    val contactEmail : String,
    val contactPhoneNumber : String,
    val contactStars : String,
    val contactTypeId : String, // From FK
    val contactUserId : String  // From FK
)

data class ContactTypeData(
    val contactTypeId : String,
    val contactTypeName : String,
)