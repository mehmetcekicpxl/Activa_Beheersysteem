package com.example.varliktakip.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\r0\f2\u0006\u0010)\u001a\u00020*J\u0016\u0010+\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010,\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010-\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010.\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u0016\u0010/\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u00100\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010#R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/example/varliktakip/data/repository/AssetRepository;", "", "assetDao", "Lcom/example/varliktakip/data/dao/AssetDao;", "transactionDao", "Lcom/example/varliktakip/data/dao/TransactionDao;", "debtDao", "Lcom/example/varliktakip/data/dao/DebtDao;", "billDao", "Lcom/example/varliktakip/data/dao/BillDao;", "(Lcom/example/varliktakip/data/dao/AssetDao;Lcom/example/varliktakip/data/dao/TransactionDao;Lcom/example/varliktakip/data/dao/DebtDao;Lcom/example/varliktakip/data/dao/BillDao;)V", "allAssets", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/varliktakip/data/entity/AssetType;", "getAllAssets", "()Landroidx/lifecycle/LiveData;", "allBills", "Lcom/example/varliktakip/data/entity/Bill;", "getAllBills", "allDebts", "Lcom/example/varliktakip/data/entity/Debt;", "getAllDebts", "allTransactions", "Lcom/example/varliktakip/data/entity/TransactionWithAsset;", "getAllTransactions", "deleteAsset", "", "assetType", "(Lcom/example/varliktakip/data/entity/AssetType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBill", "bill", "(Lcom/example/varliktakip/data/entity/Bill;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDebt", "debt", "(Lcom/example/varliktakip/data/entity/Debt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransaction", "transaction", "Lcom/example/varliktakip/data/entity/Transaction;", "(Lcom/example/varliktakip/data/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionsForAsset", "assetId", "", "insertAsset", "insertBill", "insertDebt", "insertTransaction", "updateBill", "updateDebt", "app_debug"})
public final class AssetRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.data.dao.AssetDao assetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.data.dao.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.data.dao.DebtDao debtDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.varliktakip.data.dao.BillDao billDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.AssetType>> allAssets = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.TransactionWithAsset>> allTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Debt>> allDebts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Bill>> allBills = null;
    
    public AssetRepository(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.dao.AssetDao assetDao, @org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.dao.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.dao.DebtDao debtDao, @org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.dao.BillDao billDao) {
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
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Debt>> getAllDebts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Bill>> getAllBills() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertAsset(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.AssetType assetType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAsset(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.AssetType assetType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.varliktakip.data.entity.Transaction>> getTransactionsForAsset(int assetId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteDebt(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Debt debt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBill(@org.jetbrains.annotations.NotNull()
    com.example.varliktakip.data.entity.Bill bill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}