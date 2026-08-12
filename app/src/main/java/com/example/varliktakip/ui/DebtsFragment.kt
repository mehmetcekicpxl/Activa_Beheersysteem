package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.data.entity.Debt
import com.example.varliktakip.databinding.FragmentDebtsBinding
import com.example.varliktakip.ui.adapter.DebtAdapter

class DebtsFragment : Fragment() {

    private var _binding: FragmentDebtsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val debtAdapter = DebtAdapter(
        onSettleClick = { debt ->
            // Toggle settled state
            val updatedDebt = debt.copy(isSettled = !debt.isSettled)
            viewModel.updateDebt(updatedDebt)
        },
        onDeleteClick = { debt ->
            showDeleteDialog(debt)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebtsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupRecyclerView()
        observeData()

        binding.fabAddDebt.setOnClickListener {
            AddDebtDialogFragment().show(parentFragmentManager, "AddDebt")
        }
    }

    private fun setupRecyclerView() {
        binding.rvDebts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = debtAdapter
        }
    }

    private fun observeData() {
        viewModel.allDebts.observe(viewLifecycleOwner) { debts ->
            if (debts.isEmpty()) {
                binding.tvEmptyState.visibility = View.VISIBLE
            } else {
                binding.tvEmptyState.visibility = View.GONE
            }
            debtAdapter.submitList(debts)
        }
    }
    
    private fun showDeleteDialog(debt: Debt) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Record")
            .setMessage("Are you sure you want to delete this record for ${debt.personName}?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteDebt(debt)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
