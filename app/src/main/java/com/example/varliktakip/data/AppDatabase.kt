package com.example.varliktakip.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.varliktakip.data.dao.AssetDao
import com.example.varliktakip.data.dao.TransactionDao
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.data.entity.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [AssetType::class, Transaction::class, com.example.varliktakip.data.entity.Debt::class, com.example.varliktakip.data.entity.Bill::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao
    abstract fun transactionDao(): TransactionDao
    abstract fun debtDao(): com.example.varliktakip.data.dao.DebtDao
    abstract fun billDao(): com.example.varliktakip.data.dao.BillDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "varlik_takip_database"
                )
                .fallbackToDestructiveMigration() // Handle version change simply for now
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
