package com.example.varliktakip.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class Debt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val personName: String,
    val type: String, // "LENT" (Alacak), "BORROWED" (Borç)
    val amount: Double,
    val currency: String = "TRY", // Default currency
    val dueDate: Long,
    val isSettled: Boolean = false,
    val notes: String? = null
)
