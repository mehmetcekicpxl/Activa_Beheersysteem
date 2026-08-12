package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.data.entity.TransactionWithAsset
import com.example.varliktakip.databinding.FragmentAssetDetailBinding
import com.example.varliktakip.ui.adapter.TransactionAdapter

class AssetDetailFragment : Fragment() {

    private var _binding: FragmentAssetDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val transactionAdapter = TransactionAdapter { transactionWithAsset ->
        showDeleteConfirmationDialog(transactionWithAsset)
    }
    
    // Arguments
    private var assetId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            assetId = it.getInt("assetId", -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupRecyclerView()
        
        if (assetId != -1) {
            observeData()
        }
    }

    private fun setupRecyclerView() {
        binding.rvAssetTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
    }

    private fun observeData() {
        // Observe all transactions and filter/calculate manually for now
        // A better approach would be to have specific DAO methods, but this works for smaller data sets
        viewModel.allTransactions.observe(viewLifecycleOwner) { allTransactionsWithAssets ->
            val assetTransactions = allTransactionsWithAssets.filter { it.transaction.assetTypeId == assetId }
            
            // Update List
            transactionAdapter.submitList(assetTransactions)
            
            // Update Summary Card
            if (assetTransactions.isNotEmpty()) {
                val asset = assetTransactions.first().asset
                binding.tvDetailAssetName.text = asset.name
                binding.tvDetailAssetCategory.text = asset.category
                
                calculateAndDisplaySummary(assetTransactions)
            } else {
                 // Try to find asset info from allAssets if no transactions yet
                 viewModel.allAssets.observe(viewLifecycleOwner) { assets ->
                     val asset = assets.find { it.id == assetId }
                     asset?.let {
                        binding.tvDetailAssetName.text = it.name
                        binding.tvDetailAssetCategory.text = it.category
                     }
                 }
                 
                 binding.tvDetailAmount.text = "0"
                 binding.tvDetailAvgCost.text = "0.0"
                 binding.tvDetailProfit.text = "0.0"
            }
        }
    }
    
    private fun calculateAndDisplaySummary(transactions: List<TransactionWithAsset>) {
        // Re-using logic similar to ViewModel's calculation, but doing it locally for display
        // ideally logic should be in ViewModel
        
        var currentAmount = 0.0
        var costBasis = 0.0
        var totalRealizedProfit = 0.0
        
        val sorted = transactions.map { it.transaction }.sortedBy { it.date }
        
        for (t in sorted) {
            if (t.transactionType == "BUY") {
                val totalValueBefore = currentAmount * costBasis
                val tradeValue = t.amount * t.pricePerUnit
                
                currentAmount += t.amount
                
                if (currentAmount > 0) {
                    costBasis = (totalValueBefore + tradeValue) / currentAmount
                }
            } else if (t.transactionType == "SELL") {
                val profit = (t.pricePerUnit - costBasis) * t.amount
                totalRealizedProfit += profit
                currentAmount -= t.amount
            }
            
            // Floating point correction and reset
            if (currentAmount <= 0.0001) {
                currentAmount = 0.0
                costBasis = 0.0
            }
        }
        
        binding.tvDetailAmount.text = String.format("%.2f", currentAmount)
        binding.tvDetailAvgCost.text = String.format("%.2f", costBasis)
        
        val profitText = String.format("%.2f", totalRealizedProfit)
        binding.tvDetailProfit.text = if (totalRealizedProfit >= 0) "+$profitText" else profitText
        
        if (totalRealizedProfit >= 0) {
            binding.tvDetailProfit.setTextColor(resources.getColor(android.R.color.holo_green_dark, null))
        } else {
            binding.tvDetailProfit.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
        }
    }

    private fun showDeleteConfirmationDialog(transactionWithAsset: TransactionWithAsset) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Transaction")
            .setMessage("Are you sure you want to delete this transaction?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteTransaction(transactionWithAsset.transaction)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(assetId: Int) =
            AssetDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("assetId", assetId)
                }
            }
    }
}
