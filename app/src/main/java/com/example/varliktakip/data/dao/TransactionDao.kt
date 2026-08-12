package com.example.varliktakip.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.varliktakip.data.entity.Transaction

@Dao
interface TransactionDao {
    @androidx.room.Transaction
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getTransactionsWithAssets(): LiveData<List<com.example.varliktakip.data.entity.TransactionWithAsset>>

    @Query("SELECT * FROM transactions WHERE assetTypeId = :assetTypeId ORDER BY date DESC")
    fun getTransactionsForAsset(assetTypeId: Int): LiveData<List<Transaction>>

    @Insert
    suspend fun insert(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)
}
