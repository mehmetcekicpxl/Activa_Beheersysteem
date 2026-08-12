package com.example.varliktakip.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varliktakip.VarlikTakipApplication
import com.example.varliktakip.databinding.FragmentDashboardBinding
import com.example.varliktakip.ui.adapter.TransactionAdapter
import com.example.varliktakip.data.entity.Transaction

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val transactionAdapter = TransactionAdapter { transactionWithAsset ->
        showDeleteConfirmationDialog(transactionWithAsset)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val factory = MainViewModelFactory((requireActivity().application as VarlikTakipApplication).repository)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        setupRecyclerView()
        setupFilters()
        observeData()
        
        binding.fabAdd.setOnClickListener {
            AddTransactionDialogFragment().show(parentFragmentManager, "AddTransaction")
        }
        
        setupPieChart()
    }

    // Filter implementation
    private var allTransactionsList: List<com.example.varliktakip.data.entity.TransactionWithAsset> = emptyList()
    private var assetList: List<com.example.varliktakip.data.entity.AssetType> = emptyList()
    
    private fun setupFilters() {
        // Chip Group Listener
        binding.cgFilterType.setOnCheckedChangeListener { _, _ ->
            applyFilters()
        }
        
        // Spinner Listener
        binding.spnFilterAsset.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
             override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                 applyFilters()
             }
             override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }
    }

    private fun applyFilters() {
        var filtered = allTransactionsList
        
        // 1. Filter by Asset
        val selectedAssetPosition = binding.spnFilterAsset.selectedItemPosition
        // Position 0 is "All Assets"
        if (selectedAssetPosition > 0 && assetList.isNotEmpty()) {
            // assetList index is position - 1 because we added "All Assets" at top
            val selectedAssetId = assetList[selectedAssetPosition - 1].id
            filtered = filtered.filter { it.transaction.assetTypeId == selectedAssetId }
        }
        
        // 2. Filter by Type
        when (binding.cgFilterType.checkedChipId) {
            com.example.varliktakip.R.id.chipBuy -> {
                filtered = filtered.filter { it.transaction.transactionType == "BUY" }
            }
            com.example.varliktakip.R.id.chipSell -> {
                filtered = filtered.filter { it.transaction.transactionType == "SELL" }
            }
            // chipAll -> do nothing
        }
        
        transactionAdapter.submitList(filtered)
    }

    private fun setupRecyclerView() {
        binding.rvRecentTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
    }

    private fun observeData() {
        // Observe Assets to populate Spinner
        viewModel.allAssets.observe(viewLifecycleOwner) { assets ->
            assetList = assets
            val assetNames = mutableListOf("All Assets")
            assetNames.addAll(assets.map { it.name })
            
            val adapter = android.widget.ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, assetNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnFilterAsset.adapter = adapter
        }

        viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            allTransactionsList = transactions
            
            // Initial show
            applyFilters()
            
            // Calculate summary on ALL transactions (Portfolio logic shouldn't change based on filter usually,
            // or maybe user wants to see stats for filter? User said "see only gram altin".
            // Usually Portfolio Value is global. Let's keep summary global for now.)
            val summary = viewModel.calculatePortfolioSummary(transactions)
            
            binding.tvTotalInvestment.text = String.format("%.2f", summary.totalInvestment)
            binding.tvTotalSales.text = String.format("%.2f", summary.totalSales)
            binding.tvTotalProfit.text = String.format("%s%.2f", if(summary.totalProfit >= 0) "+" else "", summary.totalProfit)
            
            if (summary.totalProfit >= 0) {
                binding.tvTotalProfit.setTextColor(resources.getColor(com.example.varliktakip.R.color.green_profit, null))
            } else {
                binding.tvTotalProfit.setTextColor(resources.getColor(com.example.varliktakip.R.color.red_loss, null))
            }
            
            updateChart(summary)
            
            // Update Current Assets Section
            binding.llCurrentAssets.removeAllViews()
            
            if (summary.holdings.isEmpty()) {
                val emptyView = android.widget.TextView(context).apply {
                    text = "No assets currently held."
                    setTextColor(android.graphics.Color.GRAY)
                    setPadding(0, 8, 0, 8)
                }
                binding.llCurrentAssets.addView(emptyView)
            } else {
                summary.holdings.forEach { holding ->
                    val assetView = android.widget.LinearLayout(context).apply {
                        orientation = android.widget.LinearLayout.HORIZONTAL
                        layoutParams = android.widget.LinearLayout.LayoutParams(
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0, 8, 0, 8)
                        }
                    }
                    
                    val nameTv = android.widget.TextView(context).apply {
                        text = holding.assetName
                        textSize = 16f
                        setTextColor(resources.getColor(android.R.color.black, null)) // Or theme color
                        layoutParams = android.widget.LinearLayout.LayoutParams(
                            0,
                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                            1f
                        )
                    }
                    
                    val amountTv = android.widget.TextView(context).apply {
                        text = String.format("%.2f", holding.amount)
                        textSize = 16f
                        setTypeface(null, android.graphics.Typeface.BOLD)
                        setTextColor(resources.getColor(android.R.color.black, null))
                    }
                    
                    assetView.addView(nameTv)
                    assetView.addView(amountTv)
                    binding.llCurrentAssets.addView(assetView)
                    
                    // Divider
                    val divider = android.view.View(context).apply {
                         layoutParams = android.view.ViewGroup.LayoutParams(
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                            1
                        )
                        setBackgroundColor(android.graphics.Color.LTGRAY)
                    }
                    binding.llCurrentAssets.addView(divider)
                }
            }
        }
    }
    
    private fun setupPieChart() {
        binding.chartPortfolio.apply {
            description.isEnabled = false
            legend.isEnabled = true
            legend.verticalAlignment = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM
            legend.horizontalAlignment = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            legend.orientation = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL
            legend.setDrawInside(false)
            setEntryLabelColor(android.graphics.Color.WHITE)
            setEntryLabelTextSize(12f)
            isDrawHoleEnabled = true
            setHoleColor(android.graphics.Color.TRANSPARENT)
            setTransparentCircleColor(android.graphics.Color.WHITE)
            setTransparentCircleAlpha(110)
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            centerText = "Assets"
            setCenterTextSize(16f)
            // PieChart doesn't have axisRight, so no need to disable it
        }
    }

    private fun updateChart(summary: com.example.varliktakip.ui.PortfolioSummary) {
        val entries = ArrayList<com.github.mikephil.charting.data.PieEntry>()
        val colors = ArrayList<Int>()
        
        // Define some nice colors
        val joyfulColors = com.github.mikephil.charting.utils.ColorTemplate.JOYFUL_COLORS
        val colorfulColors = com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
        val libertyColors = com.github.mikephil.charting.utils.ColorTemplate.LIBERTY_COLORS
        
        var colorIndex = 0
        
        summary.holdings.forEach { holding ->
            // Use amount for the pie chart value
            entries.add(com.github.mikephil.charting.data.PieEntry(holding.amount.toFloat(), holding.assetName))
            
            // Pick a color
            if (colorIndex < joyfulColors.size) {
                colors.add(joyfulColors[colorIndex])
            } else if (colorIndex < joyfulColors.size + colorfulColors.size) {
                colors.add(colorfulColors[colorIndex - joyfulColors.size])
            } else {
                colors.add(libertyColors[colorIndex % libertyColors.size])
            }
            colorIndex++
        }

        val dataSet = com.github.mikephil.charting.data.PieDataSet(entries, "Asset Distribution")
        dataSet.colors = colors
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.valueTextColor = android.graphics.Color.WHITE
        dataSet.valueTextSize = 14f
        
        val data = com.github.mikephil.charting.data.PieData(dataSet)
        binding.chartPortfolio.data = data
        binding.chartPortfolio.invalidate() // refresh
        
        // Hide if empty
        if (summary.holdings.isEmpty()) {
            binding.chartPortfolio.visibility = View.GONE
        } else {
            binding.chartPortfolio.visibility = View.VISIBLE
            binding.chartPortfolio.animateY(1000, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)
        }
    }

    private fun showDeleteConfirmationDialog(transactionWithAsset: com.example.varliktakip.data.entity.TransactionWithAsset) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Transaction")
            .setMessage("Are you sure you want to delete this transaction for ${transactionWithAsset.asset.name}?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteTransaction(transactionWithAsset.transaction)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
