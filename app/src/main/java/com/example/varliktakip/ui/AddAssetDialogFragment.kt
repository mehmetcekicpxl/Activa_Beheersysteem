package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.databinding.DialogAddAssetBinding

class AddAssetDialogFragment : DialogFragment() {

    private var _binding: DialogAddAssetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddAssetBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupCategorySpinner()
        
        binding.btnSaveAsset.setOnClickListener {
            saveAsset()
        }
    }

    private fun setupCategorySpinner() {
        val categories = listOf("Currency", "Gold", "Crypto", "Stock", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.spinnerCategory.setAdapter(adapter)
    }

    private fun saveAsset() {
        val name = binding.etAssetName.text.toString()
        val category = binding.spinnerCategory.text.toString()
        
        if (name.isNotBlank() && category.isNotBlank()) {
            val asset = AssetType(
                name = name,
                category = category,
                isCustom = true
            )
            
            viewModel.insertAsset(asset)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
