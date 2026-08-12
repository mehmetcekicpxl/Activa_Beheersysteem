package com.example.varliktakip.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.Debt
import com.example.varliktakip.databinding.DialogAddDebtBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddDebtDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddDebtBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddDebtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
        
        updateDateButton()
        
        binding.btnDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    updateDateButton()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etPersonName.text.toString()
            val amountStr = binding.etAmount.text.toString()
            val notes = binding.etNotes.text.toString()
            
            if (name.isBlank() || amountStr.isBlank()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val type = if (binding.rbLent.isChecked) "LENT" else "BORROWED"
            
            val debt = Debt(
                personName = name,
                type = type,
                amount = amountStr.toDouble(),
                dueDate = calendar.timeInMillis,
                notes = if (notes.isNotBlank()) notes else null
            )
            
            viewModel.insertDebt(debt)
            dismiss()
        }
    }
    
    private fun updateDateButton() {
        val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        binding.btnDate.text = "Due: ${format.format(calendar.time)}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
