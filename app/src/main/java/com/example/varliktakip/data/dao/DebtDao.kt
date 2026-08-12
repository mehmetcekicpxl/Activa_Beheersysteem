package com.example.varliktakip.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.varliktakip.data.entity.Debt

@Dao
interface DebtDao {
    @Query("SELECT * FROM debts ORDER BY isSettled ASC, dueDate ASC")
    fun getAllDebts(): LiveData<List<Debt>>

    @Insert
    suspend fun insert(debt: Debt)

    @Update
    suspend fun update(debt: Debt)

    @Delete
    suspend fun delete(debt: Debt)
}
