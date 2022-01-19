package com.ikechiu.poultryapp.data.database.dbmapper

import com.ikechiu.poultryapp.data.database.model.PoultryAccountDbModel
import com.ikechiu.poultryapp.data.database.model.PoultryInventoryDbModel
import com.ikechiu.poultryapp.domain.model.PoultryAccountModel
import com.ikechiu.poultryapp.domain.model.PoultryInventoryModel
import com.ikechiu.poultryapp.util.NEW_ID

class DbMapperImpl : DbMapper {
    override fun mapPoultryAccounts(poultryAccountDbModels: List<PoultryAccountDbModel>): List<PoultryAccountModel> {
        return poultryAccountDbModels.map { mapPoultryAccount(it) }
    }

    override fun mapPoultryAccount(poultryAccountDbModel: PoultryAccountDbModel): PoultryAccountModel {
        return with(poultryAccountDbModel) {
            PoultryAccountModel(
                id,
                time,
                sample,
                isEdited,
                standardBrownEggSales,
                organicEggSales,
                chickSales,
                henSales,
                cockerelSales,
                dungSales,
                totalSales,
                crackedCornFeedExpenses,
                comboFeedExpenses,
                waterExpenses,
                antibioticsDrugExpenses,
                regularDrugExpenses,
                utilityExpenses,
                totalExpenses,
                profitLoss
            )
        }
    }

    override fun mapPoultryInventories(poultryInventoryDbModels: List<PoultryInventoryDbModel>): List<PoultryInventoryModel> {
        return poultryInventoryDbModels.map { mapPoultryInventory(it) }
    }

    override fun mapPoultryInventory(poultryInventoryDbModel: PoultryInventoryDbModel): PoultryInventoryModel {
        return with(poultryInventoryDbModel) {
            PoultryInventoryModel(
                id,
                time,
                sample,
                isEdited,
                chicksCount,
                hensCount,
                cockerelsCount,
                mortalityCount,
                crackedCornFeedCount,
                comboFeedCount,
                standardBrownEggCount,
                organicEggCount,
                waterConsumptionCount,
                antibioticsDrugCount,
                regularDrugCount
            )
        }
    }

    override fun mapDbPoultryAccounts(poultryAccountModel: List<PoultryAccountModel>): List<PoultryAccountDbModel> {
        return poultryAccountModel.map { mapDbPoultryAccount(it) }
    }

    override fun mapDbPoultryAccount(poultryAccountModel: PoultryAccountModel): PoultryAccountDbModel {
        return with(poultryAccountModel) {
            if (id == NEW_ID) {
                PoultryAccountDbModel(
                    time = time,
                    isEdited = isEdited,
                    sample = sample,
                    standardBrownEggSales = standardBrownEggSales,
                    organicEggSales = organicEggSales,
                    chickSales = chickSales,
                    henSales = henSales,
                    cockerelSales = cockerelSales,
                    dungSales = dungSales,
                    totalSales = totalSales,
                    crackedCornFeedExpenses = crackedCornFeedExpenses,
                    comboFeedExpenses = comboFeedExpenses,
                    waterExpenses = waterExpenses,
                    antibioticsDrugExpenses = antibioticsDrugExpenses,
                    regularDrugExpenses = regularDrugExpenses,
                    utilityExpenses = utilityExpenses,
                    totalExpenses = totalExpenses,
                    profitLoss = profitLoss
                )
            } else {
                PoultryAccountDbModel(
                    id,
                    time,
                    sample,
                    isEdited,
                    standardBrownEggSales,
                    organicEggSales,
                    chickSales,
                    henSales,
                    cockerelSales,
                    dungSales,
                    totalSales,
                    crackedCornFeedExpenses,
                    comboFeedExpenses,
                    waterExpenses,
                    antibioticsDrugExpenses,
                    regularDrugExpenses,
                    utilityExpenses,
                    totalExpenses,
                    profitLoss
                )
            }
        }
    }

    override fun mapDbPoultryInventories(poultryInventoryModel: List<PoultryInventoryModel>): List<PoultryInventoryDbModel> {
        return poultryInventoryModel.map { mapDbPoultryInventory(it) }
    }

    override fun mapDbPoultryInventory(poultryInventoryModel: PoultryInventoryModel): PoultryInventoryDbModel {
        return with(poultryInventoryModel) {
            if (id == NEW_ID) {
                PoultryInventoryDbModel(
                    time = time,
                    isEdited = isEdited,
                    sample = sample,
                    chicksCount = chicksCount,
                    hensCount = hensCount,
                    cockerelsCount = cockerelsCount,
                    mortalityCount = mortalityCount,
                    crackedCornFeedCount = crackedCornFeedCount,
                    comboFeedCount = comboFeedCount,
                    standardBrownEggCount = standardBrownEggCount,
                    organicEggCount = organicEggCount,
                    waterConsumptionCount = waterConsumptionCount,
                    antibioticsDrugCount = antibioticsDrugCount,
                    regularDrugCount = regularDrugCount
                )
            } else {
                PoultryInventoryDbModel(
                    id,
                    time,
                    sample,
                    isEdited,
                    chicksCount,
                    hensCount,
                    cockerelsCount,
                    mortalityCount,
                    crackedCornFeedCount,
                    comboFeedCount,
                    standardBrownEggCount,
                    organicEggCount,
                    waterConsumptionCount,
                    antibioticsDrugCount,
                    regularDrugCount
                )
            }
        }
    }
}