package com.example.firebasecrud.ui.screens

import com.example.firebasecrud.model.Product

data class ProductState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
