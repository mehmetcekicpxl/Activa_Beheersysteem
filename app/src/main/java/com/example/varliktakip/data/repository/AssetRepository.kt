package com.example.varliktakip.data.repository

import androidx.lifecycle.LiveData
import com.example.varliktakip.data.dao.AssetDao
import com.example.varliktakip.data.dao.TransactionDao
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.data.entity.Transaction
import androidx.lifecycle.asLiveData

class AssetRepository(
    private val assetDao: AssetDao, 
    private val transactionDao: TransactionDao,
    private val debtDao: com.example.varliktakip.data.dao.DebtDao,
    private val billDao: com.example.varliktakip.data.dao.BillDao
) {

    val allAssets: LiveData<List<AssetType>> = assetDao.getAllAssets()
    val allTransactions: LiveData<List<com.example.varliktakip.data.entity.TransactionWithAsset>> = transactionDao.getTransactionsWithAssets()
    val allDebts: LiveData<List<com.example.varliktakip.data.entity.Debt>> = debtDao.getAllDebts()
    val allBills: LiveData<List<com.example.varliktakip.data.entity.Bill>> = billDao.getAllBills().asLiveData()

    suspend fun insertAsset(assetType: AssetType) {
        assetDao.insert(assetType)
    }

    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insert(transaction)
    }
    
    suspend fun deleteAsset(assetType: AssetType) {
        assetDao.delete(assetType)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    fun getTransactionsForAsset(assetId: Int): LiveData<List<Transaction>> {
        return transactionDao.getTransactionsForAsset(assetId)
    }
    
    // Debt Methods
    suspend fun insertDebt(debt: com.example.varliktakip.data.entity.Debt) {
        debtDao.insert(debt)
    }
    
    suspend fun updateDebt(debt: com.example.varliktakip.data.entity.Debt) {
        debtDao.update(debt)
    }
    
    suspend fun deleteDebt(debt: com.example.varliktakip.data.entity.Debt) {
        debtDao.delete(debt)
    }

    // Bill Methods
    suspend fun insertBill(bill: com.example.varliktakip.data.entity.Bill) {
        billDao.insert(bill)
    }

    suspend fun updateBill(bill: com.example.varliktakip.data.entity.Bill) {
        billDao.update(bill)
    }

    suspend fun deleteBill(bill: com.example.varliktakip.data.entity.Bill) {
        billDao.delete(bill)
    }
}
