package com.example.varliktakip.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asset_types")
data class AssetType(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String, // Gold, Currency, Crypto, Other
    val symbol: String? = null,
    val isCustom: Boolean = false
)
