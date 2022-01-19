package com.ikechiu.poultryapp.data.database.dbmapper

import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel

interface DbMapper {

    // PoultryAccountDbModel -> PoultryAccountModel

    fun mapPoultryAccounts(
        poultryAccountDbModels: List<PoultryAccountDbModel>
    ): List<PoultryAccountModel>

    fun mapPoultryAccount(poultryAccountDbModel: PoultryAccountDbModel): PoultryAccountModel


    //  PoultryInventoryDbModel -> PoultryInventoryModel

    fun mapPoultryInventories(
        poultryInventoryDbModels: List<PoultryInventoryDbModel>
    ): List<PoultryInventoryModel>

    fun mapPoultryInventory(poultryInventoryDbModel: PoultryInventoryDbModel): PoultryInventoryModel


    // PoultryAccountModel -> PoultryAccountDbModel

    fun mapDbPoultryAccounts(
        poultryAccountModel: List<PoultryAccountModel>
    ): List<PoultryAccountDbModel>

    fun mapDbPoultryAccount(poultryAccountModel: PoultryAccountModel): PoultryAccountDbModel


    // poultryInventoryModel -> PoultryInventoryDbModel

    fun mapDbPoultryInventories(
        poultryInventoryModel: List<PoultryInventoryModel>
    ): List<PoultryInventoryDbModel>

    fun mapDbPoultryInventory(poultryInventoryModel: PoultryInventoryModel): PoultryInventoryDbModel
}