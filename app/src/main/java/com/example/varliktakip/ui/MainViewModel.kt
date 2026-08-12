package com.example.varliktakip.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.data.entity.Transaction
import com.example.varliktakip.data.repository.AssetRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AssetRepository) : ViewModel() {

    val allAssets: LiveData<List<AssetType>> = repository.allAssets
    val allTransactions: LiveData<List<com.example.varliktakip.data.entity.TransactionWithAsset>> = repository.allTransactions

    fun insertAsset(assetType: AssetType) = viewModelScope.launch {
        repository.insertAsset(assetType)
    }

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }
    
    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.deleteTransaction(transaction)
    }

    fun deleteAsset(assetType: AssetType) = viewModelScope.launch {
        repository.deleteAsset(assetType)
    }
    
    fun getTransactionsForAsset(assetId: Int): LiveData<List<Transaction>> {
        return repository.getTransactionsForAsset(assetId)
    }
    
    // Debt Operations
    val allDebts: LiveData<List<com.example.varliktakip.data.entity.Debt>> = repository.allDebts
    
    fun insertDebt(debt: com.example.varliktakip.data.entity.Debt) = viewModelScope.launch {
        repository.insertDebt(debt)
    }
    
    fun updateDebt(debt: com.example.varliktakip.data.entity.Debt) = viewModelScope.launch {
        repository.updateDebt(debt)
    }
    
    fun deleteDebt(debt: com.example.varliktakip.data.entity.Debt) = viewModelScope.launch {
        repository.deleteDebt(debt)
    }

    // Bill Operations
    val allBills: LiveData<List<com.example.varliktakip.data.entity.Bill>> = repository.allBills
    
    fun insertBill(bill: com.example.varliktakip.data.entity.Bill) = viewModelScope.launch {
        repository.insertBill(bill)
    }
    
    fun updateBill(bill: com.example.varliktakip.data.entity.Bill) = viewModelScope.launch {
        repository.updateBill(bill)
    }
    
    fun deleteBill(bill: com.example.varliktakip.data.entity.Bill) = viewModelScope.launch {
        repository.deleteBill(bill)
    }

    // Simple calculation logic
    fun calculatePortfolioSummary(transactionsWithAssets: List<com.example.varliktakip.data.entity.TransactionWithAsset>): PortfolioSummary {
        // Initialize Totals
        var totalBuyAmount = 0.0
        var totalSellAmount = 0.0
        var totalRealizedProfit = 0.0
        
        // Group by Asset ID to calculate holdings and profit
        val transactionsByAsset = transactionsWithAssets.groupBy { it.asset.id }
        val holdings = mutableListOf<AssetHolding>()
        
        transactionsByAsset.forEach { (_, tList) ->
            var currentAmount = 0.0
            var costBasis = 0.0
            
            // We need the asset name. Since we grouped by ID, all items in tList have the same asset.
            val assetName = tList.first().asset.name
            
            // Sort by date to process properly (FIFO/Average Cost)
            val sorted = tList.sortedBy { it.transaction.date }
            
            sorted.forEach { t ->
                val transaction = t.transaction
                val tradeValue = transaction.amount * transaction.pricePerUnit
                
                if (transaction.transactionType == "BUY") {
                    // Update Total Buys
                    totalBuyAmount += tradeValue
                    
                    // Update Weighted Average Cost Basis
                    val totalValueBefore = currentAmount * costBasis
                    
                    currentAmount += transaction.amount
                    
                    if (currentAmount > 0) {
                        costBasis = (totalValueBefore + tradeValue) / currentAmount
                    }
                } else if (transaction.transactionType == "SELL") {
                    // Update Total Sells
                    totalSellAmount += tradeValue
                    
                    // Calculate Realized Profit
                    val profit = (transaction.pricePerUnit - costBasis) * transaction.amount
                    totalRealizedProfit += profit
                    
                    currentAmount -= transaction.amount
                }
                
                // Floating point correction and reset
                if (currentAmount <= 0.0001) {
                    currentAmount = 0.0
                    costBasis = 0.0
                }
            }
            
            // If any amount remains, add to holdings
            if (currentAmount > 0.0001) {
                holdings.add(AssetHolding(assetName, currentAmount))
            }
        }
        
        return PortfolioSummary(totalBuyAmount, totalSellAmount, totalRealizedProfit, holdings)
    }
}

data class PortfolioSummary(
    val totalInvestment: Double, // Sum of Buys
    val totalSales: Double,      // Sum of Sells
    val totalProfit: Double,     // Realized Profit
    val holdings: List<AssetHolding> = emptyList()
)

data class AssetHolding(
    val assetName: String,
    val amount: Double
)

class MainViewModelFactory(private val repository: AssetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
