package com.ikechiu.poultryapp.data.local

import androidx.lifecycle.LiveData
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel

interface LocalDataSource {

    // PoultryAccountDbModel

    fun insertPoultryAccount(poultryAccountModel: PoultryAccountModel)

    fun deletePoultryAccount(poultryAccountModelId: Long)

    fun deletePoultryAccounts(poultryAccountModelIds: List<Long>)

    fun getAllPoultryAccounts(): LiveData<List<PoultryAccountModel>>


    // PoultryInventoryDbModel

    fun insertPoultryInventory(poultryInventoryModel: PoultryInventoryModel)

    fun deletePoultryInventory(poultryInventoryModelId: Long)

    fun deletePoultryInventories(poultryInventoryModelIds: List<Long>)

    fun getAllPoultryInventories(): LiveData<List<PoultryInventoryModel>>

}