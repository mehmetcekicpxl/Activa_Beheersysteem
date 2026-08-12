package com.example.varliktakip.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.varliktakip.data.entity.Transaction
import com.example.varliktakip.databinding.ItemTransactionBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import com.example.varliktakip.data.entity.TransactionWithAsset

class TransactionAdapter(private val onTransactionLongClick: (TransactionWithAsset) -> Unit = {}) : ListAdapter<TransactionWithAsset, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        fun bind(item: TransactionWithAsset) {
            val transaction = item.transaction
            val asset = item.asset
            
            binding.root.setOnLongClickListener {
                onTransactionLongClick(item)
                true
            }
            
            binding.btnDelete.setOnClickListener {
                onTransactionLongClick(item)
            }
            
            binding.tvCategory.text = asset.category
            binding.tvAssetName.text = asset.name
            
            binding.tvTransactionType.text = transaction.transactionType
            if (transaction.transactionType == "BUY") {
                binding.tvTransactionType.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
            } else {
                binding.tvTransactionType.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
            }
            
            val totalValue = transaction.amount * transaction.pricePerUnit
            
            binding.tvAmountValue.text = String.format("%.2f", transaction.amount)
            binding.tvPriceValue.text = String.format("%.2f", transaction.pricePerUnit)
            binding.tvTotalValue.text = String.format("%.2f", totalValue)
            
            binding.tvDate.text = dateFormat.format(Date(transaction.date))
        }
    }

    class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionWithAsset>() {
        override fun areItemsTheSame(oldItem: TransactionWithAsset, newItem: TransactionWithAsset): Boolean {
            return oldItem.transaction.id == newItem.transaction.id
        }

        override fun areContentsTheSame(oldItem: TransactionWithAsset, newItem: TransactionWithAsset): Boolean {
            return oldItem == newItem
        }
    }
}
