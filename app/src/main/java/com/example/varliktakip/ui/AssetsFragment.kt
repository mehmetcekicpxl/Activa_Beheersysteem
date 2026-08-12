package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.databinding.FragmentAssetsBinding
import com.example.varliktakip.ui.adapter.AssetAdapter

class AssetsFragment : Fragment() {

    private var _binding: FragmentAssetsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val assetAdapter = AssetAdapter(
        onAssetClick = { assetId ->
            // Navigate to Detail Fragment
            parentFragmentManager.beginTransaction()
                .replace(com.example.varliktakip.R.id.fragment_container, AssetDetailFragment.newInstance(assetId))
                .addToBackStack(null)
                .commit()
        },
        onAssetLongClick = { asset ->
            showDeleteAssetDialog(asset)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupRecyclerView()
        observeData()
        
        binding.fabAddAsset.setOnClickListener {
             AddAssetDialogFragment().show(parentFragmentManager, "AddAsset")
        }
    }

    private fun setupRecyclerView() {
        binding.rvAssets.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = assetAdapter
        }
    }

    private fun observeData() {
        viewModel.allAssets.observe(viewLifecycleOwner) { assets ->
            assetAdapter.submitList(assets)
        }
    }

    private fun showDeleteAssetDialog(asset: AssetType) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Asset")
            .setMessage("Are you sure you want to delete ${asset.name}? WARNING: All transactions for this asset will also be deleted!")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteAsset(asset)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
