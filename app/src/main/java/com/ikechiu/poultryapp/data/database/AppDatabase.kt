package com.ikechiu.poultryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ikechiu.poultryapp.data.database.dao.PoultryAccountDao
import com.ikechiu.poultryapp.data.database.dao.PoultryInventoryDao
import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel

@Database(entities = [PoultryAccountDbModel::class, PoultryInventoryDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun poultryAccountDao(): PoultryAccountDao
    abstract fun poultryInventoryDao(): PoultryInventoryDao
}