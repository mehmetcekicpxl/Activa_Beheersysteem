package com.example.varliktakip.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.databinding.ItemAssetBinding

class AssetAdapter(
    private val onAssetClick: (Int) -> Unit,
    private val onAssetLongClick: (AssetType) -> Unit
) : ListAdapter<AssetType, AssetAdapter.AssetViewHolder>(AssetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AssetViewHolder(private val binding: ItemAssetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asset: AssetType) {
            binding.root.setOnClickListener {
                onAssetClick(asset.id)
            }
            binding.root.setOnLongClickListener {
                onAssetLongClick(asset)
                true
            }
            binding.btnDeleteAsset.setOnClickListener {
                onAssetLongClick(asset)
            }
            binding.tvAssetName.text = asset.name
            binding.tvAssetCategory.text = asset.category
            // Value would need to be calculated/passed, for now placeholder
            binding.tvAssetValue.text = asset.symbol ?: ""
        }
    }

    class AssetDiffCallback : DiffUtil.ItemCallback<AssetType>() {
        override fun areItemsTheSame(oldItem: AssetType, newItem: AssetType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AssetType, newItem: AssetType): Boolean {
            return oldItem == newItem
        }
    }
}
