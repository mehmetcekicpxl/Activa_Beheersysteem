package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.Bill
import com.example.varliktakip.databinding.FragmentBillsBinding
import com.example.varliktakip.ui.adapter.BillAdapter
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class BillsFragment : Fragment() {

    private var _binding: FragmentBillsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val billAdapter = BillAdapter(
        onPaidClick = { bill ->
            val updatedBill = bill.copy(isPaid = !bill.isPaid)
            viewModel.updateBill(updatedBill)
        },
        onDeleteClick = { bill ->
            showDeleteDialog(bill)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupRecyclerView()
        observeData()

        binding.fabAddBill.setOnClickListener {
            AddBillDialogFragment().show(parentFragmentManager, "AddBill")
        }
        
        binding.btnEnableNotifications.setOnClickListener {
            requestNotificationPermission()
        }
    }
    
    override fun onResume() {
        super.onResume()
        updateNotificationUI()
    }

    private fun updateNotificationUI() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
             val permissionGranted = androidx.core.content.ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            
            if (!permissionGranted) {
                binding.btnEnableNotifications.visibility = View.VISIBLE
            } else {
                binding.btnEnableNotifications.visibility = View.GONE
            }
        } else {
             binding.btnEnableNotifications.visibility = View.GONE
        }
    }

    private fun requestNotificationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val intent = android.content.Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, requireContext().packageName)
            
             if (intent.resolveActivity(requireActivity().packageManager) != null) {
                 startActivity(intent)
             } else {
                  requestPermissions(
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
             }
        }
    }

    private fun setupRecyclerView() {
        binding.rvBills.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = billAdapter
        }
    }

    private fun observeData() {
        viewModel.allBills.observe(viewLifecycleOwner) { bills ->
            if (bills.isEmpty()) {
                binding.tvEmptyState.visibility = View.VISIBLE
            } else {
                binding.tvEmptyState.visibility = View.GONE
            }
            billAdapter.submitList(bills)
        }
    }
    
    private fun showDeleteDialog(bill: Bill) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Bill")
            .setMessage("Are you sure you want to delete ${bill.name}?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteBill(bill)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
