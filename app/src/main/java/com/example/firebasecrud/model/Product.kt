package com.example.firebasecrud.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class Product(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    @ServerTimestamp
    val timestamp: Timestamp? = null
)
