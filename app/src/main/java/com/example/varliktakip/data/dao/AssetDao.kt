package com.example.varliktakip.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.varliktakip.data.entity.AssetType

@Dao
interface AssetDao {
    @Query("SELECT * FROM asset_types ORDER BY name ASC")
    fun getAllAssets(): LiveData<List<AssetType>>

    @Query("SELECT * FROM asset_types WHERE id = :id")
    suspend fun getAssetById(id: Int): AssetType?

    @Insert
    suspend fun insert(assetType: AssetType)

    @Update
    suspend fun update(assetType: AssetType)

    @Delete
    suspend fun delete(assetType: AssetType)
}
