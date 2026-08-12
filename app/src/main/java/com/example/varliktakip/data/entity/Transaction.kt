package com.example.varliktakip.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = AssetType::class,
            parentColumns = ["id"],
            childColumns = ["assetTypeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [androidx.room.Index(value = ["assetTypeId"])]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val assetTypeId: Int,
    val transactionType: String, // BUY, SELL
    val amount: Double,
    val pricePerUnit: Double,
    val date: Long,
    val notes: String? = null
)
