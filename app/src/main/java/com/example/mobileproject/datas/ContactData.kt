package com.example.mobileproject.datas

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