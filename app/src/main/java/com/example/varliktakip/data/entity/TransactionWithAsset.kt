package com.example.varliktakip.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithAsset(
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "assetTypeId",
        entityColumn = "id"
    )
    val asset: AssetType
)
