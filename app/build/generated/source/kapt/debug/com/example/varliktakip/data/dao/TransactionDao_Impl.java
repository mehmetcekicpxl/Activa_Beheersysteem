package com.example.varliktakip.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.varliktakip.data.entity.AssetType;
import com.example.varliktakip.data.entity.Transaction;
import com.example.varliktakip.data.entity.TransactionWithAsset;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `transactions` (`id`,`assetTypeId`,`transactionType`,`amount`,`pricePerUnit`,`date`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAssetTypeId());
        if (entity.getTransactionType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTransactionType());
        }
        statement.bindDouble(4, entity.getAmount());
        statement.bindDouble(5, entity.getPricePerUnit());
        statement.bindLong(6, entity.getDate());
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`assetTypeId` = ?,`transactionType` = ?,`amount` = ?,`pricePerUnit` = ?,`date` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAssetTypeId());
        if (entity.getTransactionType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTransactionType());
        }
        statement.bindDouble(4, entity.getAmount());
        statement.bindDouble(5, entity.getPricePerUnit());
        statement.bindLong(6, entity.getDate());
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransaction.insert(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Transaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<TransactionWithAsset>> getTransactionsWithAssets() {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"asset_types",
        "transactions"}, true, new Callable<List<TransactionWithAsset>>() {
      @Override
      @Nullable
      public List<TransactionWithAsset> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfAssetTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "assetTypeId");
            final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
            final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
            final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
            final LongSparseArray<AssetType> _collectionAsset = new LongSparseArray<AssetType>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfAssetTypeId);
              _collectionAsset.put(_tmpKey, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipassetTypesAscomExampleVarliktakipDataEntityAssetType(_collectionAsset);
            final List<TransactionWithAsset> _result = new ArrayList<TransactionWithAsset>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final TransactionWithAsset _item;
              final Transaction _tmpTransaction;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final int _tmpAssetTypeId;
              _tmpAssetTypeId = _cursor.getInt(_cursorIndexOfAssetTypeId);
              final String _tmpTransactionType;
              if (_cursor.isNull(_cursorIndexOfTransactionType)) {
                _tmpTransactionType = null;
              } else {
                _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
              }
              final double _tmpAmount;
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
              final double _tmpPricePerUnit;
              _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
              final long _tmpDate;
              _tmpDate = _cursor.getLong(_cursorIndexOfDate);
              final String _tmpNotes;
              if (_cursor.isNull(_cursorIndexOfNotes)) {
                _tmpNotes = null;
              } else {
                _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
              }
              _tmpTransaction = new Transaction(_tmpId,_tmpAssetTypeId,_tmpTransactionType,_tmpAmount,_tmpPricePerUnit,_tmpDate,_tmpNotes);
              final AssetType _tmpAsset;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfAssetTypeId);
              _tmpAsset = _collectionAsset.get(_tmpKey_1);
              _item = new TransactionWithAsset(_tmpTransaction,_tmpAsset);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Transaction>> getTransactionsForAsset(final int assetTypeId) {
    final String _sql = "SELECT * FROM transactions WHERE assetTypeId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, assetTypeId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAssetTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "assetTypeId");
          final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpAssetTypeId;
            _tmpAssetTypeId = _cursor.getInt(_cursorIndexOfAssetTypeId);
            final String _tmpTransactionType;
            if (_cursor.isNull(_cursorIndexOfTransactionType)) {
              _tmpTransactionType = null;
            } else {
              _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new Transaction(_tmpId,_tmpAssetTypeId,_tmpTransactionType,_tmpAmount,_tmpPricePerUnit,_tmpDate,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipassetTypesAscomExampleVarliktakipDataEntityAssetType(
      @NonNull final LongSparseArray<AssetType> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipassetTypesAscomExampleVarliktakipDataEntityAssetType(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`category`,`symbol`,`isCustom` FROM `asset_types` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfCategory = 2;
      final int _cursorIndexOfSymbol = 3;
      final int _cursorIndexOfIsCustom = 4;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final AssetType _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpCategory;
          if (_cursor.isNull(_cursorIndexOfCategory)) {
            _tmpCategory = null;
          } else {
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
          }
          final String _tmpSymbol;
          if (_cursor.isNull(_cursorIndexOfSymbol)) {
            _tmpSymbol = null;
          } else {
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
          }
          final boolean _tmpIsCustom;
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfIsCustom);
          _tmpIsCustom = _tmp != 0;
          _item_1 = new AssetType(_tmpId,_tmpName,_tmpCategory,_tmpSymbol,_tmpIsCustom);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
