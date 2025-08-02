package com.example.firebasecrud.repository

import com.example.firebasecrud.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    // Mereferensikan koleksi 'products' di Firestore
    private val productsCollection = firestore.collection("products")

    // CREATE: Menambahkan produk baru
    suspend fun addProduct(product: Product) {
        productsCollection.add(product).await()
    }

    // READ: Mengambil semua produk secara real-time
    fun getProducts() = productsCollection
        .orderBy("timestamp", Query.Direction.DESCENDING)
        .snapshots() // Memberikan stream data real-time
        .map { snapshot ->
            snapshot.toObjects(Product::class.java).mapIndexed { index, product ->
                product.copy(id = snapshot.documents[index].id)
            }
        }

    // UPDATE: Memperbarui produk yang ada
    suspend fun updateProduct(product: Product) {
        productsCollection.document(product.id).set(product).await()
    }

    // DELETE: Menghapus produk
    suspend fun deleteProduct(productId: String) {
        productsCollection.document(productId).delete().await()
    }
}