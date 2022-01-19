package com.ikechiu.poultryapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel

@Dao
interface PoultryAccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(poultryAccountDbModel: PoultryAccountDbModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg poultryAccountDbModel: PoultryAccountDbModel)

    @Query("DELETE FROM PoultryAccountDbModel WHERE id LIKE :poultryAccountDbModelId")
    fun deleteAll(poultryAccountDbModelId: Long)

    @Query("DELETE FROM PoultryAccountDbModel WHERE id IN (:poultryAccountDbModelIds)")
    fun deleteAll(poultryAccountDbModelIds: List<Long>)

    @Query("SELECT * FROM PoultryAccountDbModel ORDER BY id DESC")
    fun getAllPoultryAccounts(): List<PoultryAccountDbModel>

}