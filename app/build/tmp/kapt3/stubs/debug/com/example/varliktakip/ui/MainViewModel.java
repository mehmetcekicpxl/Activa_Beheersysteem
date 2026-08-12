package com.example.varliktakip.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 J\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00070\u00062\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010&\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u000fJ\u000e\u0010\'\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\fJ\u000e\u0010)\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u000fR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/varliktakip/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/varliktakip/data/repository/AssetRepository;", "(Lcom/example/varliktakip/data/repository/AssetRepository;)V", "allAssets", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/varliktakip/data/entity/AssetType;", "getAllAssets", "()Landroidx/lifecycle/LiveData;", "allBills", "Lcom/example/varliktakip/data/entity/Bill;", "getAllBills", "allDebts", "Lcom/example/varliktakip/data/entity/Debt;", "getAllDebts", "allTransactions", "Lcom/example/varliktakip/data/entity/TransactionWithAsset;", "getAllTransactions", "calculatePortfolioSummary", "Lcom/example/varliktakip/ui/PortfolioSummary;", "transactionsWithAssets", "deleteAsset", "Lkotlinx/coroutines/Job;", "assetType", "deleteBill", "bill", "deleteDebt", "debt", "deleteTransaction", "transaction", "Lcom/example/varliktakip/data/entity/Transaction;", "getTransactionsForAsset", "assetId", "", "insertAsset", "insertBill", "insertDebt", "insertTransaction", "updateBill", "updateDebt", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.data.repository.AssetRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.AssetType>> allAssets = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.TransactionWithAsset>> allTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Debt>> allDebts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Bill>> allBills = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.repository.AssetRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.AssetType>> getAllAssets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.TransactionWithAsset>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertAsset(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.AssetType assetType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertTransaction(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteAsset(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.AssetType assetType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Transaction>> getTransactionsForAsset(int assetId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Debt>> getAllDebts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Bill>> getAllBills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.varliktakip.ui.PortfolioSummary calculatePortfolioSummary(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.varliktakip.data.entity.TransactionWithAsset> transactionsWithAssets) {
        return null;
    }
}