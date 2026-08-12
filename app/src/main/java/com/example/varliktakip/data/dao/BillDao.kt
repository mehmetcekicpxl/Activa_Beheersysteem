package com.example.varliktakip.data.dao

import androidx.room.*
import com.example.varliktakip.data.entity.Bill
import kotlinx.coroutines.flow.Flow

@Dao
interface BillDao {
    @Query("SELECT * FROM bills ORDER BY dueDate ASC")
    fun getAllBills(): Flow<List<Bill>>

    @Query("SELECT * FROM bills WHERE isPaid = 0 AND dueDate BETWEEN :startTime AND :endTime")
    suspend fun getUnpaidBillsDueInRange(startTime: Long, endTime: Long): List<Bill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bill: Bill)

    @Update
    suspend fun update(bill: Bill)

    @Delete
    suspend fun delete(bill: Bill)
}
