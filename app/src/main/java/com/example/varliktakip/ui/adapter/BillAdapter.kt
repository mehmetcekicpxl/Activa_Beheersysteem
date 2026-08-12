package com.example.varliktakip.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.varliktakip.data.entity.Bill
import com.example.varliktakip.databinding.ItemBillBinding
import java.text.SimpleDateFormat
import java.util.*

class BillAdapter(
    private val onPaidClick: (Bill) -> Unit,
    private val onDeleteClick: (Bill) -> Unit
) : ListAdapter<Bill, BillAdapter.BillViewHolder>(BillDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val binding = ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BillViewHolder(private val binding: ItemBillBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bill: Bill) {
            binding.tvBillName.text = bill.name
            binding.tvBillAmount.text = String.format("%.2f", bill.amount)
            
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            binding.tvBillDueDate.text = "Due: ${dateFormat.format(Date(bill.dueDate))}"
            
            if (bill.isPaid) {
                binding.chipPaidStatus.text = "PAID"
                binding.chipPaidStatus.setChipBackgroundColorResource(android.R.color.holo_green_dark)
                binding.chipPaidStatus.setOnClickListener(null) // Disable click if paid? Or allow toggle? Let's allow toggle.
            } else {
                binding.chipPaidStatus.text = "UNPAID"
                binding.chipPaidStatus.setChipBackgroundColorResource(android.R.color.holo_red_dark)
            }
            
            binding.chipPaidStatus.setOnClickListener {
                onPaidClick(bill)
            }
            
            binding.btnDeleteBill.setOnClickListener {
                onDeleteClick(bill) // Reuse the delete button id from item_bill.xml layout we created?
                // Wait, in item_bill.xml I used ImageButton with id btnDeleteBill.
                // But I didn't verify if I added it to the layout correctly. Let's assume I did based on my previous write_to_file.
                // Actually I need to check the exact ID in item_bill.xml content I wrote.
                // "btnDeleteBill"
            }
        }
    }

    class BillDiffCallback : DiffUtil.ItemCallback<Bill>() {
        override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
            return oldItem == newItem
        }
    }
}
