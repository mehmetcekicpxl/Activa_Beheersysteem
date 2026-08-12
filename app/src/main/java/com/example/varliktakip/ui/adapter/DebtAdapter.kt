package com.example.varliktakip.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.varliktakip.data.entity.Debt
import com.example.varliktakip.databinding.ItemDebtBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DebtAdapter(
    private val onSettleClick: (Debt) -> Unit,
    private val onDeleteClick: (Debt) -> Unit
) : ListAdapter<Debt, DebtAdapter.DebtViewHolder>(DebtDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val binding = ItemDebtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DebtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DebtViewHolder(private val binding: ItemDebtBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        fun bind(debt: Debt) {
            binding.tvPersonName.text = debt.personName
            binding.tvAmount.text = String.format("%.2f", debt.amount)
            binding.tvDueDate.text = "Due: ${dateFormat.format(Date(debt.dueDate))}"
            
            if (debt.notes.isNullOrBlank()) {
                binding.tvNotes.visibility = View.GONE
            } else {
                binding.tvNotes.visibility = View.VISIBLE
                binding.tvNotes.text = debt.notes
            }
            
            if (debt.isSettled) {
                binding.tvType.text = "SETTLED"
                binding.tvType.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                binding.btnSettle.visibility = View.GONE
                binding.tvPersonName.alpha = 0.5f 
            } else {
                binding.tvPersonName.alpha = 1.0f
                binding.btnSettle.visibility = View.VISIBLE
                
                if (debt.type == "LENT") {
                    binding.tvType.text = "LENT"
                    binding.tvType.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#388E3C")) // Green
                } else {
                    binding.tvType.text = "BORROWED"
                    binding.tvType.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D32F2F")) // Red
                }
            }
            
            binding.btnSettle.setOnClickListener {
                onSettleClick(debt)
            }
            
            binding.btnDeleteDebt.setOnClickListener {
                onDeleteClick(debt)
            }
        }
    }

    class DebtDiffCallback : DiffUtil.ItemCallback<Debt>() {
        override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem == newItem
        }
    }
}
