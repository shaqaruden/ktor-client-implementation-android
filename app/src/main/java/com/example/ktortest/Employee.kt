package com.example.ktortest

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    val imageUrl: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val contactNumber: String,
    val age: Int,
    val dob: String,
    val salary: Double,
    val address: String
)