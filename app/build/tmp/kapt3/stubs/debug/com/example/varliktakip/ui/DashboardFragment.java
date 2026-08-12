package com.example.varliktakip.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\u001a\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0007H\u0002J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/varliktakip/ui/DashboardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/varliktakip/databinding/FragmentDashboardBinding;", "allTransactionsList", "", "Lcom/example/varliktakip/data/entity/TransactionWithAsset;", "assetList", "Lcom/example/varliktakip/data/entity/AssetType;", "binding", "getBinding", "()Lcom/example/varliktakip/databinding/FragmentDashboardBinding;", "transactionAdapter", "Lcom/example/varliktakip/ui/adapter/TransactionAdapter;", "viewModel", "Lcom/example/varliktakip/ui/MainViewModel;", "applyFilters", "", "observeData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupFilters", "setupPieChart", "setupRecyclerView", "showDeleteConfirmationDialog", "transactionWithAsset", "updateChart", "summary", "Lcom/example/varliktakip/ui/PortfolioSummary;", "app_debug"})
public final class DashboardFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.varliktakip.databinding.FragmentDashboardBinding _binding;
    private com.example.varliktakip.ui.MainViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.ui.adapter.TransactionAdapter transactionAdapter = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.varliktakip.data.entity.TransactionWithAsset> allTransactionsList;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.varliktakip.data.entity.AssetType> assetList;
    
    public DashboardFragment() {
        super();
    }
    
    private final com.example.varliktakip.databinding.FragmentDashboardBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupFilters() {
    }
    
    private final void applyFilters() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeData() {
    }
    
    private final void setupPieChart() {
    }
    
    private final void updateChart(com.example.varliktakip.ui.PortfolioSummary summary) {
    }
    
    private final void showDeleteConfirmationDialog(com.example.varliktakip.data.entity.TransactionWithAsset transactionWithAsset) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}