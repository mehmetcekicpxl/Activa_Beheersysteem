package com.example.varliktakip.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.Bill
import com.example.varliktakip.databinding.DialogAddDebtBinding // I can reuse debt layout or create new one. Better create new one or programmatically?
// Let's create a new layout for add bill dialog first.
// Wait, I haven't created dialog_add_bill.xml yet.
// I should use a generic approach or write the layout XML first.
// I'll stick to creating the XML first in the next step, but let's write the class assuming the layout exists.
import com.example.varliktakip.databinding.DialogAddBillBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddBillDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogAddBillBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var selectedDateTimestamp: Long = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]
        
        // Date Picker logic
        val calendar = Calendar.getInstance()
        updateDateText(calendar)
        
        binding.inputDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDateTimestamp = calendar.timeInMillis
                    updateDateText(calendar)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnSave.setOnClickListener {
            saveBill()
        }
    }

    private fun updateDateText(calendar: Calendar) {
        val dateFormat = java.text.SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        binding.inputDate.setText(dateFormat.format(calendar.time))
    }

    private fun saveBill() {
        val name = binding.inputName.text.toString()
        val amountStr = binding.inputAmount.text.toString()

        if (name.isBlank() || amountStr.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toDoubleOrNull()
        if (amount == null) {
             Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
             return
        }

        val bill = Bill(
            name = name,
            amount = amount,
            dueDate = selectedDateTimestamp
        )

        viewModel.insertBill(bill)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
