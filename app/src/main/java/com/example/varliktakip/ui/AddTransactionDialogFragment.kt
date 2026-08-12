package com.example.varliktakip.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.R
import com.example.varliktakip.data.entity.AssetType
import com.example.varliktakip.data.entity.Transaction
import com.example.varliktakip.databinding.DialogAddTransactionBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class AddTransactionDialogFragment : DialogFragment() {

    private var _binding: DialogAddTransactionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var selectedAsset: AssetType? = null
    private val assetsList = mutableListOf<AssetType>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private var selectedDateTimestamp: Long = System.currentTimeMillis()
    private val dateFormat = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.getDefault())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupAssetSpinner()
        setupDatePicker()
        
        binding.toggleType.check(R.id.btnBuy) // Default selection

        binding.btnSave.setOnClickListener {
            saveTransaction()
        }
    }

    private fun setupDatePicker() {
        // Set current date initially
        binding.etDate.setText(dateFormat.format(java.util.Date(selectedDateTimestamp)))

        binding.etDate.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            calendar.timeInMillis = selectedDateTimestamp
            
            val datePickerDialog = android.app.DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDateTimestamp = calendar.timeInMillis
                    binding.etDate.setText(dateFormat.format(calendar.time))
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun setupAssetSpinner() {
        viewModel.allAssets.observe(viewLifecycleOwner) { assets ->
            assetsList.clear()
            assetsList.addAll(assets)
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, assets.map { it.name })
            binding.spinnerAssetType.setAdapter(adapter)
            
            binding.spinnerAssetType.setOnItemClickListener { _, _, position, _ ->
                selectedAsset = assetsList[position]
            }
        }
    }

    private fun saveTransaction() {
        val amountStr = binding.etAmount.text.toString()
        val priceStr = binding.etPrice.text.toString()
        val asset = selectedAsset
        
        if (amountStr.isNotBlank() && priceStr.isNotBlank() && asset != null) {
            val amount = amountStr.toDoubleOrNull() ?: 0.0
            val price = priceStr.toDoubleOrNull() ?: 0.0
            
            val type = if (binding.toggleType.checkedButtonId == R.id.btnBuy) "BUY" else "SELL"
            
            val pricePerUnit = if (amount != 0.0) price / amount else 0.0

            val transaction = Transaction(
                assetTypeId = asset.id,
                transactionType = type,
                amount = amount,
                pricePerUnit = pricePerUnit,
                date = selectedDateTimestamp
            )
            
            viewModel.insertTransaction(transaction)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
