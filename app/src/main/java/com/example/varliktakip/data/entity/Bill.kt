package com.example.varliktakip.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bills")
data class Bill(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val currency: String = "TRY",
    val dueDate: Long, // Timestamp
    val isPaid: Boolean = false,
    val notes: String? = null
)
