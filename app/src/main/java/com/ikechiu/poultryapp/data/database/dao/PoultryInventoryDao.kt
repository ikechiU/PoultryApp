package com.ikechiu.poultryapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel

@Dao
interface PoultryInventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(poultryInventoryDbModel: PoultryInventoryDbModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg poultryInventoryDbModel: PoultryInventoryDbModel)

    @Query("DELETE FROM PoultryInventoryDbModel WHERE id LIKE :poultryInventoryDbModelId")
    fun delete(poultryInventoryDbModelId: Long)

    @Query("DELETE FROM PoultryInventoryDbModel WHERE id IN (:poultryInventoryDbModelIds)")
    fun deleteAll(poultryInventoryDbModelIds: List<Long>)

    @Query("SELECT * FROM PoultryInventoryDbModel ORDER BY id DESC")
    fun getAllPoultryInventories(): List<PoultryInventoryDbModel>

}